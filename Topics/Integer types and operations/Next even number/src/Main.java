import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        ++num;
        if (num % 2 == 0) {
            System.out.println(num);
        } else {
            System.out.println(++num);
        }
    }
}