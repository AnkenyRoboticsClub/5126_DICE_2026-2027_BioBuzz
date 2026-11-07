package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Gate;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Launcher;


@Autonomous(name = "BioBuzzAutoLaunch and drive", group = "BioBuzz")
public class BioBuzzAuto extends OpMode {

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

    private boolean firstRun = true;



    @Override
    public void init() {
        drive = new Drive(hardwareMap, MOTOR_NAME_FRONT_RIGHT, MOTOR_NAME_FRONT_LEFT, MOTOR_NAME_BACK_RIGHT, MOTOR_NAME_BACK_LEFT);
        gate = new Gate(hardwareMap, GATE_NAME_NECTAR, GATE_NAME_POLLEN);
        intake = new Intake(hardwareMap, MOTOR_NAME_INTAKE);
        launcher = new Launcher(hardwareMap, MOTOR_NAME_NECTAR_LAUNCH, MOTOR_NAME_POLLEN_LAUNCH);

    }


    private void delay(long millisecondsToDelay, String label)
    {


        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        boolean waiting = true;
        while (waiting)
        {
            long waitTime = millisecondsToDelay - (long)timer.milliseconds();

            telemetry.addData("Delay for", label);
            telemetry.addData("Wait time", waitTime);
            telemetry.update();

            if (waitTime <= 0)
                waiting = false;
        }


    }

    private void delay(long millisecondsToDelay)
    {
        delay(millisecondsToDelay, "Default Delay");
    }

    @Override
    public void loop() {

        if (!firstRun) {
            drive.drive(0, 0.5, 0);

            return;
        }





        //delay 5 secs
        //open gate
        //leave it open until whole program is stopped

        launcher.pollenLaunch(.65);

        delay(2000, "Waiting to open gate");
        gate.openPolyGate();
        delay(5000, "Waiting to stop launch");
        launcher.stopPollenLaunch();
        gate.closePolyGate();
        drive.drive(.3,0,0);
        delay(1500);
        drive.drive(0,-.5,0);
        delay(1000);
        drive.drive(1,0,0);
        delay(1500);
        drive.drive(0,-.5,0);
        delay(800);
        drive.drive(0,0,0);
        telemetry.update();



        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        long millisecondsToDelay = 750;
        boolean waiting = true;
        while (waiting)
        {
            long waitTime = millisecondsToDelay - (long)timer.milliseconds();


            telemetry.addData("Wait time", waitTime);
            telemetry.update();


            if (waitTime <= 0)
                waiting = false;
        }


        drive.drive(0, 0, 0);

        firstRun = false;

    }
}
