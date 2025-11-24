package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public abstract class robotCommands extends OpMode {

    private Alliance alliance;
    public robotCommands(Alliance alliance) {
        this.alliance = alliance;
    }

}
