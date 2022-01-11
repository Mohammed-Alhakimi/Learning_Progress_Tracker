import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] array = scanner.nextLine().replaceAll("\\s+","").split("");
        StringBuilder sb = new StringBuilder();
        Arrays.stream(array).forEach(sb::append);
        System.out.println(Integer.valueOf(sb.reverse().toString()));
    }
}