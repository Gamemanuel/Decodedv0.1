package org.firstinspires.ftc.teamcode.commands.shooter;


import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

import java.util.function.DoubleSupplier;

public class ShooterShootCmd {

    ShooterSubsystem shooterSubsystem;
    DoubleSupplier targetvelocity;

    public ShooterShootCmd(ShooterSubsystem shooterSubsystem, DoubleSupplier targetvelocity){
        this.shooterSubsystem = shooterSubsystem;
        this.targetvelocity = targetvelocity;
//        addRequirements(shooterSubsystem);


    }



    public void execute(){
        shooterSubsystem.setTargetVelocity(targetvelocity.getAsDouble());
    }


    public void end(boolean interrupted){
        shooterSubsystem.setTargetVelocity(0);
    }


    public boolean isFinished(){
        return false;
    }

}