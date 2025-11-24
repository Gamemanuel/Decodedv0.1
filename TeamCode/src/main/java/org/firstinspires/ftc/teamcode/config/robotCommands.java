package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.LLSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;

public abstract class robotCommands extends OpMode {

    private Alliance alliance;

    public TurretSubsystem turretSubsystem;
    public LLSubsystem llSubsystem;

    public robotCommands(Alliance alliance) {
        this.alliance = alliance;
    }

    public void init() {
        turretSubsystem = new TurretSubsystem(hardwareMap);
        llSubsystem = new LLSubsystem(hardwareMap, alliance);
    }

    public void loop() {
       turretSubsystem.periodic();
       llSubsystem.periodic();
       turretSubsystem.periodic();
    }
}
