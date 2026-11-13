package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Gate;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Launcher;

@TeleOp(name = "BioBuzz v3", group = "BioBuzz")
public class BioBuzzTeleOp extends OpMode {
    // Robot Constants
    private static final String MOTOR_NAME_NECTAR_LAUNCH = "nectarLaunch";
    private static final String MOTOR_NAME_POLLEN_LAUNCH = "pollenLaunch";

    private static final String MOTOR_NAME_INTAKE = "motorIntake";

    private static final String GATE_NAME_NECTAR = "nectarGate";
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

    private double nextPIDFUpdate = System.currentTimeMillis();

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
        ElapsedTime timer = new ElapsedTime();

        boolean waiting = true;
        while (waiting)
        {
            if (timer.milliseconds() > millisecondsToDelay)
                waiting = false;
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

    private void updateNextPIDF()
    {
        nextPIDFUpdate = System.currentTimeMillis() + 200;
    }

    private void pidfControl()
    {
        if (System.currentTimeMillis() < nextPIDFUpdate)
            return;

        if (gamepad2.dpadUpWasPressed())
        {
            updateNextPIDF();
            launcher.increasePValue();
        }

        if (gamepad2.dpadLeftWasPressed())
        {
            updateNextPIDF();
            launcher.decreasePValue();
        }


        if (gamepad2.dpadDownWasPressed())
        {
        updateNextPIDF();
        launcher.increaseIValue();
        }
        if (gamepad2.dpadRightWasPressed()){
            updateNextPIDF();
            launcher.decreaseIValue();
        }
        if(gamepad2.right_bumper){
            updateNextPIDF();
            launcher.increaseDValue();
        }
        if(gamepad2.left_bumper){
            updateNextPIDF();
            launcher.decreaseDValue();
        }
        if(gamepad2.yWasPressed()){
            updateNextPIDF();
            launcher.increaseFValue();
        }
        if(gamepad2.bWasPressed()){
            updateNextPIDF();
            launcher.decreaseFValue();
        }

}





    private void launchControl()
    {
        boolean isLaunchPower = launcher.isLaunchPower();
        telemetry.addData("Is Launch Power", isLaunchPower);
        telemetry.addData("Nectar Velocity", launcher.getNectarVelocity());
        telemetry.addData("Pollen Velocity", launcher.getPollenVelocity());

        // Print PIDF information
        PIDFCoefficients values = launcher.getPIDFValues();

        double p = values.p;
        double i = values.i;
        double d = values.d;
        double f = values.f;

        telemetry.addData("p values", p);
        telemetry.addData("i values", i);
        telemetry.addData("d values", d);
        telemetry.addData("f values", f);


        if (gamepad2.dpad_up)
            launcher.togglePowerOrVelocity();

//        // Nectar Gate
//        if (gamepad2.right_bumper)
//            gate.closeNectGate();
//        else
//            gate.openNectGate();
//
//        if (gamepad2.left_bumper)
//            gate.closePolyGate();
//        else
//            gate.openPolyGate();

        // You have to hold the trigger to launch nectar
        if (gamepad2.right_trigger > 0.5)
        {
            launcher.nectarLaunch();
            launcher.pollenLaunch(); // Test
        }
        else
        {
            launcher.stopNectarLaunch();
            launcher.stopPollenLaunch();
        }

        // You have to hold the trigger to launch pollen
        if (gamepad2.left_trigger > 0.5)
        {
            launcher.pollenLaunch();
            telemetry.addData("Pollen Launch", "Launching");
        }
        else
        {
            launcher.stopPollenLaunch();
            telemetry.addData("Pollen Launch", "Stopped");

        }

//        if (gamepad2.y)
//            launcher.increaseNectarLaunchPower();
//        if (gamepad2.b)
//            launcher.decreaseNectarLaunchPower();

        if (gamepad2.xWasPressed())
            launcher.increasePollenLaunchPower();
        if (gamepad2.aWasPressed())
            launcher.decreasePollenLaunchPower();

        double nectarLaunchPower = launcher.getLaunchPowerNectar();
        double pollenLaunchPower = launcher.getLaunchPowerPollen();

        telemetry.addData("Nectar Launch Power", nectarLaunchPower);
        telemetry.addData("Pollen Launch Power", pollenLaunchPower);
        telemetry.addData("Nectar RPM", launcher.getNectarRPM());
        telemetry.addData("Pollen RPM", launcher.getPollenRPM());


    }

    private void intakeControl()
    {
        if (gamepad1.right_bumper)
            intake.startIntake();
        else if (gamepad1.left_bumper)
        {
            intake.outTake();
        }


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

        telemetry.addData("Is Nectar Gate Open", gate.isNectarGateOpen());
        telemetry.addData("Nectar Gate Position", gate.getNectarGatePosition());
        telemetry.addData("Is Pollen Gate Open", gate.isPollenGateOpen());
        telemetry.addData("Pollen Gate Position", gate.getPollenGatePosition());

        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
