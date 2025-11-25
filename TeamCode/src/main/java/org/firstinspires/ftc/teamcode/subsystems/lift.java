package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class lift {
    DcMotorEx right, left;
    MotorGroup lift;
    public lift(HardwareMap hMap) {
        right = hMap.get(DcMotorEx.class,"liftR");
        left = hMap.get(DcMotorEx.class,"liftL");
        lift = new MotorGroup(right,left);
    }
}
