package frc.robot.commands.cargointake.cargoarm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.cargointake.CargoArm;;

public class MoveRocketWaitCommand extends Command {

    private CargoArm cargoArm;

    private double start;

    public MoveRocketWaitCommand() {
        super();
        
        cargoArm = Robot.cargoArm;
        requires(cargoArm);
    }

    @Override
    protected void initialize() {
        cargoArm.moveRocket();
        start = Timer.getFPGATimestamp();
    }

    @Override
    protected void execute() {
        
    }

    @Override
    protected boolean isFinished() {
        return cargoArm.getState() == CargoArm.State.MANUAL || 
            Timer.getFPGATimestamp() - start > RobotMap.CARGO_ARM_PID_WAIT;
    }
}