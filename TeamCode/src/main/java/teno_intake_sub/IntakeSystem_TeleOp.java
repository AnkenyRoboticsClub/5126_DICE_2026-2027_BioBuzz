package teno_intake_sub;
    import androidx.core.app.FrameMetricsAggregator;

    import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.eventloop.opmode.OpMode;
    import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.qualcomm.robotcore.hardware.Gamepad;
    import com.qualcomm.robotcore.hardware.Servo;
    import com.qualcomm.robotcore.util.ElapsedTime;
    import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Intake_System_Tele", group = "INTAKE TELE")
public class IntakeSystem_TeleOp extends OpMode {
    // Declare OpMode members.
    DcMotor MIM; // Main Intake Motor
    DcMotor MPL;
    DcMotor MNL;

    // Successful Test at .7!
    double LeftLaunch = .7;

    double RightLaunch = .7;
    double Power = 0.8;
    int Direction = 0;
    int loopCount = 0;
    boolean currentX = false;
    boolean previousX = false;
    boolean systemActive = false;


    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        MIM = hardwareMap.get(DcMotor.class, "motorIntake");
        //MPL = hardwareMap.get(DcMotor.class, "pollenLaunch");
        MNL = hardwareMap.get(DcMotor.class, "necterLaunch");
        MPL = hardwareMap.get(DcMotor.class, "pollenLaunch");

        MNL.setDirection(DcMotorSimple.Direction.FORWARD);
        MPL.setDirection(DcMotorSimple.Direction.REVERSE);

        MIM.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        MIM.setPower(Power);
        if (Direction == 1) {
            MIM.setDirection(DcMotorSimple.Direction.FORWARD);
        }else{
            MIM.setDirection(DcMotorSimple.Direction.REVERSE);
        }

    }
    @Override
    public void loop(){

        /*





         */







        if (currentX && !previousX) {

            systemActive = !systemActive;

            currentX = gamepad2.x;

            if (systemActive)
            {
                MIM.setPower(Power);
                telemetry.addData("Motor Status", Power);


            }
            else
            {
                MIM.setPower(0);
                telemetry.addData("Motor Status", "Stopped");
            }



        long updateDelay = 2000; // 2000 = 2 seconds
        long nextPowerUpdate = System.currentTimeMillis();


            telemetry.addData("Loop Count", loopCount++);

            long timeUntilNextPowerUpdate = nextPowerUpdate - System.currentTimeMillis();
            if (timeUntilNextPowerUpdate < 0)
            {
                telemetry.addData("Time Until Next Update", "Now");

                if (gamepad1.leftBumperWasPressed()) {
                    LeftLaunch = Math.max(0.2, LeftLaunch - 0.1);
                    RightLaunch = Math.max(0.2, RightLaunch - 0.1);

                    nextPowerUpdate = System.currentTimeMillis() + updateDelay;
                }

                if (gamepad1.rightBumperWasPressed()) {
                    LeftLaunch = Math.min(1.0, LeftLaunch + 0.1);
                    RightLaunch = Math.min(1.0, RightLaunch + 0.1);

                    nextPowerUpdate = System.currentTimeMillis() + updateDelay;
                }
            }
            else
            {
                telemetry.addData("Time Until Next Update", timeUntilNextPowerUpdate);

            }


            if (gamepad1.left_trigger > .1) {
                MPL.setPower(LeftLaunch);
            }
            else{
                MPL.setPower(0.0);
            }

            if (gamepad1.right_trigger > .1) {
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

//
//        if (gamepad1.a != PreA){
//            PreA = gamepad1.a;
//            if (!gamepad1.a){
//                if (MIM_On){
//                    MIM_On = false;
//                    MIM.setPower(0);
//
//                }
//                else{
//                    MIM_On = true;
//                    MIM.setPower(Power);
//                }
//
//            }
//        }

    }
}

