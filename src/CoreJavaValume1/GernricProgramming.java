package CoreJavaValume1;

import java.util.function.Supplier;

public class GernricProgramming {
    //泛型没出来前的ArrayList
//    public class ArryList{
//        private Object[] elementData;
//        ...
//        public Object get(int i){...}
//        public void add(Object o){...}
//    }
    public static <T> Pair<T> makePair(Supplier<T> constr){
        return new Pair<>(constr.get(),constr.get());
    }
    public static <T> Pair<T> makePair(Class<T> c1){
        try{
            return new Pair<>(c1.newInstance(),c1.newInstance());
        }catch (Exception e){
            return null;
        }
    }
    public static void main(String[] args) {

    }
}
