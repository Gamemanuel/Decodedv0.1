package org.firstinspires.ftc.teamcode.auto.Shoot3CMD;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Alliance;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterAutoLLCMD;
import org.firstinspires.ftc.teamcode.commands.turret.TurretAutoLLCMD;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.LLSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;
import org.firstinspires.ftc.teamcode.Robot;

@Config
public abstract class Shoot3CMD extends LinearOpMode {

    // --- TUNING CONSTANTS ---
    public static double DRIVE_POWER = -0.3;
    public static final double TARGET_DISTANCE_INCHES = 69.0;
    public static final double SHOOTER_TOLERANCE = 2375.0;
    public static final long PUSH_DURATION_MS = 600;
    public static final long SHOT_DELAY_MS = 1000;

    // Flipper/Servo Positions
    public static final double FLIPPER_STOW_POS = 0.0;
    public static final double FLIPPER_SHOOT_POS = 0.75;

    // Turret
    public static final double TURRET_TOLERANCE = 1.5;
    public static final double TURRET_SHOOT_SPEED = 0.2;

    Robot robot; // The single robot instance
    private final Alliance alliance;

    public Shoot3CMD(Alliance alliance) {
        this.alliance = alliance;
    }

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        robot = new Robot(hardwareMap, alliance);
        robot.intake.floop.setPosition(FLIPPER_STOW_POS);

        waitForStart();

        if (isStopRequested()) return;

        // Drive To position
        while (robot.ll.getDistanceInches() > TARGET_DISTANCE_INCHES && opModeIsActive()) {
            robot.drivetrain.Drive(DRIVE_POWER, 0);
            runSubsystems();
            telemetry.addData("Phase", "1. Driving");
            telemetry.addData("Distance", robot.ll.getDistanceInches());
            telemetry.update();
        }
        robot.drivetrain.Drive(0, 0);

        // Wait for flywheel to get to speed
        long timeout = 4000;
        long startTime = System.currentTimeMillis();

        while (opModeIsActive() && System.currentTimeMillis() < startTime + timeout) {
            runSubsystems(); // Updates PID and Vision

            double target = robot.shooter.getTargetVelocity();
            double actual = robot.shooter.shooter.getVelocity();

            if (Math.abs(target - actual) <= SHOOTER_TOLERANCE) {
                break;
            }
            telemetry.addData("Phase", "2. Spooling Up");
            telemetry.addData("Err", target - actual);
            telemetry.update();
        }

        // Shoot 3 Balls
        if (opModeIsActive()) {
            robot.intake.front.setPower(-1.0);

            for (int i = 1; i <= 3; i++) {
                if (!opModeIsActive()) break;
                telemetry.addData("Phase", "3. Shooting Ball " + i);
                telemetry.update();

                // --- FIRE THE BALL ---
                if (i <= 2) {
                    safeWait(PUSH_DURATION_MS);
                } else {
                    robot.intake.floop.setPosition(FLIPPER_SHOOT_POS);
                    safeWait(PUSH_DURATION_MS);
                    robot.intake.floop.setPosition(FLIPPER_STOW_POS);
                }

                // --- RECOVERY ---
                robot.intake.front.setPower(0);
                safeWait(SHOT_DELAY_MS);

                while (Math.abs(robot.shooter.getTargetVelocity() - robot.shooter.shooter.getVelocity()) > SHOOTER_TOLERANCE && opModeIsActive()) {
                    runSubsystems();
                    telemetry.addData("Phase", "Recovering Speed...");
                    telemetry.update();
                }

                if (i < 3) {
                    robot.intake.front.setPower(-1.0);
                }
            }
        }

        // Stop Everything
        robot.intake.front.setPower(0);
        robot.intake.floop.setPosition(FLIPPER_STOW_POS);
        robot.shooter.setTargetVelocity(0);
        robot.turretSubsystem.setPower(0);
        robot.drivetrain.Drive(0,0);

        while (robot.ll.getDistanceInches() < 90 && opModeIsActive() && robot.ll.getDistanceInches() != 1000) {
            robot.drivetrain.Drive(-DRIVE_POWER, 0);
            runSubsystems(); // Updates PID and Vision
            telemetry.addData("Phase", "1. Driving");
            telemetry.addData("Distance", robot.ll.getDistanceInches());
            telemetry.update();
        }
        robot.drivetrain.Drive(0, 0);
    }

    /**
     * Replaces sleep(). Waits for the duration while keeping the shooter PID running.
     */
    public void safeWait(long durationMs) {
        long start = System.currentTimeMillis();
        while (opModeIsActive() && System.currentTimeMillis() < start + durationMs) {
            runSubsystems();
        }
    }

    /**
     * Helper to keep all subsystems active
     */
    public void runSubsystems() {
        robot.runPeriodic();
        robot.turretAuto.faceAprilTag(TURRET_TOLERANCE, alliance);
    }
}