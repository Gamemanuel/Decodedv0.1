package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Alliance;

@Config
public class TurretSubsystem {
    CRServo Turret;
    LLSubsystem ll;
    public static PIDFCoefficients llPidfCoeffs = new PIDFCoefficients(0.017, 0, 0.001, 0);
    public static double Minimum = 0.043;


    public TurretSubsystem(HardwareMap hMap, Alliance alliance) {
        Turret = hMap.get(CRServo.class, "turntable");
        ll = new LLSubsystem(hMap, alliance);
    }

    public void setPower(double power) {
        Turret.setPower(power);
    }

    public void periodic() {
        FtcDashboard.getInstance().getTelemetry().addData("turret power", Turret.getPower());
    }
}