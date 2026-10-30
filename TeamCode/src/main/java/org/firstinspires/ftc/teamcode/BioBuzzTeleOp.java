package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Gate;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Launcher;

@TeleOp(name = "RENAME", group = "RENAME")
public class BioBuzzTeleOp extends OpMode {
    // Robot Constants
    private static final String MOTOR_NAME_NECTAR_LAUNCH = ""; // TODO: Add name
    private static final String MOTOR_NAME_POLLEN_LAUNCH = ""; // TODO: Add name

    private static final String MOTOR_NAME_INTAKE = "motorIntake";

    private static final String GATE_NAME_NECTAR = "necterGate";
    private static final String GATE_NAME_POLLEN = "pollenGate";

    private static final String MOTOR_NAME_FRONT_LEFT = "";
    private static final String MOTOR_NAME_FRONT_RIGHT = "";
    private static final String MOTOR_NAME_BACK_LEFT = "";
    private static final String MOTOR_NAME_BACK_RIGHT = "";

    private ElapsedTime runtime = new ElapsedTime();

    // Declare the robot's subsystems
    private Drive drive;
    private Gate gate;
    private Intake intake;
    private Launcher launcher;

    /**
     * User-defined init method
     * <p>
     * This method will be called once, when the INIT button is pressed.
     */
    @Override
    public void init() {
        Drive drive = new Drive(MOTOR_NAME_FRONT_RIGHT, MOTOR_NAME_FRONT_LEFT, MOTOR_NAME_BACK_RIGHT, MOTOR_NAME_BACK_LEFT);
        Gate gate = new Gate(GATE_NAME_NECTAR, GATE_NAME_POLLEN);
        Intake intake = new Intake(MOTOR_NAME_INTAKE);
        Launcher launcher = new Launcher(MOTOR_NAME_NECTAR_LAUNCH, MOTOR_NAME_POLLEN_LAUNCH);
    }

    private void delay(long millisecondsToDelay)
    {
        try {
            Thread.sleep(millisecondsToDelay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e); // TODO: Normally you would handle this error - leave it for now
        }
    }

    private void autoLaunchNectar()
    {
        // Turn on nectar launch motor
        launcher.nectarLaunch();

        // Wait 1 second (for launch motor to get to full power)
        delay(1000);

        // Open nectar gate
        gate.openNectGate();

        // Wait 4 seconds (for all nectar to finish launching)
        delay(4000);

        // Close gate
        gate.closeNectGate();

        // Turn off nectar launch motor
        launcher.stopNectarLaunch();
    }

    private void autoLaunchPollen()
    {
        // Turn on nectar launch motor
        launcher.nectarLaunch();

        // Wait 1 second (for launch motor to get to full power)
        delay(1000);

        // Open nectar gate
        gate.openPolyGate();

        // Wait 4 seconds (for all nectar to finish launching)
        delay(4000);

        // Close gate
        gate.closePolyGate();

        // Turn off nectar launch motor
        launcher.stopPollenLaunch();


    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit START
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits START
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    @Override
    public void loop() {

        telemetry.addData("Launch Power", launcher.getLaunchPower());

        if (gamepad2.right_trigger > 0.5)
        {
            autoLaunchNectar();
        }

        if (gamepad2.left_trigger > 0.5)
        {
            autoLaunchPollen();
        }

//        // Example code only - delete before running
//        // Increaes Launch speed
//        launcher.increaseLaunchPower();
//
//        // Decrease launch speed
//        launcher.decreateLaunchPower();
//
//        // Launch Nectar
//        launcher.nectarLaunch();
//
//        // Stop Nectar
//        launcher.stopNectarLaunch();
//
//        // Launch Pollen
//        launcher.pollenLaunch();
//
//        // Stop Pollen
//        launcher.stopPollenLaunch();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
