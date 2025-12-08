package org.firstinspires.ftc.teamcode.commands.shooter;

import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.solversLibComponents.InterpLUT.InterpLUT;

import org.firstinspires.ftc.teamcode.subsystems.LLSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class ShooterAutoLLCMD {

    private static InterpLUT VelocityLUT = new InterpLUT();

    static {
        // Clear the old values (technically not needed as we are rewriting, but logic wise)

        // Format: VelocityLUT.add(Target_Area, Target_Velocity);

        // 1. Farthest Shot (Smallest Area)
        VelocityLUT.add(0.12, 1900);

        // 2. Mid-Field Shots
        VelocityLUT.add(0.2, 1670);
        VelocityLUT.add(0.4, 1430);

        // 3. Closest Shot (Largest Area)
        VelocityLUT.add(3.22, 1050);

        // 4. Safety Value (If area is massive, e.g., 10, stop the motor)
        VelocityLUT.add(10, 0);

        VelocityLUT.createLUT();
    }

    private final ShooterSubsystem subsystem;

    private final LLSubsystem LL;

    public ShooterAutoLLCMD(ShooterSubsystem subsystem, LLSubsystem LL) {
        this.subsystem = subsystem;
        this.LL = LL;
    }

    public void execute(){

        if (LL.result != null && LL.result.isValid()) {
            Double Area = LL.getAllianceTA();
            if(Area != null){
                double velocity = VelocityLUT.get(Area);

                subsystem.setTargetVelocity(Range.clip(velocity, 1050, 1900));
            }
        }
    }
}