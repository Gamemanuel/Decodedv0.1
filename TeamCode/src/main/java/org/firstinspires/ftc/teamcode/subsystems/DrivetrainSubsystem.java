package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DrivetrainSubsystem {

    // CPR (counts per motor revolution) calculations
    static final double     COUNTS_PER_MOTOR_REV    = 28.0;
    static final double     DRIVE_GEAR_REDUCTION    = 29.7;
    static final double     COUNTS_PER_WHEEL_REV    = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION;

    // RPM calculations
    static final double     MOTOR_FREE_SPEED        = 6000;
    static final double     THEORETICAL_RPM         = MOTOR_FREE_SPEED / DRIVE_GEAR_REDUCTION;

    public DrivetrainSubsystem(HardwareMap hMap) {
        // We are defining the motors here:
        // Left side of the robot
        DcMotorEx frontLeft = hMap.get(DcMotorEx.class, "frontLeft");
        DcMotorEx backLeft = hMap.get(DcMotorEx.class, "backLeft");
        DcMotorEx[] LeftSide = {frontLeft, backLeft};

        // Right side of the robot
        DcMotorEx frontRight = hMap.get(DcMotorEx.class, "frontRight");
        DcMotorEx backRight = hMap.get(DcMotorEx.class, "backRight");
        DcMotorEx[] rightSide = {frontRight, backRight};


    }

    public void Drive() {

    }

}
