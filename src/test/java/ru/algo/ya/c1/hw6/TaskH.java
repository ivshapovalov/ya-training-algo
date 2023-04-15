package ru.algo.ya.c1.hw6;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/*
https://contest.yandex.ru/contest/27844/problems/H/
Провода
*/
public class TaskH extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input =
                    Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int k = input[1];
            int[] chunks = new int[n];
            int max = 0;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                int ch = Integer.valueOf(reader.readLine().trim());
                chunks[i] = ch;
                max = Math.max(max, ch);
                sum += ch;
            }
            int len = 0;
            if (sum >= k) {
                len = binarySearch(chunks, k, max);
            }
            System.out.println(len);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static int binarySearch(int[] chunks, int k, int r) {
        int l = 1;
        while (l < r) {
            int len = l + ((r - l) / 2);
            int amount = getAmount(chunks, len);
            if (amount < k) {
                r = len - 1;
            } else {
                l = len + 1;
            }
//            if (r - l == 1 && amount == k) break;
        }
        return r;
    }

    private static int getAmount(int[] chunks, int len) {
        int counter = 0;
        for (int i = 0; i < chunks.length; i++) {
            counter += (chunks[i] / len);
        }
        return counter;
    }

    @Test
    public void test_01() {
        provideConsoleInput("4 11\n" +
                "802\n" +
                "743\n" +
                "457\n" +
                "539\n\n");
        main(new String[0]);
        String expected = "200\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("4 15\n" +
                "5\n" +
                "4\n" +
                "3\n" +
                "2\n\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("4 14\n" +
                "5\n" +
                "4\n" +
                "3\n" +
                "2\n\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("1 14\n" +
                "13\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("1 14\n" +
                "14\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_07() {
        provideConsoleInput("7 13\n" +
                "3318\n" +
                "5775\n" +
                "7318\n" +
                "336\n" +
                "9490\n" +
                "5712\n" +
                "2379");
        main(new String[0]);
        String expected = "2372\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        StringBuilder sb = new StringBuilder("10000 10000\n");
        for (int i = 1; i <= 10000; i++) {
            sb.append("10000000\n");
        }
        provideConsoleInput(sb.toString());
        main(new String[0]);
        String expected = "10000000\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
