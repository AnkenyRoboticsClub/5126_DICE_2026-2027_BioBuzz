package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Gate {

    private static final double POLLEN_GATE_CLOSED = 0;
    private static final double POLLEN_GATE_OPEN = 0.35;
    private static final double NECTAR_GATE_CLOSED = 0.35;
    private static final double NECTAR_GATE_OPEN = 0;


    private Servo nectarGate;

    boolean isNectarGateOpen;
    boolean isPollenGateOpen;

    double currentNectarPosition;
    double currentPollenPosition;

    private Servo pollenGate;


    // Constructor (needs the name of the nectar and pollen gates)
    public Gate(HardwareMap hardwareMap, String nectarGateName, String pollenGateName) {

        // Initialize the servos
        nectarGate = hardwareMap.get(Servo.class, nectarGateName);
        pollenGate = hardwareMap.get(Servo.class, pollenGateName);

        closeNectGate();
        closePolyGate();

        currentNectarPosition = 0;
        currentPollenPosition = 0;

    }

    public void toggleNectarGate() {
        if (isNectarGateOpen) {
            closeNectGate();
        }
        else {
            openNectGate();
        }
    }

    public void togglePollenGate() {
        if (isPollenGateOpen) {
            closePolyGate();
        }
        else {
            openPolyGate();
        }

    }

    public boolean isPollenGateOpen()
    {
        return isPollenGateOpen;
    }

    public boolean isNectarGateOpen()
    {
        return isNectarGateOpen;
    }

    public void openNectGate() {
        // Open gate code goes here
        nectarGate.setPosition(NECTAR_GATE_OPEN);
        currentNectarPosition = NECTAR_GATE_OPEN;
        isNectarGateOpen = true;
    }

    public void closeNectGate() {
        // CLose gate code goes herea
        nectarGate.setPosition(NECTAR_GATE_CLOSED);
        currentNectarPosition = NECTAR_GATE_CLOSED;
        isNectarGateOpen = false;
    }

    public void openPolyGate() {

        pollenGate.setPosition(POLLEN_GATE_OPEN);
        currentPollenPosition = POLLEN_GATE_OPEN;
        isPollenGateOpen = true;
    }

    public void closePolyGate() {

        pollenGate.setPosition(POLLEN_GATE_CLOSED);
        currentPollenPosition = POLLEN_GATE_CLOSED;
        isPollenGateOpen = false;
    }

    public double getPollenGatePosition()
    {
        return currentPollenPosition;
    }

    public double getNectarGatePosition()
    {
        return currentNectarPosition;
    }

}
