package teno_intake_sub;
    import androidx.core.app.FrameMetricsAggregator;

    import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.eventloop.opmode.OpMode;
    import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.qualcomm.robotcore.hardware.Servo;
    import com.qualcomm.robotcore.util.ElapsedTime;
    import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Intake_System_Tele", group = "INTAKE TELE")
public class IntakeSystem_TeleOp extends OpMode {
    // Declare OpMode members.
    DcMotor MIM; // Main Intake Motor
    int Power = 0;
    int Direction = 1;
    boolean MIM_On = false;
    boolean PreA = false;
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        MIM = hardwareMap.get(DcMotor.class, "motorIntake");

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
        if (gamepad1.a != PreA){
            PreA = gamepad1.a;
            if (!gamepad1.a){
                if (MIM_On){
                    MIM_On = false;
                    MIM.setPower(0);

                }
                else{
                    MIM_On = true;
                    MIM.setPower(Power);
                }

            }
        }

    }
}

