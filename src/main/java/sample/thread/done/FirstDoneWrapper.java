package sample.thread.done;

/**
 * Created by schernov on 07.02.18.
 */
public class FirstDoneWrapper implements IDoneWrapper {
    private DoneWrapper doneWrapper;

    public FirstDoneWrapper(DoneWrapper doneWrapper) {
        this.doneWrapper = doneWrapper;
    }

    @Override
    public void setDone() {
        doneWrapper.setFirstDone();
    }

    @Override
    public boolean isDone() {
       return doneWrapper.isSecondDone();
    }
}
