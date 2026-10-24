package reyes_drive_sub;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "TELE_DRIVE", group = "V1")
public class DriveSystem_TeleOp extends LinearOpMode {

    DcMotor MFR;
    DcMotor MFL;
    DcMotor MBR;
    DcMotor MBL;
    double Foreward;
    double Reverse;
    double Turn;
    double Strafe;
    double Vertical;

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

            Foreward = gamepad1.right_trigger;
            Reverse = gamepad1.left_trigger * -1;
            Turn = gamepad1.left_stick_x;
            Strafe = gamepad1.right_stick_x;
            Vertical = gamepad1.right_stick_y;

            MFR.setPower(Foreward + Reverse - Turn - Strafe + Vertical);
            MFL.setPower(Foreward + Reverse + Turn + Strafe + Vertical);
            MBR.setPower(Foreward + Reverse - Turn + Strafe + Vertical);
            MBL.setPower(Foreward + Reverse + Turn - Strafe + Vertical);



        }


    }

}
