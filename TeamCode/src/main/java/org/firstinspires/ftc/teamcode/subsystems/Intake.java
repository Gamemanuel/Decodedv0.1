package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    public DcMotorEx front;
    public Servo floop;
    public Intake (HardwareMap hMap) {
        front = hMap.get(DcMotorEx.class, "intake1");
        floop = hMap.get(Servo.class, "intake2");
    }
}
