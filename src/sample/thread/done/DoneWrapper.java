package sample.thread.done;

/**
 * Created by schernov on 07.02.18.
 */
public class DoneWrapper {
    private boolean firstDone;
    private boolean secondDone;

    public DoneWrapper(boolean firstDone, boolean secondDone) {
        this.firstDone = firstDone;
        this.secondDone = secondDone;
    }
    void setFirstDone(){
        firstDone=true;
        secondDone =false;
    }
    void setSecondDone()
    {
        secondDone =true;
        firstDone=false;
    }
    boolean isFirstDone()
    {
        return firstDone;
    }
    boolean isSecondDone()
    {
        return secondDone;
    }

}
