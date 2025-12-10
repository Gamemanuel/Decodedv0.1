package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.VoltageSensor; // Import this
import org.firstinspires.ftc.teamcode.solversLibComponents.PIDFController.PIDFController;

@Config
public class ShooterSubsystem {

    public DcMotorEx shooter;
    public PIDFController shooterPIDF;
    private VoltageSensor batteryVoltageSensor; // To read battery voltage

    // PID Coefficients - Tune these in Dashboard!
    public static PIDFCoefficients SCoeffs = new PIDFCoefficients(0, 0, 0, 0);
    public static double kV = 0; // Base feedforward

    private double TargetVelocity = 0;

    public ShooterSubsystem(HardwareMap hMap){
        shooter = hMap.get(DcMotorEx.class,"shooter");
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        shooter.setDirection(DcMotorSimple.Direction.REVERSE);

        // Initialize voltage sensor (usually named "Control Hub")
        batteryVoltageSensor = hMap.voltageSensor.iterator().next();

        shooterPIDF = new PIDFController(SCoeffs);
    }

    public void periodic(){
        // 1. Update Coefficients from Dashboard
        shooterPIDF.setCoefficients(SCoeffs);

        // 2. Get Current State
        double currentVelocity = shooter.getVelocity();
        double currentVoltage = batteryVoltageSensor.getVoltage();

        // 3. Calculate PID Output
        // Note: Changed .calculate() to .calculateOutput() to match your library
        double pidOutput = shooterPIDF.calculateOutput(currentVelocity);

        // 4. Calculate Feedforward (kV * Target)
        double feedforward = kV * TargetVelocity;

        // 5. Combine and Voltage Compensate
        // We normalize the power to 12V. If battery is 14V, we scale down. If 11V, we scale up.
        double targetPower = (feedforward + pidOutput) * (12.0 / currentVoltage);

        // 6. Apply Power
        // Ensure we don't send negative power if we just want to coast down (optional)
        shooter.setPower(Math.max(0, targetPower));
    }

    public void setTargetVelocity(double target){
        TargetVelocity = target;
        // Update the PID setpoint immediately
        shooterPIDF.setSetPoint(target);
    }

    public double getTargetVelocity(){
        return TargetVelocity;
    }
}