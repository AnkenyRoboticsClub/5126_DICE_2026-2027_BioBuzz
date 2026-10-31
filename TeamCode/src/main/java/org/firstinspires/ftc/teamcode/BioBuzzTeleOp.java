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

@TeleOp(name = "BioBuzz", group = "BioBuzz")
public class BioBuzzTeleOp extends OpMode {
    // Robot Constants
    private static final String MOTOR_NAME_NECTAR_LAUNCH = "nectarLaunch";
    private static final String MOTOR_NAME_POLLEN_LAUNCH = "pollenLaunch";

    private static final String MOTOR_NAME_INTAKE = "motorIntake";

    private static final String GATE_NAME_NECTAR = "necterGate";
    private static final String GATE_NAME_POLLEN = "pollenGate";

    private static final String MOTOR_NAME_FRONT_LEFT = "frontLeft";
    private static final String MOTOR_NAME_FRONT_RIGHT = "frontRight";
    private static final String MOTOR_NAME_BACK_LEFT = "backLeft";
    private static final String MOTOR_NAME_BACK_RIGHT = "backRight";

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
        drive = new Drive(hardwareMap, MOTOR_NAME_FRONT_RIGHT, MOTOR_NAME_FRONT_LEFT, MOTOR_NAME_BACK_RIGHT, MOTOR_NAME_BACK_LEFT);
        gate = new Gate(hardwareMap, GATE_NAME_NECTAR, GATE_NAME_POLLEN);
        intake = new Intake(hardwareMap, MOTOR_NAME_INTAKE);
        launcher = new Launcher(hardwareMap, MOTOR_NAME_NECTAR_LAUNCH, MOTOR_NAME_POLLEN_LAUNCH);
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

    private void autoLaunchPollen( )
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

    private void launchControl()
    {
        // Nectar Gate
        if (gamepad2.right_bumper)
            gate.toggleNectarGate();

        if (gamepad2.left_bumper)
            gate.togglePollenGate();

        // You have to hold the trigger to launch nectar
        if (gamepad2.right_trigger > 0.5)
            launcher.nectarLaunch();
        else
            launcher.stopNectarLaunch();

        // You have to hold the trigger to launch pollen
        if (gamepad2.left_trigger > 0.5)
            launcher.pollenLaunch();
        else
            launcher.stopPollenLaunch();

        if (gamepad2.y)
            launcher.increaseNectarLaunchPower();
        if (gamepad2.b)
            launcher.decreaseNectarLaunchPower();

        if (gamepad2.x)
            launcher.increasePollenLaunchPower();
        if (gamepad2.a)
            launcher.decreasePollenLaunchPower();

        double nectarLaunchPower = launcher.getLaunchPowerNectar();
        double pollenLaunchPower = launcher.getLaunchPowerPollen();

        telemetry.addData("Nectar Launch Power", nectarLaunchPower);
        telemetry.addData("Pollen Launch Power", pollenLaunchPower);


    }

    private void intakeControl()
    {
        if (gamepad1.a)
            intake.startIntake();
        else
            intake.stopIntake();
    }

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    @Override
    public void loop() {

        launchControl();
        intakeControl();
        drive.drive(gamepad1);

        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
