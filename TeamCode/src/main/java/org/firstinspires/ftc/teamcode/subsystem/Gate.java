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

    private Servo pollenGate;

    // Constructor (needs the name of the nectar and pollen gates)
    public Gate(HardwareMap hardwareMap, String nectarGateName, String pollenGateName) {

        // Initialize the servos
        nectarGate = hardwareMap.get(Servo.class, nectarGateName);
        pollenGate = hardwareMap.get(Servo.class, pollenGateName);

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
        nectarGate.setPosition(0.0);
    }

    public void closeNectGate() {
        // CLose gate code goes herea
        nectarGate.setPosition(.8);
    }

    public void openPolyGate() {

        pollenGate.setPosition(.8);
    }

    public void closePolyGate() {

        pollenGate.setPosition(0.0);

    }

}
