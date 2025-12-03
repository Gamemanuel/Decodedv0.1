package org.firstinspires.ftc.teamcode.config;

import org.firstinspires.ftc.teamcode.Alliance;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class robotCommands extends OpMode {

    private final Alliance alliance;

    public robotCommands(Alliance alliance) {
        this.alliance = alliance;
    }

}
