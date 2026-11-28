package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Flower {

    private Servo flowerGate;
    private static final double FLOWER_GATE_CLOSED = 0.35;
    private static final double FLOWER_GATE_OPEN = 0;

    double currentFlowerPosition;
    boolean isFlowerGateOpen;
    public Flower (HardwareMap hardwareMap, String flowerIntake) {

        flowerGate = hardwareMap.get(Servo.class, flowerIntake);

        closeFlowerGate();

        currentFlowerPosition = 0;
    }


    public void ToggleFlowerGate() {
        if (isFlowerGateOpen) {
            closeFlowerGate();
        }
        else {
            openFlowerGate();
        }
    }

    public boolean isFlowerGateOpen()
    {
        return isFlowerGateOpen;

    }
    public void openFlowerGate()
    {
        flowerGate.setPosition(FLOWER_GATE_OPEN);
        currentFlowerPosition = FLOWER_GATE_OPEN;
        isFlowerGateOpen = true;
    }

    public void closeFlowerGate()
    {
        flowerGate.setPosition(FLOWER_GATE_CLOSED);
        currentFlowerPosition = FLOWER_GATE_CLOSED;
        isFlowerGateOpen = false;
    }

}

