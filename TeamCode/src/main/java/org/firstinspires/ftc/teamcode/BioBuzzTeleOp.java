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
        Drive drive = new Drive();
        Gate gate = new Gate(GATE_NAME_NECTAR, GATE_NAME_POLLEN);
        Intake intake = new Intake(MOTOR_NAME_INTAKE);
        Launcher launcher = new Launcher(MOTOR_NAME_NECTAR_LAUNCH, MOTOR_NAME_POLLEN_LAUNCH);
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

        if (gamepad1.x)
            intake.startIntake();
        else
            intake.stopIntake();


        //Stops the Intake when Pressed



//
//
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
