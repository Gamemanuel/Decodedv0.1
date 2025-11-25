package org.firstinspires.ftc.teamcode.libraryUtils;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class MotorGroup {
    private final DcMotorEx Motor1;
    private final DcMotorEx Motor2;
    public MotorGroup(DcMotorEx motor1, DcMotorEx motor2, DcMotorEx.Direction direction) {
        this.Motor1 = motor1;
        this.Motor2 = motor2;
        motor1.setDirection(direction);
        motor2.setDirection(direction);
    }
    public void setPower(double power) {
        Motor1.setPower(power);
        Motor2.setPower(power);
    }
    public double getPower() {
        return Motor1.getPower();
    }

}
