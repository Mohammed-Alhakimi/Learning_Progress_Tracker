import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Utils {

    public static List<Integer> sortOddEven(List<Integer> numbers) {
        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
        numbers.forEach(num -> {
            if (num % 2 == 0) {
                even.add(num);
            } else {
                odd.add(num);
            }
        });
        numbers.clear();
        even.sort(Comparator.reverseOrder());
        odd.sort(Comparator.naturalOrder());
        numbers.addAll(odd);
        numbers.addAll(even);
        return numbers;
    }
}