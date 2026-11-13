package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class Launcher {

    // Constants
    private static final double LAUNCH_POWER_INCREMENT = 0.025;
    private static final double LAUNCH_VELOCITY_INCREMENT = 25;

    // The Launcher uses two DC Motors
    private DcMotorEx motorPollenLauncher;
    private DcMotorEx motorNectarLauncher;

    // Default to launch at 70% power
    private double launchPowerNectar = 0.5;
    private double launchPowerPollen = 1.0;

    // Default Launch velocity
    private double launchVelocityNectar = 1200;
    private double launchVelocityPollen = 10000;

    private long nextNectarUpdate;
    private long nextPollenUpdate;
    private long nextToggleUpdate;


    private boolean isLaunchPower = true;


    private double motorCPM = 28;

    private double p, i, d, f;




    // Create a constructor to initialize the launcher
    public Launcher(HardwareMap hardwareMap, String nectarLauchMotor, String pollenLaunchMotor)
    {
        // Initialize the motors
        motorNectarLauncher = hardwareMap.get(DcMotorEx.class, nectarLauchMotor);
        motorPollenLauncher = hardwareMap.get(DcMotorEx.class, pollenLaunchMotor);

        // Set the motor directions
        motorNectarLauncher.setDirection(DcMotorSimple.Direction.FORWARD);
        motorPollenLauncher.setDirection(DcMotorSimple.Direction.REVERSE);

        motorPollenLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorNectarLauncher.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));

        getPIDFValues();

        setpollenPIDF();



        //Set the motors to run in Encoders
//        motorNectarLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorPollenLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        nextNectarUpdate = System.currentTimeMillis();
        nextPollenUpdate = System.currentTimeMillis();
        nextToggleUpdate = System.currentTimeMillis();

        motorNectarLauncher.setPower(0);
        motorPollenLauncher.setPower(0);
    }

    public void setpollenPIDF() {
        motorPollenLauncher.setVelocityPIDFCoefficients(p,i,d,f);
    }

    public void increasePValue()
    {
        p = p + 1;
        setpollenPIDF();
    }


    public void decreasePValue(){
        p = p - 1;
        setpollenPIDF();
    }

    public void increaseIValue(){
        i = i + 1;
        setpollenPIDF();
    }

    public void decreaseIValue(){
        i = i - 1;
        setpollenPIDF();
    }

    public void increaseDValue(){
        d = d + 1;
        setpollenPIDF();
    }

    public void decreaseDValue(){
        d = d - 1;
        setpollenPIDF();
    }

    public void increaseFValue(){
        f = f + 1;
        setpollenPIDF();
    }

    public void decreaseFValue(){
        f = f - 1;
        setpollenPIDF();
    }






    public void togglePowerOrVelocity()
    {

        if (System.currentTimeMillis() < nextToggleUpdate)
            return;

        isLaunchPower = !isLaunchPower;

        nextToggleUpdate = System.currentTimeMillis() + 1000; // 1 second delay
    }

    public PIDFCoefficients getPIDFValues(){
        PIDFCoefficients values = motorPollenLauncher.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        p = values.p;
        i = values.i;
        d = values.d;
        f = values.f;
        return values;
    }

    public boolean isLaunchPower()
    {
        return isLaunchPower;
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
        launchVelocityNectar = launchVelocityNectar + LAUNCH_VELOCITY_INCREMENT;

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
        launchVelocityNectar = launchVelocityNectar - LAUNCH_VELOCITY_INCREMENT;

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
        launchVelocityPollen = launchVelocityPollen + LAUNCH_VELOCITY_INCREMENT;

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
        launchVelocityPollen = launchVelocityPollen - LAUNCH_VELOCITY_INCREMENT;

        // Launch power cannot go below 0
        if (launchPowerPollen < 0)
            launchPowerPollen = 0;

        nextPollenUpdate = System.currentTimeMillis() + 1000; // 1 second delay

        return launchPowerPollen;
    }

    public void nectarLaunch()
    {
        if (isLaunchPower)
            motorNectarLauncher.setPower(launchPowerNectar);
        else {
            // New velocity code
//            motorNectarLauncher.setVelocity(launchVelocityNectar);
            motorNectarLauncher.setPower(launchPowerNectar);
        }
    }

    public void stopNectarLaunch()
    {
        motorNectarLauncher.setPower(0);
    }

    public void pollenLaunch()
    {
        if (isLaunchPower)
            motorPollenLauncher.setPower(launchPowerPollen);
        else
        {
            // New code for velocity
//            motorPollenLauncher.setVelocity(launchVelocityPollen);
            motorPollenLauncher.setPower(launchPowerPollen);

        }
    }

    public double getNectarVelocity()
    {
        return launchVelocityNectar;
    }

    public double getPollenVelocity()
    {
        return launchVelocityPollen;
    }

    public void pollenLaunch(double launchPower)
    {
        // TODO Update auto later
        motorPollenLauncher.setPower(launchPower);
    }

    public void stopPollenLaunch()
    {
        motorPollenLauncher.setPower(0);
    }

//TODO Update the both launchers later
    public void startBothLauchers()
    {
        nectarLaunch();
        pollenLaunch();
    }


    //Run in encoder testing
    public double getLaunchVelocityNectar()
        {
            return motorNectarLauncher.getVelocity();
        }


        public double getLaunchVelocityPollen()
        {
            return motorPollenLauncher.getVelocity();
        }


        public double getNectarRPM()
        {
            return (getLaunchVelocityNectar()/-motorCPM)*60;
        }

        public double getPollenRPM()
        {
            return (getLaunchVelocityPollen()/motorCPM)*60;
        }






    private void delay(long millisecondsToDelay)
    {
        delay(millisecondsToDelay);
    }

}
