package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Alliance;

public abstract class robotCommands extends OpMode {

    private Alliance alliance;

    public robotCommands(Alliance alliance) {
        this.alliance = alliance;
    }
}
