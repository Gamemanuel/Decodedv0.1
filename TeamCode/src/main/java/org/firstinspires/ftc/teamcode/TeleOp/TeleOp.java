package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Alliance;
import org.firstinspires.ftc.teamcode.config.robotCommands;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public abstract class TeleOp extends robotCommands {
    Drivetrain drivetrain;
    public TeleOp(Alliance alliance) {
        super(alliance);
    }
    Gamepad hunter = gamepad1;
    Gamepad madelyn = gamepad2;

    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
    }

    public void loop() {
        drivetrain.Drive(hunter.left_stick_y,hunter.right_stick_x);
    }
}
