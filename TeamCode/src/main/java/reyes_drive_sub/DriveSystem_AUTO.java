package reyes_drive_sub;

// ALL your imports go here together
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

// ... keep all other imports here

@Autonomous(name = "AUTO_DRIVE", group = "PROTO") // or @TeleOp if it applies
public class DriveSystem_AUTO extends LinearOpMode {

    // 1. Declare hardware variables here
    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;

    @Override
    public void runOpMode() {
        // 2. Initialize hardware here
        motorFrontRight = hardwareMap.get(DcMotor.class, "frontRight");
        motorFrontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        motorBackRight = hardwareMap.get(DcMotor.class, "backRight");
        motorBackLeft = hardwareMap.get(DcMotor.class, "backLeft");
        // Other code goes in here
        motorFrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE  );
        motorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);


        waitForStart();

        // boolean is a true or false value
        boolean isForward = true;

        // 3. Run your robot loop
        while (opModeIsActive()) {

            // double is any decimal point number
            double motorPower;

            if (isForward)
                motorPower = 0.2;
            else
                motorPower = -0.2;

            // Robot code goes here
            motorFrontRight.setPower(motorPower);
            motorFrontLeft.setPower(motorPower);
            motorBackRight.setPower(motorPower);
            motorBackLeft.setPower(motorPower);

            sleep(3000);

            isForward = !isForward; // Flip the value from true -> false (or from false -> true)

        }
    }
}
// Make sure this closing brace is at the very end of the file


