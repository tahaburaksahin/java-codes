// ELT-DefInter/MyCompar.java
 
@FunctionalInterface
public interface MyCompar<T> {
      // abstract
    boolean lt(T lhs, T rhs);

      // implemented directly in terms of the abstract
    default boolean gt(T lhs, T rhs) {
        return lt(rhs,lhs);
    }
    default boolean ge(T lhs, T rhs) {
        return !lt(lhs,rhs);
    }
    default boolean le(T lhs, T rhs) {
        return !lt(rhs,lhs);
    }
    default boolean eq(T lhs, T rhs) {
        return !lt(lhs,rhs) && !lt(rhs,lhs);
    }
      // implemented indirectly in terms of the abstract
    default boolean ne(T lhs, T rhs) {
        return !eq(lhs,rhs);
    }
}
