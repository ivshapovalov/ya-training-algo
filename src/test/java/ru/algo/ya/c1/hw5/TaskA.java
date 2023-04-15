package ru.algo.ya.c1.hw5;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

 

/*
https://contest.yandex.ru/contest/27794/problems/A/
Стильная одежда
*/

public class TaskA extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(r.readLine().trim());
            int[] ups =
                    Arrays.stream(r.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int m = Integer.valueOf(r.readLine().trim());
            int[] downs =
                    Arrays.stream(r.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();

            int[] pair = findPairWithMinDiff(ups, downs);
            System.out.println(pair[0] + " " + pair[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] findPairWithMinDiff(int[] ups, int[] downs) {
        int upIndex = 0, downIndex = 0;
        int[] pair = new int[2];

        int diff = Integer.MAX_VALUE;
        while (upIndex < ups.length && downIndex < downs.length) {
            int up = ups[upIndex];
            int down = downs[downIndex];

            int currentDiff = Math.abs(up - down);
            if (currentDiff == 0) {
                pair[0] = up;
                pair[1] = down;
                return pair;
            } else if (diff > currentDiff) {
                diff = currentDiff;
                pair[0] = up;
                pair[1] = down;
            }

            if (up > down) {
                downIndex++;
            } else {
                upIndex++;
            }
        }
        return pair;
    }

    @Test
    public void test_00() {
        provideConsoleInput("5\n" +
                "3 6 8\n" +
                "5\n" +
                "2 4 5 7 8\n");
        main(new String[0]);
        String expected = "8 8\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_01() {
        provideConsoleInput("2\n" +
                "3 4\n" +
                "3\n" +
                "1 2 3\n");
        main(new String[0]);
        String expected = "3 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("2\n" +
                "4 5\n" +
                "3\n" +
                "1 2 3\n");
        main(new String[0]);
        String expected = "4 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("5\n" +
                "1 2 3 4 5\n" +
                "5\n" +
                "1 2 3 4 5\n");
        main(new String[0]);
        String expected = "1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
