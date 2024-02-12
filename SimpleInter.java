// ELK-SimpleInter/SimpleInter.java
 
import java.util.Arrays;

interface Transformation {
    String transform(String arg);
}

class Reverse implements Transformation {
    @Override
    public String transform(String s) {
        char[] a = s.toCharArray();
        for (int i = 0, j = s.length()-1; i < j; ++i, --j) {
            char c = a[i];
            a[i] = a[j];
            a[j] = c;
        }
        return new String(a);
    }
}

public class SimpleInter {
    private static void transArray(String[] array,
                                   Transformation t) {
        for (int i = 0; i < array.length; ++i)
            array[i] = t.transform(array[i]);
    }

    public static void main (String[] args) {
        String[] arr = {"Mary", "Alice", "Janet", "Rachel"};
        System.out.println(Arrays.toString(arr));

          //object of a class
        transArray(arr, new Reverse());
        System.out.println(Arrays.toString(arr));

          // object of an anonymous class
        transArray(arr,
                   new Transformation() {
                       @Override
                       public String transform(String s) {
                           return s.toUpperCase();
                       }
                   });
        System.out.println(Arrays.toString(arr));

          // lambda
        transArray(arr, s -> "" + s.charAt(0));
        System.out.println(Arrays.toString(arr));
    }
}
