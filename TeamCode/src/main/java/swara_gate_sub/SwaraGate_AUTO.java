package swara_gate_sub;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
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

@Autonomous(name="PROTO", group="GATESYSTEM")
public class SwaraGate_AUTO extends LinearOpMode {
    Servo NG;
    Servo PG;

        public void runOpMode() {

            NG = hardwareMap.get(Servo.class,"necterGate");
            PG = hardwareMap.get(Servo.class,"pollenGate");

            NG.setDirection(Servo.Direction.FORWARD);
            PG.setDirection(Servo.Direction.FORWARD);



            while (opModeIsActive()) {






            }


        }



}
