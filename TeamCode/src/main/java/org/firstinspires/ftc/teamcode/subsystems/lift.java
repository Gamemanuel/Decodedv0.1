package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.libraryUtils.ButtonCombo;
import org.firstinspires.ftc.teamcode.libraryUtils.MotorGroup;

public class lift {
    DcMotorEx right, left;
    MotorGroup lift;
    public lift(HardwareMap hMap) {
        right = hMap.get(DcMotorEx.class,"liftR");
        left = hMap.get(DcMotorEx.class,"liftL");
        lift = new MotorGroup(right,left);
    }
}
