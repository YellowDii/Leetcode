package CoreJavaValume1;

import java.io.Serializable;

public class Interval<T extends Comparable & Serializable> implements Serializable{
    private T lower;
    private T upper;
    //...
    public Interval(T first,T upper){
        //...
    }
}
