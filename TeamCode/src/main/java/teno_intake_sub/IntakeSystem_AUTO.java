package teno_intake_sub;
    import androidx.core.app.FrameMetricsAggregator;

    import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.eventloop.opmode.OpMode;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.qualcomm.robotcore.hardware.Servo;
    import com.qualcomm.robotcore.util.ElapsedTime;
    import com.qualcomm.robotcore.util.Range;



@Autonomous(name = "Intake_System", group = "INTAKE TEST")
public class IntakeSystem_AUTO extends LinearOpMode {
    // Declare OpMode members.
    DcMotor MIM; // Main Intake Motor
    int Power = 0;

    int Direction = 0;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        MIM = hardwareMap.get(DcMotor.class, "motorIntake");

        MIM.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        MIM.setPower(Power);
        MIM.setDirection(DcMotorSimple.Direction.FORWARD);
    }
}
