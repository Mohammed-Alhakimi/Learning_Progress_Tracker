import java.util.ArrayDeque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<String> deque = new ArrayDeque<>();
        int numberOfLines = scanner.nextInt();
        scanner.nextLine();
        scanner.reset();
        for (int i = 0; i < numberOfLines; i++) {
            deque.addLast(scanner.nextLine());
        }
        while (!deque.isEmpty()) {
            System.out.println(deque.pollLast());
        }
    }
}