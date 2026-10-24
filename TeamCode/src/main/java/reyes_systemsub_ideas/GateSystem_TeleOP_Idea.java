package reyes_systemsub_ideas;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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

@TeleOp(name = "TELE_GATE", group = "V1")
public class GateSystem_TeleOP_Idea extends LinearOpMode {

    Servo SNG;
    Servo SPG;

    @Override
    public void runOpMode() {

                SNG = hardwareMap.get(Servo.class, "necterGate");
                SPG = hardwareMap.get(Servo.class, "pollenGate");

                SNG.setDirection(Servo.Direction.FORWARD);
                SPG.setDirection(Servo.Direction.FORWARD);




                while (opModeIsActive()) {








                }


    }

}
