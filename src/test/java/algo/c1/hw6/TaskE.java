package algo.c1.hw6;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/27844/problems/E/
Улучшение успеваемости
*/

public class TaskE extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            long a = Double.valueOf(reader.readLine().trim()).longValue();
            long b = Double.valueOf(reader.readLine().trim()).longValue();
            long c = Double.valueOf(reader.readLine().trim()).longValue();

            long d = binarySearch(a, b, c);
            System.out.println(d);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean check(long a, long b, long c, long mid) {
        long sum = (2 * a) + (3 * b) + (4 * c) + (5 * mid);
        long count = a + b + c + mid;
        return sum * 2 >= count * 7;
    }

    private static long binarySearch(long a, long b, long c) {
        long l = 0;
        long r = 100_000_000_000_000_000L;

        while (l < r) {
            long mid = (l + r) / 2;

            if (check(a, b, c, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    @Test
    public void test_01() {
        provideConsoleInput("2\n" +
                "0\n" +
                "0\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("0\n" +
                "0\n" +
                "4\n\n\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("1\n" +
                "0\n" +
                "4\n\n\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("5\n" +
                "5\n" +
                "5\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_12() {
        provideConsoleInput("0\n" +
                "0\n" +
                "3\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("1E+15\n" +
                "1E+15\n" +
                "1E+15\n\n\n");
        main(new String[0]);
        String expected = "1000000000000000\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_32() {
        provideConsoleInput("1000000000000000\n" +
                "1000000000000000\n" +
                "1000000000000000");
        main(new String[0]);
        String expected = "1000000000000000\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}
