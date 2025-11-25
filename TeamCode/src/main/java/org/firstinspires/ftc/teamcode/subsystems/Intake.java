package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    public DcMotorEx intake1;
    public Servo intake2;
    public Intake (HardwareMap hMap) {
        intake1 = hMap.get(DcMotorEx.class, "intake1");
        intake2 = hMap.get(Servo.class, "intake2");
    }
}
