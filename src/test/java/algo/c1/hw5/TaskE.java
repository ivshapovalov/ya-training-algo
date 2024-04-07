package algo.c1.hw5;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/27794/problems/E/
Красота превыше всего
*/
public class TaskE extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int[] input =
                    Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int k = input[1];
            int[] colors =
                    Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int end = 0;
            Map<Integer, Integer> trees = new HashMap<>();
            while (end < n && trees.size() < k) {
                trees.put(colors[end], trees.getOrDefault(colors[end], 0) + 1);
                end++;
            }
            int[] result = new int[]{0, end};
            int start = 0;
            while (start < end && end <= n) {
                int cur = colors[start];
                int freq = trees.get(cur);
                if (freq == 1) {
                    trees.remove(cur);
                } else {
                    trees.put(cur, freq - 1);
                }

                start++;
                if (trees.size() == k && end - start < result[1] - result[0]) {
                    result = new int[]{start, end};
                } else {
                    if (end == n) break;
                    trees.put(colors[end], trees.getOrDefault(colors[end], 0) + 1);
                    end++;
                }
            }
            if (trees.size() == k && end - 1 - start < result[1] - result[0]) {
                result = new int[]{start - 1, end - 1};
            }

            System.out.println((result[0] + 1) + " " + (result[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("5 3\n" +
                "1 2 1 3 2\n");
        main(new String[0]);
        String expected = "2 4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("6 4\n" +
                "2 4 2 3 3 1\n");
        main(new String[0]);
        String expected = "2 6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("6 4\n" +
                "2 3 2 4 3 1\n");
        main(new String[0]);
        String expected = "3 6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("8 4\n" +
                "2 3 2 4 3 1 2 4\n");
        main(new String[0]);
        String expected = "3 6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
