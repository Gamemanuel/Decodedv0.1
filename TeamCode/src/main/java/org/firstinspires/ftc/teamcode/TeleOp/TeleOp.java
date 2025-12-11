package org.firstinspires.ftc.teamcode.TeleOp;

import org.firstinspires.ftc.teamcode.Alliance;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.commands.shooter.ShooterAutoLLCMD;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.LLSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TurretSubsystem;
import org.firstinspires.ftc.teamcode.commands.turret.TurretAutoLLCMD;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

public abstract class TeleOp extends OpMode {

    Drivetrain drivetrain;
    Intake intake;
    TurretSubsystem turretSubsystem;
    LLSubsystem ll;
    TurretAutoLLCMD turretAuto;
    ShooterSubsystem shooter;
    ShooterAutoLLCMD shooterAutoCmd;

    private final Alliance alliance;

    public TeleOp(Alliance alliance) {
        this.alliance = alliance;
    }

    public void init() {
        // LINK DASHBOARD AND DRIVER STATION TELEMETRY
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);

        turretSubsystem = new TurretSubsystem(hardwareMap, alliance);
        ll = new LLSubsystem(hardwareMap, alliance);

        shooter = new ShooterSubsystem(hardwareMap);

        // Initialize commands
        turretAuto = new TurretAutoLLCMD(turretSubsystem, ll);
//        shooterAutoCmd = new ShooterAutoLLCMD(shooter, ll);

    }

    public void loop() {
        // run the ll loop every loop
        ll.periodic();
        // 1. DRIVETRAIN
        drivetrain.Drive(gamepad1.left_stick_y, gamepad1.right_stick_x);

        // 2. INTAKE
        intake.front.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
        intake.floop.setPosition(-gamepad2.left_stick_y * 0.75);

        // 3. TURRET LOGIC
        // Manual override triggers
        if (gamepad2.right_bumper) {
            turretSubsystem.setPower(-0.5);
        } else if (gamepad2.left_bumper) {
            turretSubsystem.setPower(0.5);
        } else {
            // AUTOMATIC TURRET TRACKING
            // Use a tolerance of 1.5 degrees
            turretAuto.faceAprilTag(1.5, alliance);
        }



//        if (gamepad2.right_stick_y > 0.1) {
//            // Manual Rev (optional override)
//            shooterSubsystem.setTargetVelocity(1500); // Set a static speed for manual
//        } else {
//            // AUTOMATIC DISTANCE SETTING
//            // This checks Limelight Area (ta) and sets target velocity using your LUT
//            shooterAutoCmd.execute();
//        }

        shooter.setTargetVelocity(-1350);

        // 2. Run the Periodic loop (Calculates PID + Feedforward)
        shooter.periodic();

        // 5. TELEMETRY
        telemetry.addData("Shooter Target", shooter.getTargetVelocity());
        telemetry.addData("Shooter Actual", shooter.shooter.getVelocity());
        telemetry.update();
    }
}