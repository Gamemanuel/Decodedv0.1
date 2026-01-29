package org.firstinspires.ftc.teamcode.commands.shooter;

import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.solversLibComponents.InterpLUT.InterpLUT;
import org.firstinspires.ftc.teamcode.subsystems.LLSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class ShooterAutoLLCMD {

    private static InterpLUT VelocityLUT = new InterpLUT();

    static {
        // Format: VelocityLUT.add(Distance_Inches, Target_Velocity);

        // ENTER YOUR DATA FROM STEP 2 HERE:

        // 1. Close Range (e.g., 4 feet)
        VelocityLUT.add(24, -1000);
        VelocityLUT.add(36, -1015);
        VelocityLUT.add(48, -1025);
        VelocityLUT.add(60, -1045);
        VelocityLUT.add(72, -1175);
        VelocityLUT.add(84, -1200);
        VelocityLUT.add(96, -1320);
        VelocityLUT.add(108, -1360);
        VelocityLUT.add(120, -1525);

        // 4. Maximum Range Cap (If we see something extremely far)
        VelocityLUT.add(8000, -3000);

        VelocityLUT.createLUT();
    }

    private final ShooterSubsystem subsystem;
    private final LLSubsystem LL;

    public ShooterAutoLLCMD(ShooterSubsystem subsystem, LLSubsystem LL) {
        this.subsystem = subsystem;
        this.LL = LL;
    }

    public void execute(){
        // Ensure we have a valid target
        if (LL.result != null && LL.result.isValid()) {

            // 1. Get the distance from our new math method
            double distanceInches = LL.getDistanceInches();

            // 2. Safety check: Ensure distance is positive (valid)
            if(distanceInches > 0) {

                // 3. Look up the velocity based on distance
                double targetVelocity = VelocityLUT.get(distanceInches);

                // 4. Send to motor
                // Note: Update the Range.clip numbers to match your min/max safe RPMs
                subsystem.setTargetVelocity(targetVelocity-80);
            }
        } else {
            // Optional: Stop shooter or maintain idle speed if target is lost
            // subsystem.setTargetVelocity(0);
        }
    }

}