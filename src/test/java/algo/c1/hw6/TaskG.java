package algo.c1.hw6;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/27844/problems/G/
Площадь
*/

public class TaskG extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            int m = Integer.valueOf(reader.readLine().trim());
            long t = Long.valueOf(reader.readLine().trim());

            long width = binarySearch(n, m, t);
            System.out.println(width);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long binarySearch(int n, int m, long t) {
        long l = 1;
        long r = Math.min(n, m) / 2;
        if (l == r) return l;
        while (l <= r) {
            long width = l + ((r - l) / 2);
            long amount = getAmount(n, m, t, width);
            if (amount <= t) {
                l = width + 1;
            } else {
                r = width - 1;
            }
        }
        return l - 1;
    }

    private static long getAmount(int n, int m, long t, long road) {
        long width = Math.min(n, m);
        long length = Math.max(n, m);
        long count = (width * road * 2) + (length - 2 * road) * road * 2;
        return count;
    }

    @Test
    public void test_01() {
        provideConsoleInput("6\n" +
                "7\n" +
                "38\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("3\n" +
                "3\n" +
                "38\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("6\n" +
                "6\n" +
                "38\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("6\n" +
                "6\n" +
                "35\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("6\n" +
                "6\n" +
                "5\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
