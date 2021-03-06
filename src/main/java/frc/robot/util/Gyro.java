package frc.robot.util;

import com.kauailabs.navx.frc.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI.Port;

/**
 * Wrapper class for the navX-MXP and ADXRS450_Gyro
 * 
 * Mostly utilizes the navX-MXP, but uses the ADXRS450 as backup if it disconnects
 */

public class Gyro {

    private static Gyro instance = null;

    private AHRS navx;
    private double resetRoll;
    private double resetPitch;

    private ADXRS450_Gyro gyro;

    private Gyro() {
        navx = new AHRS(Port.kMXP);
        resetRoll = 0;
        resetPitch = 0;

        gyro = new ADXRS450_Gyro();
        gyro.calibrate();
    }

    /**
     * Gets the current yaw angle.
     * 
     * @return The angle in degrees.
     */
    public double getYawAngle() {
        return navx.getYaw();
    }

    /**
     * Gets the current roll angle.
     * 
     * @return The angle in degrees.
     */
    public double getRollAngle() {
        return navx.getRoll() - resetRoll;
    }

    /**
     * Gets the current pitch angle.
     * 
     * @return The angle in degrees.
     */
    public double getPitchAngle() {
        return navx.getPitch() - resetPitch;
    }

    /**
     * Sets the current yaw angle to "0".
     */
    public void zeroYawAngle() {
        navx.zeroYaw();
    }

    /**
     * Sets the current roll angle to "0".
     */
    public void zeroRollAngle() {
        resetRoll = getRollAngle();
    }

    /**
     * Sets the current pitch angle to "0".
     */
    public void zeroPitchAngle() {
        resetPitch = getPitchAngle();
    }

    /**
     * Gets the current rotation of the robot.
     * 
     * @return The angle in degrees.
     */
    public double getRobotAngle() {
        return getYawAngle();
    }

    /**
     * Gets the current tilt of the robot while climbing. Utilizes the NavX if it is connected, but
     * otherwise it will use the backup gyro
     * 
     * @return THe angle in degrees
     */
    public double getClimbTiltAngle() {
        if (navXConnected()) {
            return getRollAngle();
        } else {
            return -gyro.getAngle();
        }
    }

    /**
     * Sets the current rotation of the robot to "0".
     */
    public void zeroRobotAngle() {
        zeroYawAngle();
    }

    /**
     * Sets the current tilt of the robot to "0".
     */
    public void zeroClimbTiltAngle() {
        zeroRollAngle();
        gyro.reset();
    }

    /**
     * Returns whether or not the NavX is currently connected and sending valid data
     * 
     * @return whether or not the NavX is currently connected
     */
    public boolean navXConnected() {
        return navx.isConnected();
    }

    /**
     * Displays the angles on {@code SmartDashboard}.
     */
    public void outputValues() {
        // SmartDashboard.putNumber("Yaw Angle", getYawAngle());
        // SmartDashboard.putNumber("Roll Angle", getRollAngle());
        // SmartDashboard.putNumber("Pitch Angle", getPitchAngle());

        SmartDashboard.putNumber("Robot Angle", getRobotAngle());
        SmartDashboard.putNumber("Climb Tilt Angle", getClimbTiltAngle());
        SmartDashboard.putBoolean("Gyro Connected", navXConnected());
    }

    public static Gyro getInstance() {
        if (instance == null) {
            instance = new Gyro();
        }
        return instance;
    }
}
