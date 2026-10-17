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
        motorFrontRight = hardwareMap.get(DcMotor.class, "MFR");
        motorFrontLeft = hardwareMap.get(DcMotor.class, "MFL");
        motorBackRight = hardwareMap.get(DcMotor.class, "MBR");
        motorBackLeft = hardwareMap.get(DcMotor.class, "MBL");
        // Other code goes in here
        motorFrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);


        waitForStart();

        // 3. Run your robot loop
        while (opModeIsActive()) {
            // Robot code goes here





        }
    }
}
// Make sure this closing brace is at the very end of the file


