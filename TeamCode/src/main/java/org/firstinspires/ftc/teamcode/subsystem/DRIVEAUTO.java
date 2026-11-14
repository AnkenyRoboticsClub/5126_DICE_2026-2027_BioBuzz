package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "DRIVETEST", group = "DRIVETEST")
public class DRIVEAUTO extends OpMode {


    private static final String MOTOR_NAME_FRONT_LEFT = "frontLeft";
    private static final String MOTOR_NAME_FRONT_RIGHT = "frontRight";
    private static final String MOTOR_NAME_BACK_LEFT = "backLeft";
    private static final String MOTOR_NAME_BACK_RIGHT = "backRight";

    private ElapsedTime runtime = new ElapsedTime();

    private Drive drive;


    @Override
    public void init() {

        drive = new Drive(hardwareMap, MOTOR_NAME_FRONT_RIGHT, MOTOR_NAME_FRONT_LEFT, MOTOR_NAME_BACK_RIGHT, MOTOR_NAME_BACK_LEFT);



    }

    //This is a method to establish a delay-like code.

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

    //This is the test code with pre-commands to tell the robot where to move and end.

    @Override
    public void start() {

        drive.drive(-0.3, 0, 0);

        delay(500);

        drive.drive(0, 0, -0.3);

        delay(1000);

        drive.drive(-0.3,0,0);

        delay(1000);

        drive.drive(0,0,0);

    }

    @Override
    public void loop() {


    }

}
