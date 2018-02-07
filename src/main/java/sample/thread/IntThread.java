package sample.thread;

import sample.thread.done.IDoneWrapper;
import sample.keeper.StringKeeper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by s.chernov on 07.02.2018.
 */
public abstract class IntThread implements Runnable {
    private int value;
    private int iterations;
    private StringKeeper keeper;
    Lock lock;
    Condition signalCondition;
    Condition waitCondition;
    IDoneWrapper doneWrapper;
    boolean work=true;
    public void stopThread(){
        work=false;
    }
    public IntThread(int value, int iterations, StringKeeper keeper, Lock lock, Condition waitCondition, Condition signalCondition, IDoneWrapper doneWrapper) {
        this.value = value;
        this.iterations = iterations;
        this.keeper = keeper;
        this.lock = lock;
        this.waitCondition = waitCondition;
        this.signalCondition = signalCondition;
        this.doneWrapper=doneWrapper;
    }

    @Override
    public void run() {
        while (work) {
          runMethod();
        }
    }
  abstract   void runMethod();

   final protected void doWork()
    {
        for (int i = 0; i < iterations; i++) {
            keeper.appendInt(value);
        }
    }
}


