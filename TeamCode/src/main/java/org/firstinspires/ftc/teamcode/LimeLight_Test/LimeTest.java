package org.firstinspires.ftc.teamcode.LimeLight_Test;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@TeleOp(name = "LimeLight", group = "TEST")
public class LimeTest extends OpMode {

    private Limelight3A limeCam;

    private IMU imu;

    private DcMotor FR;
    private DcMotor FL;
    private DcMotor BR;
    private DcMotor BL;

    double xAxis;
    double yAxis;
    double aAxis;

    @Override
    public void init() {

        limeCam = hardwareMap.get(Limelight3A.class, "limeLight");
        limeCam.pipelineSwitch(8);
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));

        FR = hardwareMap.get(DcMotor.class, "frontRight");
        FL = hardwareMap.get(DcMotor.class, "frontLeft");
        BR = hardwareMap.get(DcMotor.class, "backRight");
        BL = hardwareMap.get(DcMotor.class, "backLeft");

        FR.setDirection(DcMotorSimple.Direction.FORWARD);
        FL.setDirection(DcMotorSimple.Direction.FORWARD);
        BR.setDirection(DcMotorSimple.Direction.FORWARD);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);

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

            xAxis = llResult.getTx();
            yAxis = llResult.getTy();
            aAxis = llResult.getTa();

            FR.setPower(-xAxis / 10);
            FL.setPower(xAxis / 10);
            BR.setPower(-xAxis / 10);
            BL.setPower(xAxis / 10);


            telemetry.addData("Target X", xAxis);
            telemetry.addData("Target Y", yAxis);
            telemetry.addData("Target Area", aAxis);
            telemetry.addData("BotePose", botPose.toString());
            telemetry.addData("Yaw", botPose.getOrientation().getYaw());

        }
        else {
            telemetry.addData("Limelight", "Nothing found");
        }

        telemetry.update();
    }



}
