// ELL-InterFun/InterFun.java
 
public class InterFun {
    public static void main(String[] args) {
        double[] a = {-Math.PI/6, Math.PI/6, 7*Math.PI/6};
        double[] r = Fun.transformArray(a, new Sin());
        double[] s = Fun.transformArray(r, new MultBy2());
        System.out.println(java.util.Arrays.toString(s));
    }

}

@FunctionalInterface
interface Fun {
    double apply(double d);

    static double[] transformArray(double[] arr, Fun f) {
        double[] res = new double[arr.length];
        for (int i = 0; i < arr.length; ++i)
            res[i] = f.apply(arr[i]);
        return res;
    }
}

class Sin implements Fun {
    @Override
    public double apply(double d) { return Math.sin(d); }
}

class MultBy2 implements Fun {
    @Override
    public double apply(double d) { return 2*d; }
}
