class Predicate {
    public static final TernaryIntPredicate ALL_DIFFERENT = (i, j, k) -> i != j && j != k && i != k;

    @FunctionalInterface
    public interface TernaryIntPredicate {
        boolean test(int i, int j, int k);
    }
}