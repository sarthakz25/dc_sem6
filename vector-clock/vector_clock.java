package exp8;

public class vector_clock {
    // initializing vector clock for each process
    private final int[] p1clock = new int[3];
    private final int[] p2clock = new int[3];
    private final int[] p3clock = new int[3];

    private String displayClock(int[] clock) {
        return "[" + clock[0] + "," + clock[1] + "," + clock[2] + "]";
    }

    private void displayVCs() {
        System.out.println("P1:" + displayClock(p1clock) + " P2:" + displayClock(p2clock) + " P3:" + displayClock(p3clock));
    }

    private void displayInitialVC() {
        System.out.println("initial vector clocks:");
        displayVCs();
    }

    private void processP1SendsToP2() {
        p1clock[0]++;
        System.out.println("after P1 sends message to P2:");
        displayVCs();
    }

    private void processP2ReceivesFromP1() {
        p2clock[0] = p1clock[0];
        p2clock[1]++;
        System.out.println("after P2 receives message from P1:");
        displayVCs();
    }

    private void processP3SendsToP1andP2() {
        p3clock[2]+=2;
        System.out.println("after P3 sends message to P1 and P2:");
        displayVCs();
    }

    private void processP1andP2ReceiveFromP3() {
        // P1 updates clock based on P3s message
        p1clock[0] = Math.max(p1clock[0], p3clock[0]) + 1; // self increments as well
        p1clock[2] = p3clock[2];

        // P2 updates clock based on P3s message
        p2clock[1] = Math.max(p2clock[1], p3clock[1]) + 1; // self increments as well
        p2clock[2] = p3clock[2];

        System.out.println("after P1 and P2 receive message from P3:");
        displayVCs();
    }

    public static void main(String[] args) {
        vector_clock vc = new vector_clock();

        vc.displayInitialVC();
        vc.processP1SendsToP2();
        vc.processP2ReceivesFromP1();
        vc.processP3SendsToP1andP2();
        vc.processP1andP2ReceiveFromP3();
    }
}
