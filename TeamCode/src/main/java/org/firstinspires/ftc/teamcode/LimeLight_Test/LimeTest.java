package org.firstinspires.ftc.teamcode.LimeLight_Test;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


import javax.crypto.spec.OAEPParameterSpec;

@TeleOp(name = "LimeLight", group = "TEST")
public class LimeTest extends OpMode {

    private Limelight3A limeCam;

    private IMU imu;

    @Override
    public void init() {

        limeCam = hardwareMap.get(Limelight3A.class, "limeLight");
        limeCam.pipelineSwitch(8);
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));

    }

    @Override
    public void start() {

        limeCam.start();

    }



    @Override
    public void loop() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limeCam.updateRobotOrientation(orientation.getYaw());
        LLResult llResult = limeCam.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            Pose3D botPose = llResult.getBotpose_MT2();
            telemetry.addData("Target X", llResult.getTx());
            telemetry.addData("Target Y", llResult.getTy());
            telemetry.addData("Target Area", llResult.getTa());
            telemetry.addData("BotePose", botPose.toString());
            telemetry.addData("Yaw", botPose.getOrientation().getYaw());

        }
        else {
            telemetry.addData("Limelight", "Nothing found");
        }

        telemetry.update();
    }



}
