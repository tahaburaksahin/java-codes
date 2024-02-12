// ELT-DefInter/MyComparMain.java
 
public class MyComparMain {

    static class CompDigits implements MyCompar<Integer> {
        @Override
        public boolean lt(Integer lhs, Integer rhs) {
            return sumOfDigs(lhs) < sumOfDigs(rhs);
        }
        private static int sumOfDigs(int n) {
            int sum = 0;
            n = n < 0 ? -n : n;
            while (n != 0) {
                sum += n % 10;
                n /= 10;
            }
            return sum;
        }
    }

    public static void main (String[] args) {

          // object of "normal" class
        MyCompar<Integer> cmpInt1 = new CompDigits();

          // object of anonymous class
        MyCompar<String> cmpStr1 = new MyCompar<String>() {
            @Override
            public boolean lt(String lhs, String rhs) {
                return lhs.length() < rhs.length();
            }
        };

         // lambda
        MyCompar<String> cmpStr2 =
                (lhs,rhs) -> lhs.compareTo(rhs) < 0;

         // lambda for Objects
        MyCompar<Object> cmpObj1 =
            (lhs,rhs) -> lhs.hashCode() < rhs.hashCode();

        compare("cmpInt1 - BY SUM OF DIGITS",cmpInt1,
                10,2, 3,12, 5,22);
        compare("cmpStr1 - BY LENGTH",cmpStr1,
                "Alice","John", "Sue","Joe", "Lea","Mike");
        compare("cmpStr2 - LEXICOGRAPHICALLY",cmpStr2,
                "Alice","Ben", "Sue","Sue", "Zoe","Mat");
        compare("cmpObj1 - BY HASH CODE AS OBJECTS",cmpObj1,
                "Alice","Ben", "Sue","Sue", "Zoe","Mat");
          // lambda ad hoc
        compare("lambda  - BY LENGTH REVERSED",
                (lhs,rhs) -> lhs.length() > rhs.length(),
                "Alice","John", "Sue","Joe", "Lea","Mike");
    }

    @SafeVarargs
    private static <T> void compare(String message,
                    MyCompar<? super T> cmp, T... pairs) {
        System.out.println("\n========= " + message);
        for (int k = 0; k < pairs.length; k += 2) {
            T a = pairs[k], b = pairs[k+1];
            System.out.println("** (" + a + "," + b + "): "+
                    "lt->" + cmp.lt(a,b) + ", " +
                    "le->" + cmp.le(a,b) + "\n" +
                "    gt->" + cmp.gt(a,b) + ", " +
                    "ge->" + cmp.ge(a,b) + ", " +
                    "eq->" + cmp.eq(a,b) + ", " +
                    "ne->" + cmp.ne(a,b) );
        }
    }
}
