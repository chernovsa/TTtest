package main.java.sample.thread.done;

/**
 * Created by schernov on 07.02.18.
 */
public class SecondDoneWrapper implements IDoneWrapper {
    private DoneWrapper doneWrapper;

    public SecondDoneWrapper(DoneWrapper doneWrapper) {
        this.doneWrapper = doneWrapper;
    }

    @Override
    public void setDone() {
        doneWrapper.setSecondDone();
    }

    @Override
    public boolean isDone() {
       return doneWrapper.isFirstDone();
    }
}
