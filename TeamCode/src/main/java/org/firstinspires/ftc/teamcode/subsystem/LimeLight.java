package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class LimeLight {

    private Limelight3A limeCam;
    private IMU imu;



    public LimeLight(HardwareMap hardwareMap, String THELIME, String IMU) {

        limeCam = hardwareMap.get(Limelight3A.class, THELIME);
        limeCam.pipelineSwitch(8);
        imu = hardwareMap.get(IMU.class, IMU);
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));



    }

    public void camBoot() {

        limeCam.start();

    }

    public void autoAim(Drive drive) {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limeCam.updateRobotOrientation(orientation.getYaw());
        LLResult llResult = limeCam.getLatestResult();



        if (llResult != null && llResult.isValid()) {
            double xAxis = llResult.getTx();

            double turnPower = xAxis / 20.0;

            drive.drive(0,0, turnPower);


        }  else {

            drive.drive(0,0,0);

        }


    }

    public void updateTelemetry(Telemetry telemetry) {
        LLResult llResult = limeCam.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            telemetry.addData("Target X", llResult.getTx());
            telemetry.addData("Target Y", llResult.getTy());
            telemetry.addData("Target Area", llResult.getTa());
        } else {
            telemetry.addData("Limelight", "No Target");
        }

        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        telemetry.addData("Yaw", orientation.getYaw());
    }



}
