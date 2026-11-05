package org.firstinspires.ftc.teamcode.subsystem;


import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Launcher {

    // Constants
    private final int LAUNCH_POWER_INCREMENT = 200;

    // The Launcher uses two DC Motors
    private final DcMotorEx motorPollenLauncher;
    private final DcMotorEx motorNectarLauncher;

    // Default to launch at 70% power
    private int launchPowerNectar = 2000;
    private int launchPowerPollen = 2000;

    private long nextNectarUpdate;
    private long nextPollenUpdate;

    // Create a constructor to initialize the launcher
    public Launcher(HardwareMap hardwareMap, String nectarLauchMotor, String pollenLaunchMotor)
    {
        // Initialize the motors
        motorNectarLauncher = hardwareMap.get(DcMotorEx.class, nectarLauchMotor);
        motorPollenLauncher = hardwareMap.get(DcMotorEx.class, pollenLaunchMotor);

        // Set the motor directions
        motorNectarLauncher.setDirection(DcMotorSimple.Direction.FORWARD);
        motorPollenLauncher.setDirection(DcMotorSimple.Direction.REVERSE);

        // Turn on motor encoders
        motorNectarLauncher.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motorPollenLauncher.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        nextNectarUpdate = System.currentTimeMillis();
        nextPollenUpdate = System.currentTimeMillis();

        motorNectarLauncher.setVelocity(0);
        motorPollenLauncher.setVelocity(0);
    }

    public int getNectarLaunchVelocity()
    {
        return launchPowerNectar;
    }

    public int getPollenLaunchVelocity()
    {
        return launchPowerPollen;
    }

    public int increaseNectarLaunchPower()
    {
        if (System.currentTimeMillis() < nextNectarUpdate)
            return launchPowerNectar;

        // Increase the laucnh power by the default increment
        launchPowerNectar = launchPowerNectar + LAUNCH_POWER_INCREMENT;

        // Launch power cannot go over 1 / 2800 is the maximun tick
        if (launchPowerNectar > 2800)
            launchPowerNectar = 2800;

        nextNectarUpdate = System.currentTimeMillis() + 1000; // 1 second delay

        return launchPowerNectar;
    }

    public int decreaseNectarLaunchPower()
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

    public int increasePollenLaunchPower()
    {
        if (System.currentTimeMillis() < nextPollenUpdate)
            return launchPowerPollen;

        // Increase the laucnh power by the default increment
        launchPowerPollen = launchPowerPollen + LAUNCH_POWER_INCREMENT;

        // Launch power cannot go over 1
        if (launchPowerPollen > 2800)
            launchPowerPollen = 2800;

        nextPollenUpdate = System.currentTimeMillis() + 1000; // 1 second delay

        return launchPowerPollen;
    }

    public int decreasePollenLaunchPower()
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
        motorNectarLauncher.setVelocity(launchPowerNectar);
    }

    public void stopNectarLaunch()
    {
            motorNectarLauncher.setVelocity(0);
    }

    public void pollenLaunch()
    {
        motorPollenLauncher.setVelocity(launchPowerPollen);
    }

    public void pollenLaunch(int launchPower)
    {
        motorPollenLauncher.setVelocity(launchPower);
    }

    public void stopPollenLaunch()
    {
        motorPollenLauncher.setVelocity(0);
    }


    public void startBothLauchers()
    {
        nectarLaunch();
        pollenLaunch();
    }
}
