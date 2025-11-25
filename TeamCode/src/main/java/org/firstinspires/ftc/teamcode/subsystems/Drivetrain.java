package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.libraryUtils.MotorGroup;

public class Drivetrain {
    public MotorGroup LeftSide;
    public MotorGroup RightSide;
    MotorGroup motorGroup;

    // CPR (counts per motor revolution) calculations
    static final double     COUNTS_PER_MOTOR_REV    = 28.0;
    static final double     DRIVE_GEAR_REDUCTION    = 29.7;
    static final double     COUNTS_PER_WHEEL_REV    = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION;

    // RPM calculations
    static final double     MOTOR_FREE_SPEED        = 6000;
    static final double     THEORETICAL_RPM         = MOTOR_FREE_SPEED / DRIVE_GEAR_REDUCTION;

    public Drivetrain(HardwareMap hMap) {
        // We are defining the motors here:
        // Left side of the robot
        DcMotorEx frontLeft = hMap.get(DcMotorEx.class, "frontLeft");
        DcMotorEx backLeft = hMap.get(DcMotorEx.class, "backLeft");
        LeftSide = new MotorGroup(frontLeft,backLeft);

        // Right side of the robot
        DcMotorEx frontRight = hMap.get(DcMotorEx.class, "frontRight");
        DcMotorEx backRight = hMap.get(DcMotorEx.class, "backRight");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        RightSide = new MotorGroup(frontRight,backRight);
    }

    public void Drive(double forward, double turn) {
        LeftSide.setPower(forward + turn);
        RightSide.setPower(forward - turn);
    }

}
