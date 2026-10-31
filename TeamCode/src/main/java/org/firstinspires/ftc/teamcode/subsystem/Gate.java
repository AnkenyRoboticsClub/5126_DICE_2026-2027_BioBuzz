package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Gate {

    // Motors
    // None

    // Servos

    private Servo nectarGate;

    boolean isNectarGateOpen;
    boolean isPollenGateOpen;

    private Servo ponGate;

    // Constructor (needs the name of the nectar and pollen gates)
    public Gate(HardwareMap hardwareMap, String nectarGateName, String pollenGateName) {

        // Initialize the servos
        nectarGate = hardwareMap.get(Servo.class, nectarGateName);
        ponGate = hardwareMap.get(Servo.class, pollenGateName);

        isNectarGateOpen = false;
        isPollenGateOpen = false;

    }

    public void toggleNectarGate() {
        if (isNectarGateOpen) {
            closeNectGate();
            isNectarGateOpen = false;
        }
        else {
            openNectGate();
            isNectarGateOpen = true;
        }
    }

    public void togglePollenGate() {
        if (isPollenGateOpen) {
            closePolyGate();
            isPollenGateOpen = false;
        }
        else {
            openPolyGate();
            isPollenGateOpen = true;
        }
    }

    public void openNectGate() {
        // Open gate code goes here
        ponGate.setPosition(0.0);
    }

    public void closeNectGate() {
        // CLose gate code goes here
        ponGate.setPosition(1.0);
    }

    public void openPolyGate() {

        nectarGate.setPosition(1.0);
    }

    public void closePolyGate() {

        nectarGate.setPosition(0.0);

    }








    //
//    private DcMotor leftDrive = null;
//
//    private DcMotor rightDrive = null;
//
//
//    @Override
//    public void init() {
//        telemetry.addData("Status", "Initialized");
//
//        // Initialize the hardware variables. Note that the strings used here as parameters
//        // to 'get' must correspond to the names assigned during the robot configuration
//        // step (using the FTC Robot Controller app on the phone).
////        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
////        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
//
//        necterGate = hardwareMap.get(Servo.class,"necterGate");
//
//        polynGate = hardwareMap.get(Servo.class,"pollenGate");
//
//
//        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
//        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
//        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
////        leftDrive.setDirection(DcMotor.Direction.REVERSE);
////        rightDrive.setDirection(DcMotor.Direction.FORWARD);
//
//        // Tell the driver that initialization is complete.
//        telemetry.addData("Status", "Initialized");
//    }
//
//    /*
//     * Code to run REPEATEDLY after the driver hits INIT, but before they hit START
//     */
//    @Override
//    public void init_loop() {
//    }
//
//    /*
//     * Code to run ONCE when the driver hits START
//     */
//    @Override
//    public void start() {
//        runtime.reset();
//    }
//
//    /*
//     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
//     */
//    @Override
//    public void loop() {
//
//        double leftPower;
//        double rightPower;
//
//        double drive = -gamepad1.left_stick_y;
//        double turn = gamepad1.right_stick_x;
//        leftPower = Range.clip(drive + turn, -1.0, 1.0);
//        rightPower = Range.clip(drive - turn, -1.0, 1.0);
//
//        gateopenonX();
//        gateopenonB();
//
//        telemetry.update();
//
//
//        // Show the elapsed game time and wheel power.
//        telemetry.addData("Status", "Run Time: " + runtime.toString());
//        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
//    }
//
//    /*
//     * Code to run ONCE after the driver hits STOP
//     */
//    @Override
//    public void stop() {
//    }
//
//    private boolean @Override
//    public void init() {
//        telemetry.addData("Status", "Initialized");
//
//        // Initialize the hardware variables. Note that the strings used here as parameters
//        // to 'get' must correspond to the names assigned during the robot configuration
//        // step (using the FTC Robot Controller app on the phone).
////        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
////        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
//
//        necterGate = hardwareMap.get(Servo.class,"necterGate");
//
//        polynGate = hardwareMap.get(Servo.class,"pollenGate");
//
//
//        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
//        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
//        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
////        leftDrive.setDirection(DcMotor.Direction.REVERSE);
////        rightDrive.setDirection(DcMotor.Direction.FORWARD);
//
//        // Tell the driver that initialization is complete.
//        telemetry.addData("Status", "Initialized");
//    }
//
//    /*
//     * Code to run REPEATEDLY after the driver hits INIT, but before they hit START
//     */
//    @Override
//    public void init_loop() {
//    }
//
//    /*
//     * Code to run ONCE when the driver hits START
//     */
//    @Override
//    public void start() {
//        runtime.reset();
//    }
//
//    /*
//     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
//     */
//    @Override
//    public void loop() {
//        // Setup a variable for each drive wheel to save power level for telemetry
//        double leftPower;
//        double rightPower;
//
//        // Choose to drive using either Tank Mode, or POV Mode
//        // Comment out the method that's not used.  The default below is POV.
//
//        // POV Mode uses left stick to go forward, and right stick to turn.
//        // - This uses basic math to combine motions and is easier to drive straight.
//        double drive = -gamepad1.left_stick_y;
//        double turn = gamepad1.right_stick_x;
//        leftPower = Range.clip(drive + turn, -1.0, 1.0);
//        rightPower = Range.clip(drive - turn, -1.0, 1.0);
//
//        // Tank Mode uses one stick to control each wheel.
//        // - This requires no math, but it is hard to drive forward slowly and keep straight.
//        // leftPower  = -gamepad1.left_stick_y ;
//        // rightPower = -gamepad1.right_stick_y ;
//
//        // Send calculated power to wheels
////        leftDrive.setPower(leftPower);
////        rightDrive.setPower(rightPower);
//
//
//        // Next steps: Choose a controller button that will open adn close gates
//        gateopenonX();
//        gateopenonB();
//
//        telemetry.update();
//
//        // Example code, check if ready to fire
////        boolean areWeReadyToFire = inFiringPosition();
//
//        // If ready to fire, open both nectar and pollen gates
////        if (areWeReadyToFire) {
////            // nectarGateServo.setPosition(OPEN_POSITION);
////            // pollenGateServo.setPosition(OPEN_POSITION);
////        }
//
//
//        // Show the elapsed game time and wheel power.
//        telemetry.addData("Status", "Run Time: " + runtime.toString());
//        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
//    }
//
//    /*
//     * Code to run ONCE after the driver hits STOP
//     */
//    @Override
//    public void stop() {
//    }
//
//    private boolean inFiringPosition() {
//        // Start of method code
//
//        // Example: Limelight calculations go here
//
//        // Determine if ready to fire
//
//        // For this example, always return true
//        boolean isReadyToFire = true;
//
//
//        return isReadyToFire;
//        // End of method code
//    }
//
//    private void variableExample() {
//        // Boolean = true or false
//        boolean isTrue = true;
//        boolean isNotTrue = false;
//
//        // double = any decimal point number (also whole numbers)
//        double decimalNumber = 1.2345;
//        double negativeDecimalNumber = -0.5002;
//        double wholeNumber = 1;
//        double zeroNumber = 0;
//
//
//    }
//
//
//
//    private void gateopenonB() {
//        boolean TRP = gamepad2.right_trigger_pressed;
//
//        telemetry.addData("Is RT Pressed", TRP);
//
//
//        if (TRP) {
//            // will need to test to figure out position
//            sleep(1000);
//            necterGate.setPosition(1.0);
//        } else {
//            necterGate.setPosition(0.0);
//        }
//    }
//
//
//
//
//
//
//    private void gateopenonX()
//    {
//        boolean TLP = gamepad2.left_trigger_pressed;
//
//        telemetry.addData("Is X Pressed", TLP);
//
//        if (TLP) {
//            // will need to test to figure out position
//            sleep(1000);
//
//
//        } else {
//            polynGate.setPosition(1.0);
//        }
//
//        boolean isReadyToFire = true;
//
//
//        return isReadyToFire;
//        // End of method code
//    }
//
//    private void variableExample() {
//        // Boolean = true or false
//        boolean isTrue = true;
//        boolean isNotTrue = false;
//
//        // double = any decimal point number (also whole numbers)
//        double decimalNumber = 1.2345;
//        double negativeDecimalNumber = -0.5002;
//        double wholeNumber = 1;
//        double zeroNumber = 0;
//
//
//    }
//
//    private void controllerExample()
//    {
//        boolean RTP = gamepad2.right_trigger_pressed;
//
//        if (isButtonAPressed) {
//            // Do action 1
//            // Do action 2
//            // Do action 3
//            // .....
//            // Do action x
//        }
//
//        // Gamepad sticks give you a number from -1 to 1
//        // If the stick is in neutral position, it returns 0
//        double leftStickYValue = gamepad1.left_stick_y;
//
//    }
//
//
//
//    private void gateopenonB() {
//        boolean isbuttonBpressed = gamepad1.b;
//
//        telemetry.addData("Is B Pressed", isbuttonBpressed);
//
//
//        if (RTP) {
//            // will need to test to figure out position
//            wait(1000);
//            necterGate.setPosition(1.0);
//        } else {
//            necterGate.setPosition(0.0);
//        }
//    }
//
//
//
//
//
//
//    private void gateopenonX() {
//        boolean isbuttonXpressed = gamepad1.x;
//
//        telemetry.addData("Is X Pressed", isbuttonXpressed);
//
//        if (isbuttonXpressed) {
//            // will need to test to figure out position
//            wait(1000);
//            polynGate.setPosition(0.0);
//
//        } else {
//            polynGate.setPosition(1.0);
//        }
//
//
//    }

}
