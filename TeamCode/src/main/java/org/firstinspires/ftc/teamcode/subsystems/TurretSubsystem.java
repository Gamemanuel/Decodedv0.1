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
    private final Alliance alliance;

    public static PIDFCoefficients llPidfCoeffs = new PIDFCoefficients(0.017, 0, 0.001, 0);
    public static double Minimum = 0.043;


    public TurretSubsystem(HardwareMap hMap, Alliance alliance) {
        Turret = hMap.get(CRServo.class, "turntable");
        this.alliance = alliance;
        ll = new LLSubsystem(hMap, alliance);
    }

    public void setTurretPower(double power) {
        Turret.setPower(power);
    }

//    public void lookAtGoal(double tolerance) {
//        ll.limelight.pipelineSwitch(index);
//        LLResult llResult = robot.limelight.getLatestResult();
//        if (llResult != null && llResult.isValid()) {
//            double tx = llResult.getTx() + 5; // offset correction
//            telemetry.addData("Tx", llResult.getTx());
//            telemetry.addData("Tx with offset", tx);
//            telemetry.addData("Ty", llResult.getTy());
//            telemetry.addData("Ta", llResult.getTa());
//            telemetry.addData("wants to stop", Math.abs(tx) < tolerance);
//            if (Math.abs(tx) > tolerance) {
//                if (tx > 0) {
//                    // negative is right
//                    robot.turntable.setPower(-speed);
//                } else {
//                    robot.turntable.setPower(speed);
//                }
//            } else {
//                robot.turntable.setPower(0);
//            }
//        } else {
//            telemetry.addData("", "Nothing is being detected");
//            robot.turntable.setPower(0);
//        }
//        telemetry.update();
//    }
}