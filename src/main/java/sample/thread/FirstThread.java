package main.java.sample.thread;

import main.java.sample.thread.done.IDoneWrapper;
import main.java.sample.keeper.StringKeeper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by schernov on 07.02.18.
 */
public class FirstThread extends IntThread{


    public FirstThread(int value, int iterations, StringKeeper keeper, Lock lock, Condition waitCondition, Condition signalCondition, IDoneWrapper doneWrapper) {
        super(value, iterations, keeper, lock, waitCondition, signalCondition, doneWrapper);
    }

    void runMethod(){
        lock.lock();
        try {
            while (!doneWrapper.isDone())
                waitCondition.await();
            doWork();
            doneWrapper.setDone();
            signalCondition.signal();
        }
        catch(InterruptedException  e)
        {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

}
