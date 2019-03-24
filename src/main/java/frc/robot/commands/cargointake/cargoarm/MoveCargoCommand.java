package frc.robot.commands.cargointake.cargoarm;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.robot.Robot;
import frc.robot.subsystems.cargointake.CargoArm;;

public class MoveCargoCommand extends InstantCommand {

    private CargoArm cargoArm;

    public MoveCargoCommand() {
        super();
        
        cargoArm = Robot.cargoArm;
        requires(cargoArm);
    }

    @Override
    protected void initialize() {
        cargoArm.moveCargo();
    }
}