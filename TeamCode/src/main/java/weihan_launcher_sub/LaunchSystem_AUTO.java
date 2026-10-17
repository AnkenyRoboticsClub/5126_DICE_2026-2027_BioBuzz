package weihan_launcher_sub;

import com.qualcomm.hardware.ams.AMSColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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


@Autonomous(name = "AUTO_LAUNCHERS", group = "PROTO")
public class LaunchSystem_AUTO extends LinearOpMode {

    DcMotor motorPollenLauncher;
    DcMotor motorNectarLauncher;

    @Override
    public void runOpMode() {

        motorPollenLauncher = hardwareMap.get(DcMotor.class, "MPL");
        motorNectarLauncher = hardwareMap.get(DcMotor.class, "MNL");

        motorPollenLauncher.setDirection(DcMotorSimple.Direction.FORWARD);
        motorNectarLauncher.setDirection(DcMotorSimple.Direction.FORWARD);


        waitForStart();

        while (opModeIsActive()) {

            motorNectarLauncher.setPower(1);
            motorPollenLauncher.setPower(1);

            sleep(3000);


        }

    }


}