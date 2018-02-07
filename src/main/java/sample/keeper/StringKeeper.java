package main.java.sample.keeper;

/**
 * Created by s.chernov on 07.02.2018.
 */
public class StringKeeper {
    public StringKeeper() {
        this.sb=new StringBuffer();
    }

    private StringBuffer sb;
    public void appendInt(int value){
        sb.append(Integer.toString(value));
    }
    public void clearBuffer(){
        sb.delete(0,sb.length());
    }
    public String getString(){
        return sb.toString();
    }
}
