import java.util.*;

class MapFunctions {

    public static void calcTheSamePairs(Map<String, String> map1, Map<String, String> map2) {
        int samePairs = 0;
        for (String s : map1.keySet()
        ) {
            for (String s2 : map2.keySet()
            ) {
                if (s.equals(s2) && Objects.equals(map1.get(s), map2.get(s2))) {
                    samePairs++;
                }
            }
        }
        System.out.print(samePairs);
    }
}