import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

class Main {
    public static void main(String[] args) {
        Deque<Integer> stk = new ArrayDeque<>();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        while (n-- > 0) {
            switch (s.next()) {
                case "push": int i = s.nextInt();
                    stk.push(stk.isEmpty() || i >= stk.peek() ? i : stk.peek());
                    break;
                case "pop": stk.pop();
                    break;
                default: System.out.println(stk.peek());
            }
        }
    }
}