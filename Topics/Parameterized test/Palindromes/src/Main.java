import java.util.Locale;

class StringUtils {
    public static boolean isPalindrome(String str) {
        str = str.replaceAll(" ", "").toLowerCase(Locale.ROOT);
        str = str.replaceAll("'", "");
        int middle = str.length() / 2;
        int j = middle;
        boolean palindrome = true;
        if (str.isEmpty()) {
            return false;
        }
        if (str.length() % 2 == 0) {
            for (int i = middle - 1; i >= 0; i--) {
                if (str.charAt(i) != str.charAt(j)) {
                    palindrome = false;
                }
                j++;
            }
            return palindrome;
        } else {
            for (int i = middle - 1; i >= 0; i--) {
                if (str.charAt(i) != str.charAt(j + 1)) {
                    palindrome = false;
                }
                j++;
            }
            return palindrome;
        }
    }
}
