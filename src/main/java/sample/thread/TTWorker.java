package sample.thread;

import sample.thread.done.FirstDoneWrapper;
import sample.thread.done.IDoneWrapper;
import sample.thread.done.SecondDoneWrapper;
import sample.keeper.StringKeeper;
import sample.thread.done.DoneWrapper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by s.chernov on 07.02.2018.
 */
public class TTWorker {
    private  boolean debug=false;
    private StringKeeper keeper;
    IntThread firstThread;
    IntThread secondThread;

    public TTWorker(StringKeeper keeper) {
        this.keeper = keeper;
        this.firstThread=null;
        this.secondThread=null;
    }
    public TTWorker(StringKeeper keeper,boolean debug)
    {
        this.keeper=keeper;
        this.debug=debug;
    }

    public void startWork()
    {
        final int iterations=10;
        final int firstInt=1;
        final int secondInt=2;
        final Lock lock=new ReentrantLock();
        final Condition firstCondition=lock.newCondition();
        final Condition secondCondition=lock.newCondition();
        DoneWrapper doneWrapper=new DoneWrapper(false,true);
        IDoneWrapper firstDone=new FirstDoneWrapper(doneWrapper);
        IDoneWrapper secondDone=new SecondDoneWrapper(doneWrapper);
        firstThread=new FirstThread(firstInt,iterations, keeper, lock, firstCondition, secondCondition,firstDone);
        Thread thread1=new Thread(firstThread);
        secondThread=new SecondThread(secondInt,iterations, keeper, lock, secondCondition, firstCondition,secondDone);
        Thread thread2 = new Thread(secondThread);
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread1.start();
        thread2.start();

        if (debug)
            for(int i=0;i<100;i++) {
                System.out.println("string= "+keeper.getString());
            }
    }
    public void stopWork(){
        firstThread.stopThread();
        secondThread.stopThread();
    }
    public static void main(String[] args) {
    TTWorker TTWorker = new TTWorker(new StringKeeper(),true);
    }

}
