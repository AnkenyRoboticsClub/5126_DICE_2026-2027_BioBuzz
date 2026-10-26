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
import com.sun.source.tree.WhileLoopTree;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name = "Tele_Launcher", group = "VI")
public class LaunchSystem_TeleOP extends LinearOpMode {

    DcMotor MPL;
    DcMotor MNL;

    // Successful Test at .7!
    double LeftLaunch = .7;

    double RightLaunch = .7;

    @Override
    public void runOpMode() {

        //MPL = hardwareMap.get(DcMotor.class, "pollenLaunch");
        MNL = hardwareMap.get(DcMotor.class, "necterLaunch");
        MPL = hardwareMap.get(DcMotor.class, "pollenLaunch");

        MNL.setDirection(DcMotorSimple.Direction.FORWARD);
        MPL.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        long updateDelay = 2000; // 2000 = 2 seconds
        long nextPowerUpdate = System.currentTimeMillis();


        int loopCount = 0;
        while (opModeIsActive()) {
            telemetry.addData("Loop Count", loopCount++);

            long timeUntilNextPowerUpdate = nextPowerUpdate - System.currentTimeMillis();
            if (timeUntilNextPowerUpdate < 0)
            {
                telemetry.addData("Time Until Next Update", "Now");

                if (gamepad2.leftBumperWasPressed()) {
                    LeftLaunch = Math.max(0.2, LeftLaunch - 0.1);
                    RightLaunch = Math.max(0.2, RightLaunch - 0.1);

                    nextPowerUpdate = System.currentTimeMillis() + updateDelay;
                }

                if (gamepad2.rightBumperWasPressed()) {
                    LeftLaunch = Math.min(1.0, LeftLaunch + 0.1);
                    RightLaunch = Math.min(1.0, RightLaunch + 0.1);

                    nextPowerUpdate = System.currentTimeMillis() + updateDelay;
                }
            }
            else
            {
                telemetry.addData("Time Until Next Update", timeUntilNextPowerUpdate);

            }


           if (gamepad2.left_trigger > .1) {
               MPL.setPower(LeftLaunch);
           }
               else{
                   MPL.setPower(0.0);
               }

            if (gamepad2.right_trigger > .1) {
                MNL.setPower(RightLaunch);
            }
            else{
                MNL.setPower(0.0);
            }

            //            telemetry.addData("MPL_POWER", MPL.getPower());
            telemetry.addData("Left Launch", LeftLaunch);
            telemetry.addData("Right Launch", RightLaunch);
            telemetry.addData("MNL_POWER", MNL.getPower());
            telemetry.update();
           }


        }

    }
