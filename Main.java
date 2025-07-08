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

    public static void main(String[] args) {
        String input = "aba";
        int result = countDisjointPalindromes(input);
        System.out.println("Max number palidrom: " + result); // result: 1
    }
}
