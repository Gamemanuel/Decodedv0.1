package org.firstinspires.ftc.teamcode.config;

import org.firstinspires.ftc.teamcode.Alliance;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.subsystems.LLSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;

public abstract class robotCommands extends OpMode {

    private final Alliance alliance;

    public robotCommands(Alliance alliance) {
        this.alliance = alliance;
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
