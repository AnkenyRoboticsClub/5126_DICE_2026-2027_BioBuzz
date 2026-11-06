package org.firstinspires.ftc.teamcode.WeihantestEncoder;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "Tele_Launcher", group = "VI")
public class LaunchEncoder extends LinearOpMode {

    private DcMotorEx motorPollenLauncher;
    private DcMotorEx motorNectarLauncher;

    int LeftLaunch = 2800;

    int RightLaunch = 2800;

    public void runOpMode() {

        motorNectarLauncher = hardwareMap.get(DcMotorEx.class, "nectarLauchMotor");
        motorPollenLauncher = hardwareMap.get(DcMotorEx.class, "pollenLaunchMotor");

        motorNectarLauncher.setDirection(DcMotorSimple.Direction.FORWARD);
        motorPollenLauncher.setDirection(DcMotorSimple.Direction.REVERSE);


        motorNectarLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorPollenLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();

        long updateDelay = 2000; // 2000 = 2 seconds
        long nextPowerUpdate = System.currentTimeMillis();


        int loopCount = 0;
        while (opModeIsActive()) {
            telemetry.addData("Loop Count", loopCount++);

            long timeUntilNextPowerUpdate = nextPowerUpdate - System.currentTimeMillis();
            if (timeUntilNextPowerUpdate < 0) {
                telemetry.addData("Time Until Next Update", "Now");

                if (gamepad2.leftBumperWasPressed()) {
                    LeftLaunch = Math.max(600, LeftLaunch - 200);
                    RightLaunch = Math.max(600, RightLaunch - 200);

                    nextPowerUpdate = System.currentTimeMillis() + updateDelay;
                }

                if (gamepad2.rightBumperWasPressed()) {
                    LeftLaunch = Math.min(2800, LeftLaunch + 200);
                    RightLaunch = Math.min(2800, RightLaunch + 200);

                    nextPowerUpdate = System.currentTimeMillis() + updateDelay;
                }
            } else {
                telemetry.addData("Time Until Next Update", timeUntilNextPowerUpdate);


            }


            if (gamepad2.left_trigger > 300) {
                motorPollenLauncher.setVelocity(LeftLaunch);
            }

            else{
                motorPollenLauncher.setVelocity(0);
            }


            if (gamepad2.right_trigger > 300) {
                motorNectarLauncher.setVelocity(RightLaunch);
            }

            else{
                motorNectarLauncher.setVelocity(0);
            }




            //            telemetry.addData("MPL_POWER", MPL.getPower());
            telemetry.addData("Left Launch", LeftLaunch);
            telemetry.addData("Right Launch", RightLaunch);
            telemetry.addData("MNL_POWER", motorNectarLauncher.getPower());
            telemetry.update();
        }



    }

}
