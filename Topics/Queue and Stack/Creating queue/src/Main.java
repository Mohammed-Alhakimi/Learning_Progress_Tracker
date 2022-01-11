import java.util.*;

public class Main {

    public static void main(String[] args) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(2);
        q.offer(0);
        q.offer(1);
        q.offer(7);
        System.out.println(q);
    }
}