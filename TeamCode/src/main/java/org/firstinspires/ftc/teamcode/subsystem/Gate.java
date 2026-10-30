package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;

public class Gate {
    private Servo necterGate;

    private Servo polynGate;


    public Gate(String nectarGateName, String pollenGateName) {
        necterGate = hardwareMap.get(Servo.class, nectarGateName);
        polynGate = hardwareMap.get(Servo.class,pollenGateName);
    }

    public void closePollenGate() {
        polynGate.setPosition(1.0);
    }

    public void openPollenGate()
    {
        // will need to test to figure out position
        polynGate.setPosition(0.0);


    }

    public void closeNectarGate() {
        necterGate.setPosition(1.0);
    }

    public void openNectarGate()
    {
        // will need to test to figure out position
        necterGate.setPosition(0.0);
    }

}
