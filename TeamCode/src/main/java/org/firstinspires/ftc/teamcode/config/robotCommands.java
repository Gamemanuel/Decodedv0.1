package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.LLSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;

public abstract class robotCommands extends OpMode {

    Drivetrain drivetrain;
    private final Alliance alliance;

    public TurretSubsystem turretSubsystem;
    public LLSubsystem llSubsystem;

    public robotCommands(Alliance alliance) {
        this.alliance = alliance;
    }

    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
        turretSubsystem = new TurretSubsystem(hardwareMap);
        llSubsystem = new LLSubsystem(hardwareMap, alliance);
    }

    public void loop() {
       turretSubsystem.periodic();
       llSubsystem.periodic();
       turretSubsystem.periodic();
        drivetrain.Drive(gamepad1.left_stick_y,gamepad1.right_stick_x);
    }
}
