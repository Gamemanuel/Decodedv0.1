package org.firstinspires.ftc.teamcode.commands.turret;


import com.qualcomm.hardware.limelightvision.LLResult;

import org.firstinspires.ftc.teamcode.Alliance;
import org.firstinspires.ftc.teamcode.solversLibComponents.PIDFController.PIDFController;
import org.firstinspires.ftc.teamcode.subsystems.LLSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;

public class TurretAutoLLCMD {

    TurretSubsystem turret;
    LLSubsystem ll;
    double prevSpeed = 0;

    public TurretAutoLLCMD(TurretSubsystem turret, LLSubsystem ll) {
        this.turret = turret;
        this.ll = ll;


    }

    public double[] faceGoal(double tolerance, Alliance alliance) {
        if (alliance == Alliance.RED) { // if your alliance is red
            ll.limelight.pipelineSwitch(3); //only look at red goal april tag
        } else if (alliance == Alliance.BLUE) { // vice
            ll.limelight.pipelineSwitch(2); // versa
        }
        double tx = 0;
        double speed = 0;
        LLResult result = ll.limelight.getLatestResult();
        if (result != null && result.isValid()) {
            tx = result.getTx();
            speed = Math.min(1, Math.max(tx / 10, 0.25)); // slows down, min is 0.25 power, max is 1, slows down at 10 units
            if (Math.abs(tx) > tolerance) {
                turret.setPower(speed);
                prevSpeed = speed;
            } else {
                turret.setPower(0);
            }
        } else {
            turret.setPower(-prevSpeed);
        }
        return new double[] {tx, speed}; // telementry
    }
}