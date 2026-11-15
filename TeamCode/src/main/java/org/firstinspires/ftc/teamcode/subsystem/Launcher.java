package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Launcher {

    // Constants
    private final double LAUNCH_VELOCITY_INCREMENT = 50;
    private final double ENCODER_COUNTS_PER_REV = 28.0;

    // The Launcher uses two DC Motors
    private DcMotorEx motorPollenLauncher;
    private DcMotorEx motorNectarLauncher;

    // Default to launch at 2400 rpm
    private double targetRpmNectar = 2100;
    private double targetRpmPollen = 2100;

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

        motorNectarLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorPollenLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorNectarLauncher.setVelocityPIDFCoefficients(1.17, 0.117, 0.0, 17);
        motorPollenLauncher.setVelocityPIDFCoefficients(1.17, 0.117, 0.0, 17);

        nextNectarUpdate = System.currentTimeMillis();
        nextPollenUpdate = System.currentTimeMillis();

        motorNectarLauncher.setPower(0);
        motorPollenLauncher.setPower(0);
    }

//returns the ticks per sec
    private double rpmToTicksPerSec(double rpm) {
        return (rpm / 60.0) * ENCODER_COUNTS_PER_REV;
    }

    //The actaul RPM
    private double TicksPerSecToRpm(double TicksPerSec){
        return (TicksPerSec / ENCODER_COUNTS_PER_REV) * 60;
    }

    public double getTargetRpmNectar()
    {
        return targetRpmNectar;
    }

    public double getTargetRpmPollen()
    {
        return targetRpmPollen;
    }

    public double increaseNectarLaunchPower()
    {
        if (System.currentTimeMillis() < nextNectarUpdate)
            return targetRpmNectar;

        // Increase the laucnh power by the default increment
        targetRpmNectar = targetRpmNectar + LAUNCH_VELOCITY_INCREMENT;

        // Launch power cannot go over 1
        if (targetRpmNectar > 6000)
            targetRpmNectar = 6000;

        nextNectarUpdate = System.currentTimeMillis() + 1000; // 1 second delay

        return targetRpmNectar;
    }

    public double decreaseNectarLaunchPower()
    {
        if (System.currentTimeMillis() < nextNectarUpdate)
            return targetRpmNectar;

        // Increase the launch power by the default increment
        targetRpmNectar = targetRpmNectar - LAUNCH_VELOCITY_INCREMENT;

        // Launch power cannot go over 1
        if (targetRpmNectar < 0)
            targetRpmNectar = 0;

        nextNectarUpdate = System.currentTimeMillis() + 1000; // 1 second delay

        return targetRpmNectar;
    }

    public double increasePollenLaunchPower()
    {
        if (System.currentTimeMillis() < nextPollenUpdate)
            return targetRpmPollen;

        // Increase the laucnh power by the default increment
        targetRpmPollen = targetRpmPollen + LAUNCH_VELOCITY_INCREMENT;

        // Launch power cannot go over 1
        if (targetRpmPollen > 6000)
            targetRpmPollen = 6000;

        nextPollenUpdate = System.currentTimeMillis() + 1000; // 1 second delay

        return targetRpmPollen;
    }

    public double decreasePollenLaunchPower()
    {
        if (System.currentTimeMillis() < nextPollenUpdate)
            return targetRpmPollen;

        // Increase the laucnh power by the default increment
        targetRpmPollen = targetRpmPollen - LAUNCH_VELOCITY_INCREMENT;

        // Launch power cannot go below 0
        if (targetRpmPollen < 0)
            targetRpmPollen = 0;

        nextPollenUpdate = System.currentTimeMillis() + 1000; // 1 second delay

        return targetRpmPollen;
    }

    public void nectarLaunch()
    {
        motorNectarLauncher.setVelocity(rpmToTicksPerSec(targetRpmNectar));
    }

    public void stopNectarLaunch()
    {
        motorNectarLauncher.setVelocity(0);
    }

    public void pollenLaunch()
    {
        motorPollenLauncher.setVelocity(rpmToTicksPerSec(targetRpmPollen));
    }

    public void pollenLaunch(double targetRpm)
    {
        motorPollenLauncher.setVelocity(rpmToTicksPerSec(targetRpm));
    }

    public void stopPollenLaunch()
    {
        motorPollenLauncher.setVelocity(0);
    }


     public double getActualNectarRpm(){
        return Math.abs(TicksPerSecToRpm(motorNectarLauncher.getVelocity()));
     }


     public double getActualPollenRpm(){
        return  Math.abs(TicksPerSecToRpm(motorPollenLauncher.getVelocity()));
     }


    public void startBothLaunchers()
    {
        nectarLaunch();
        pollenLaunch();
    }
}
