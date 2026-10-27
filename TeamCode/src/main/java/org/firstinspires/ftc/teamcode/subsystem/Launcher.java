package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


public class Launcher {

    // Constants
    private final double LAUNCH_POWER_INCREMENT = 0.1;

    // The Launcher uses two DC Motors
    private DcMotor motorPollenLauncher;
    private DcMotor motorNectarLauncher;

    // Default to launch at 70% power
    private double launchPower = 0.7;


    // Create a constructor to initialize the launcher
    public Launcher(String nectarLauchMotor, String pollenLaunchMotor)
    {
        // Initialize the motors
        motorNectarLauncher = hardwareMap.get(DcMotor.class, "necterLaunch");
        motorPollenLauncher = hardwareMap.get(DcMotor.class, "pollenLaunch");

        // Set the motor directions
        motorNectarLauncher.setDirection(DcMotorSimple.Direction.FORWARD);
        motorPollenLauncher.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public double getLaunchPower()
    {
        return launchPower;
    }

    public double increaseLaunchPower()
    {
        // Increase the laucnh power by the default increment
        launchPower = launchPower + LAUNCH_POWER_INCREMENT;

        // Launch power cannot go over 1
        if (launchPower > 1)
            launchPower = 1;

        return launchPower;
    }

    public double decreateLaunchPower()
    {


        return -999; // TODO Update
    }

    public void nectarLaunch()
    {
        motorNectarLauncher.setPower(launchPower);
    }

    public void stopNectarLaunch()
    {
        motorNectarLauncher.setPower(0);
    }

    public void pollenLaunch()
    {
        motorPollenLauncher.setPower(launchPower);
    }

    public void stopPollenLaunch()
    {
        motorPollenLauncher.setPower(0);
    }


    public void startBothLauchers()
    {
        nectarLaunch();
        pollenLaunch();
    }
}
