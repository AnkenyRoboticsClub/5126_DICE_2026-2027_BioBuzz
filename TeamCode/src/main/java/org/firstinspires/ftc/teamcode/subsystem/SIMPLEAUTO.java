package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Gate;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Launcher;

@TeleOp(name = "SIMPLEAUTO", group = "AUTODRIVE")
public class SIMPLEAUTO extends OpMode {


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
