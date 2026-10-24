package reyes_drive_sub;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@TeleOp(name = "TELE_DRIVE", group = "V1")
public class DriveSystem_TeleOp extends LinearOpMode{

    DcMotor MFR;
    DcMotor MFL;
    DcMotor MBR;
    DcMotor MBL;
    double Left = gamepad1.right_stick_x;

    @Override
    public void runOpMode() {

        MFR = hardwareMap.get(DcMotor.class, "frontRight");
        MFL = hardwareMap.get(DcMotor.class, "frontLeft");
        MBR = hardwareMap.get(DcMotor.class, "backRight");
        MBL = hardwareMap.get(DcMotor.class, "backLeft");

        MFR.setDirection(DcMotorSimple.Direction.FORWARD);
        MFL.setDirection(DcMotorSimple.Direction.FORWARD);
        MBR.setDirection(DcMotorSimple.Direction.REVERSE);
        MBL.setDirection(DcMotorSimple.Direction.FORWARD);


        waitForStart();

        while (opModeIsActive()) {

            float Foreward = gamepad1.right_trigger;

            MFR.setPower(gamepad1.right_trigger);
            MFL.setPower(gamepad1.right_trigger);
            MBR.setPower(gamepad1.right_trigger);
            MBL.setPower(gamepad1.right_trigger);

            float Reverse = gamepad1.left_trigger * -1;

            MFR.setPower(Reverse);
            MFL.setPower(Reverse);
            MBR.setPower(Reverse);
            MBL.setPower(Reverse);

            if (Left < 0) {
                MFR.setPower(gamepad1.right_stick_x);
                MBR.setPower(gamepad1.right_stick_x);

            }

            if (Left > 0) {
                MFL.setPower(gamepad1.right_stick_x);
                MBL.setPower(gamepad1.right_stick_x);

            }


        }



    }


}
