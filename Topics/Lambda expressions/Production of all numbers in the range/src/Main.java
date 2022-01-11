import java.util.function.*;

class Operator {

    public static LongBinaryOperator binaryOperator = (start, end) -> {
        if (start == end) {
            return start;
        }
        long count = 1;
        for (long i = start; i <= end; i++) {
            count *= i;
        }
        return count;
    };
}