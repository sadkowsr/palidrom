import java.util.HashMap;
import java.util.Map;

public class Main {

    public static int countDisjointPalindromes(String S) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : S.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        int count = 0;

        while (true) {
            Character outer = null;
            Character middle = null;

            for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
                if (entry.getValue() >= 2) {
                    outer = entry.getKey();
                    break;
                }
            }

            if (outer == null) break;

            for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
                if (!entry.getKey().equals(outer) && entry.getValue() >= 1) {
                    middle = entry.getKey();
                    break;
                }
            }

            if (middle == null) break;

            freq.put(outer, freq.get(outer) - 2);
            freq.put(middle, freq.get(middle) - 1);

            count++;
        }

        return count;
    }

     public static int countPalindromesByBruteForce(String S) {
        int n = S.length();
        boolean[] used = new boolean[n]; // śledzi, które znaki zostały już użyte
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (used[i]) continue;

            for (int j = i + 1; j < n; j++) {
                if (used[j]) continue;

                for (int k = j + 1; k < n; k++) {
                    if (used[k]) continue;

                    char a = S.charAt(i);
                    char b = S.charAt(j);
                    char c = S.charAt(k);

                    if (a == c && a != b) {
                        // znaleziono palindrom: a b a
                        used[i] = true;
                        used[j] = true;
                        used[k] = true;
                        count++;
                        // wychodzimy z trójek – nie można użyć tych indeksów ponownie
                        i = -1; // restartuj główną pętlę
                        break;
                    }
                }

                if (i == -1) break;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String input = "aaabc";
        int result = countPalindromesByBruteForce(input);
        System.out.println("Maksymalna liczba palindromów: " + result); // wynik: 1
    }

    public static void main(String[] args) {
        String input = "aba";
        int result = countDisjointPalindromes(input);
        int result2 = countPalindromesByBruteForce(input);
        System.out.println("Max number palidrom: " + result); // result: 1
    }
}
