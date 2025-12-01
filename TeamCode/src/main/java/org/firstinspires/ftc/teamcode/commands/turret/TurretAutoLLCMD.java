package org.firstinspires.ftc.teamcode.commands.turret;


import org.firstinspires.ftc.teamcode.Alliance;
import org.firstinspires.ftc.teamcode.solversLibComponents.PIDFController.PIDFController;
import org.firstinspires.ftc.teamcode.subsystems.LLSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;

public class TurretAutoLLCMD {


    PIDFController llPidf;

    TurretSubsystem turret;
    LLSubsystem ll;

    double offset = 0;


    public TurretAutoLLCMD(TurretSubsystem turret, LLSubsystem ll) {
        this.turret = turret;
        this.ll = ll;

        llPidf = new PIDFController(TurretSubsystem.llPidfCoeffs);

//        addRequirements(subsystem);

    }

    public void execute() {
        llPidf.setCoefficients(TurretSubsystem.llPidfCoeffs);
        llPidf.setTolerance(0.1);
        llPidf.setMinimumOutput(TurretSubsystem.Minimum);

        if (ll.result != null && ll.result.isValid()) {
            Double Area = ll.getAllianceTA();
            if (Area != null) {

                if (Area > 0.55) {
                    offset = 0;
                } else if (Area < 0.55) {
                    offset = 3;
                }
            }

            llPidf.setSetPoint(0);

            Double tx = ll.getAllianceTX();


            if (ll.alliance == Alliance.BLUE ){
                if (tx != null) {
                    double turretPower = llPidf.calculate(tx-offset);
                    turret.setPower(turretPower);
                } else {
                    turret.setPower(0);
                }
            } else if (ll.alliance == Alliance.RED) {

                if (tx != null) {
                    double turretPower = llPidf.calculate(tx+offset);
                    turret.setPower(turretPower);
                } else {
                    turret.setPower(0);
                }
            }
        }

    }
    public void faceGoal(double tolerance) {
        Double tx = ll.getAllianceTX();
        double speed = Math.min(1,Math.max(tx/10, 0.25));
        if (Math.abs(tx) > tolerance) {
            if (tx > 0) {
                // negative is right
                turret.setPower(speed);
            } else {
                turret.setPower(speed);
            }
        } else {
            turret.setPower(0);
        }
    }
}