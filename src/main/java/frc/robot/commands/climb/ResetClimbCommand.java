package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.robot.Robot;
import frc.robot.subsystems.Climb;

public class ResetClimbCommand extends InstantCommand {

    private Climb climb;

    public ResetClimbCommand() {
        super();
        
        climb = Robot.climb;
        requires(climb);
    }

    @Override
    protected void initialize() {
        climb.reset();
    }
}
