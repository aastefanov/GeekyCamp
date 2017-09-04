import java.util.Scanner;

public class WildcardMatcher {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String s = scanner.next();
            String p = scanner.next();
            System.out.println(checkMatch(s, p));
        }
    }

    static boolean checkMatch(String s, String p) {
        char[] s_array = s.toCharArray(), p_array = p.toCharArray();

        // k - change when wildcard asterisk
        int i = 0, j = 0, k = 0;
        try {
            for (; ; ) {
                if (j == p_array.length - 1 && p_array[j] == '*') return true;

                if (j == p_array.length) return true;

                if (s_array[i + k] != p_array[j] && p_array[j] != '?' && p_array[j] != '*') {
                    j = k = 0;
                    ++i;
                    continue;
                }

                if (p_array[j] == '?' || p_array[j] == s_array[i + k]) {
                    ++k;
                    ++j;
                    continue;
                }
                if (p_array[j] == '*') {
                    for (; ; ) {
                        if (j > p_array.length - 1) return true;
                        if (p_array[j + 1] == '*') {
                            ++j;
                            continue;
                        }

                        if(p_array[j+1] == '?') {
                            ++k;
                            ++j;
                            break;
                        }

                        if (s_array[i + k] != p_array[j + 1]) {
                            k = s.lastIndexOf(p_array[j+1]) - i;
                            continue;
                        } else {
                            ++j;
                            break;
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

    }
}
