package weihan_launcher_sub;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@Autonomous(name = "AUTO_LAUNCHERS", group = "PROTO")
public class LaunchSystem_AUTO extends LinearOpMode {

    DcMotor PL;
    DcMotor NL;


    @Override
    public void runOpMode() {

        PL = hardwareMap.get(DcMotor.class, "pollenLaunch");
        NL = hardwareMap.get(DcMotor.class, "necterLaunch");

        PL.setDirection(DcMotorSimple.Direction.FORWARD);
        NL.setDirection(DcMotorSimple.Direction.FORWARD);


        waitForStart();

        while (opModeIsActive()) {

            NL.setPower(0.2);
            PL.setPower(0.2);

            sleep(3000);


        }

    }


}