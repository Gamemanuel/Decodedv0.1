package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

@Config
@TeleOp(name = "Shooter Tuning", group = "Tuning")
public class ShooterTuning extends LinearOpMode {

    ShooterSubsystem shooter;

    // Tuning variable visible in Dashboard
    public static double TESTING_TARGET_RPM = 1500;

    @Override
    public void runOpMode() {
        // Connect to Dashboard Telemetry
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        shooter = new ShooterSubsystem(hardwareMap);

        telemetry.addLine("Ready to Tune. Open FTC Dashboard (192.168.43.1:8080)");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // 1. Set the target velocity from the Dashboard variable
            shooter.setTargetVelocity(TESTING_TARGET_RPM);

            // 2. Run the Periodic loop (Calculates PID + Feedforward)
            shooter.periodic();

            // 3. Telemetry for Graphing
            telemetry.addData("Target Velocity", TESTING_TARGET_RPM);
            telemetry.addData("Actual Velocity", shooter.shooter.getVelocity());

            // Show the values we are tuning
            telemetry.addData("Current kV", ShooterSubsystem.kV);
            telemetry.addData("Current kP", ShooterSubsystem.SCoeffs.p);

            telemetry.update();
        }
    }
}