package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Intake {
    private DcMotor intakeMotor;
    // Constructor

    public Intake(String motorIntake) {
        intakeMotor = hardwareMap.get(DcMotor.class,"motorIntake");

        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    // Methods (what can this subsystem do)

    public void startIntake()
    {
        intakeMotor.setPower(1);
    }




    public void stopIntake()
    {
        intakeMotor.setPower(0);
    }

}


//@TeleOp(name = "Intake_System_Tele", group = "INTAKE TELE")
//public class IntakeSystem_TeleOp extends OpMode {
//    // Declare OpMode members.
//    DcMotor MIM; // Main Intake Motor
//    int Power = 0;
//    int Direction = 1;
//    boolean MIM_On = false;
//    boolean PreA = false;
//    @Override
//    public void init() {
//        telemetry.addData("Status", "Initialized");
//
//        MIM = hardwareMap.get(DcMotor.class, "MIM");
//
//        MIM.setDirection(DcMotor.Direction.REVERSE);
//
//        telemetry.addData("Status", "Initialized");
//        MIM.setPower(Power);
//        if (Direction == 1) {
//            MIM.setDirection(DcMotorSimple.Direction.FORWARD);
//        }else{
//            MIM.setDirection(DcMotorSimple.Direction.REVERSE);
//        }
//
//    }
//    @Override
//    public void loop(){
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
//
//    }
//}
//