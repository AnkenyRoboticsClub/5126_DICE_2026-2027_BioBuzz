package org.firstinspires.ftc.teamcode.subsystem;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Drive {

    private DcMotor motorFrontRight;
    private DcMotor motorFrontLeft;
    private DcMotor motorBackRight;
    private DcMotor motorBackLeft;

    public Drive(String motorFrontRightName, String motorFrontLeftName, String motorBackRightName, String motorBackLeftName)
    {

        motorFrontRight = hardwareMap.get(DcMotor.class, motorFrontRightName);
        motorFrontLeft = hardwareMap.get(DcMotor.class, motorFrontLeftName);
        motorBackRight = hardwareMap.get(DcMotor.class, motorBackRightName;
        motorBackLeft = hardwareMap.get(DcMotor.class, motorBackLeftName);

        motorFrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);


    }
    public void drive(double forward, double right, double rotate) {
        // This calculates the power needed for each wheel based on the amount of forward,
        // strafe right, and rotate
        double frontLeftPower = forward + right + rotate;
        double frontRightPower = forward - right - rotate;
        double backRightPower = forward + right - rotate;
        double backLeftPower = forward - right + rotate;

        double maxSpeed = 1.0;  // make this slower for outreaches


        // We multiply by maxSpeed so that it can be set lower for outreaches
        // When a young child is driving the robot, we may not want to allow full
        // speed.
        motorFrontLeft.setPower(maxSpeed * frontLeftPower);
        motorFrontRight.setPower(maxSpeed * frontRightPower);
        motorBackLeft.setPower(maxSpeed * backLeftPower);
        motorBackRight.setPower(maxSpeed * backRightPower);
    }
}
