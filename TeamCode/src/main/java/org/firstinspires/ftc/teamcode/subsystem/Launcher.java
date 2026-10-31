package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Launcher {

    // Constants
    private final double LAUNCH_POWER_INCREMENT = 0.1;

    // The Launcher uses two DC Motors
    private DcMotor motorPollenLauncher;
    private DcMotor motorNectarLauncher;

    // Default to launch at 70% power
    private double launchPowerNectar = 0.7;
    private double launchPowerPollen = 0.7;

    private long nextNectarUpdate;
    private long nextPollenUpdate;

    // Create a constructor to initialize the launcher
    public Launcher(HardwareMap hardwareMap, String nectarLauchMotor, String pollenLaunchMotor)
    {
        // Initialize the motors
        motorNectarLauncher = hardwareMap.get(DcMotor.class, nectarLauchMotor);
        motorPollenLauncher = hardwareMap.get(DcMotor.class, pollenLaunchMotor);

        // Set the motor directions
        motorNectarLauncher.setDirection(DcMotorSimple.Direction.FORWARD);
        motorPollenLauncher.setDirection(DcMotorSimple.Direction.REVERSE);

        nextNectarUpdate = System.currentTimeMillis();
        nextPollenUpdate = System.currentTimeMillis();
    }

    public double getLaunchPowerNectar()
    {
        return launchPowerNectar;
    }

    public double getLaunchPowerPollen()
    {
        return launchPowerPollen;
    }

    public double increaseNectarLaunchPower()
    {
        if (System.currentTimeMillis() < nextNectarUpdate)
            return launchPowerNectar;

        // Increase the laucnh power by the default increment
        launchPowerNectar = launchPowerNectar + LAUNCH_POWER_INCREMENT;

        // Launch power cannot go over 1
        if (launchPowerNectar > 1)
            launchPowerNectar = 1;

        nextNectarUpdate = System.currentTimeMillis() + 1000; // 1 second delay

        return launchPowerNectar;
    }

    public double decreaseNectarLaunchPower()
    {
        if (System.currentTimeMillis() < nextNectarUpdate)
            return launchPowerNectar;

        // Increase the laucnh power by the default increment
        launchPowerNectar = launchPowerNectar - LAUNCH_POWER_INCREMENT;

        // Launch power cannot go over 1
        if (launchPowerNectar < 0)
            launchPowerNectar = 0;

        nextNectarUpdate = System.currentTimeMillis() + 1000; // 1 second delay

        return launchPowerNectar;
    }

    public double increasePollenLaunchPower()
    {
        if (System.currentTimeMillis() < nextPollenUpdate)
            return launchPowerPollen;

        // Increase the laucnh power by the default increment
        launchPowerPollen = launchPowerPollen + LAUNCH_POWER_INCREMENT;

        // Launch power cannot go over 1
        if (launchPowerPollen > 1)
            launchPowerPollen = 1;

        nextPollenUpdate = System.currentTimeMillis() + 1000; // 1 second delay

        return launchPowerPollen;
    }

    public double decreasePollenLaunchPower()
    {
        if (System.currentTimeMillis() < nextPollenUpdate)
            return launchPowerPollen;

        // Increase the laucnh power by the default increment
        launchPowerPollen = launchPowerPollen - LAUNCH_POWER_INCREMENT;

        // Launch power cannot go below 0
        if (launchPowerPollen < 0)
            launchPowerPollen = 0;

        nextPollenUpdate = System.currentTimeMillis() + 1000; // 1 second delay

        return launchPowerPollen;
    }

    public void nectarLaunch()
    {
        motorNectarLauncher.setPower(launchPowerNectar);
    }

    public void stopNectarLaunch()
    {
        motorNectarLauncher.setPower(0);
    }

    public void pollenLaunch()
    {
        motorPollenLauncher.setPower(launchPowerPollen);
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
