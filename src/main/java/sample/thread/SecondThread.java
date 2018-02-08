package sample.thread;

import sample.thread.done.IDoneWrapper;
import sample.keeper.StringKeeper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by schernov on 07.02.18.
 */
public class SecondThread extends IntThread {

    public SecondThread(int value, int iterations, StringKeeper keeper, Lock lock, Condition waitCondition, Condition signalCondition, IDoneWrapper doneWrapper) {
        super(value, iterations, keeper, lock, waitCondition, signalCondition, doneWrapper);
    }
}