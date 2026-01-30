package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.Webcam;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

public class WebcamAprilTagTest extends OpMode {

    Webcam webcam = new Webcam();

    @Override
    public void init() {
        webcam.init(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        webcam.update();

        AprilTagDetection id21 = webcam.getTagByID(21);

        webcam.detectionTelemetry(id21);
    }
}
