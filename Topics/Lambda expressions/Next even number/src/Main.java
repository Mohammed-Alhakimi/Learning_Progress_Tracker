import java.util.function.LongUnaryOperator;

class Operator {

    public static LongUnaryOperator unaryOperator = number -> {
        long number2 = ++number;
        if (number2 % 2 == 0) {
            return number2;
        }
        else {
            return ++number2;
        }
    };
}
