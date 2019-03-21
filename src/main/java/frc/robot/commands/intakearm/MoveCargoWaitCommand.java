package frc.robot.commands.intakearm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.IntakeArm;;

public class MoveCargoWaitCommand extends Command {

    private IntakeArm intakeArm;

    public MoveCargoWaitCommand() {
        super();
        
        intakeArm = IntakeArm.getInstance();
        requires(intakeArm);
    }

    @Override
    protected void initialize() {
        intakeArm.moveCargo();
    }

    @Override
    protected void execute() {
        
    }

    @Override
    protected boolean isFinished() {
        return intakeArm.getState() == IntakeArm.State.MANUAL;
    }
}