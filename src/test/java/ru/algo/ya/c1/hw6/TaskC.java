package ru.algo.ya.c1.hw6;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/27844/problems/C/
Дипломы
*/
public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input =
                    Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int w = input[0];
            int h = input[1];
            int n = input[2];

            long res = binarySearch(w, h, n);
            System.out.println(res);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long binarySearch(int w, int h, int n) {
        long l = Math.min(w, h);
        long r = Math.max(h, w) * n + 1;
        while (l < r) {
            long mid = l + ((r - l) / 2);
            if ((mid / w) * (mid / h) > n) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    @Test
    public void test_1() {
        provideConsoleInput("2 3 10\n\n");
        main(new String[0]);
        String expected = "9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_01() {
        provideConsoleInput("2 3 9\n\n");
        main(new String[0]);
        String expected = "9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("2 3 13\n\n");
        main(new String[0]);
        String expected = "10\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("2 3 1\n\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("2 1 1\n\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput(Integer.MAX_VALUE + " " + Integer.MAX_VALUE + " " + Integer.MAX_VALUE + "\n\n");
        main(new String[0]);
        String expected = "2147483647\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_2() {
        provideConsoleInput("1 1 10\n\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_3() {
        provideConsoleInput("1 1 1\n\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
