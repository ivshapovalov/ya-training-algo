package algo.c1.hw5;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27794/problems/H/
Подстрока
*/
public class TaskH extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input =
                    Arrays.stream(reader.readLine().trim().split(" "))
                            .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int k = input[1];
            String s = reader.readLine().trim();
            Map<Character, Integer> freq = new HashMap<>();
            int end = 0;
            for (end = 0; end < s.length(); end++) {
                char cur = s.charAt(end);
                int prev = freq.getOrDefault(cur, 0);
                if (prev == k) {
                    break;
                }
                freq.put(cur, prev + 1);
            }
            int[] answer = new int[]{end, 1};
            int start = 0;
            while (end < n) {
                char first = s.charAt(start);
                int val = freq.getOrDefault(first, 0);
                if (val == 1) {
                    freq.remove(first);
                } else {
                    freq.put(first, val - 1);
                }
                while (end < n) {
                    char next = s.charAt(end);
                    val = freq.getOrDefault(next, 0);
                    freq.put(next, val + 1);
                    if (isGoodString(freq, k)) {
                        end++;
                    } else {
                        if (end - start - 1 > answer[0]) {
                            answer = new int[]{end - start - 1, start + 2};
                        }
                        end++;
                        break;
                    }
                }
                start++;
            }
            if (isGoodString(freq, k) && end - start > answer[0]) {
                answer = new int[]{end - start, start + 1};
            }
            System.out.println(answer[0] + " " + answer[1]);

        } catch (IOException ignored) {

        }
    }

    static boolean isGoodString(Map<Character, Integer> freq, int k) {
        List<Character> result = freq.entrySet().stream().filter(el -> el.getValue() > k).map(el -> el.getKey()).collect(Collectors.toList());
        return result.size() == 0;
    }

    @Test
    public void test_01() {
        provideConsoleInput("3 1\n" +
                "abb\n");
        main(new String[0]);
        String expected = "2 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("5 2\n" +
                "ababa\n");
        main(new String[0]);
        String expected = "4 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("11 2\n" +
                "abcccddabab\n");
        main(new String[0]);
        String expected = "8 4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("12 2\n" +
                "abcccddababe\n");
        main(new String[0]);
        String expected = "9 4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("13 2\n" +
                "ababbaccddbbb\n");
        main(new String[0]);
        String expected = "8 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        provideConsoleInput("15 2\n" +
                "ababbaaaccddbbb\n");
        main(new String[0]);
        String expected = "8 7\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_07() {
        provideConsoleInput("1 1\n" +
                "a\n");
        main(new String[0]);
        String expected = "1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_08() {
        provideConsoleInput("2 2\n" +
                "ab\n");
        main(new String[0]);
        String expected = "2 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_09() {
        provideConsoleInput("2 1\n" +
                "ab\n");
        main(new String[0]);
        String expected = "2 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_10() {
        provideConsoleInput("2 1\n" +
                "aa\n");
        main(new String[0]);
        String expected = "1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f10() throws FileNotFoundException {
        String testNumber = "010";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
