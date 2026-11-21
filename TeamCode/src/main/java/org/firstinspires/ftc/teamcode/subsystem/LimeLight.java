package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class LimeLight {

    private Limelight3A limeCam;
    private IMU imu;




    public void LIMEMAP(HardwareMap hardwareMap, String THELIME, String IMU) {

        limeCam = hardwareMap.get(Limelight3A.class, THELIME);
        limeCam.pipelineSwitch(8);
        imu = hardwareMap.get(IMU.class, IMU);
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));

    };


    public void camBoot() {

        limeCam.start();

    }

    public void autoAim(Gamepad gamepad2) {
        if (gamepad2.dpad_up) { YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limeCam.updateRobotOrientation(orientation.getYaw());
        LLResult llResult = limeCam.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            Pose3D botPose = llResult.getBotpose_MT2();

           double xAxis = llResult.getTx();
           double yAxis = llResult.getTy();
           double aAxis = llResult.getTa();

            FR.setPower(-xAxis / 20);
            FL.setPower(xAxis / 20);
            BR.setPower(-xAxis / 20);
            BL.setPower(xAxis / 20);


            telemetry.addData("Target X", xAxis);
            telemetry.addData("Target Y", yAxis);
            telemetry.addData("Target Area", aAxis);
            telemetry.addData("BotePose", botPose.toString());
            telemetry.addData("Yaw", botPose.getOrientation().getYaw());

        }
        else {
            telemetry.addData("Limelight", "Nothing found");
        }

        telemetry.update(); }
    }

    public void


}
