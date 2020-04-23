package HighConcurrentPrograming.ThreadTest;

public class CASCase {
    public volatile int value;

    public synchronized void add() {
        value++;
    }
}
