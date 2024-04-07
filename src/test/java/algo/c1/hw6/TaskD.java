package algo.c1.hw6;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/27844/problems/D/
Космическое переселение
*/

public class TaskD extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            long[] input =
                    Arrays.stream(reader.readLine().trim().split(" "))
                            .mapToLong(el -> Long.valueOf(el).longValue()).toArray();
            long n = input[0];
            long a = input[1];
            long b = input[2];
            long w = input[3];
            long h = input[4];

            long res = binarySearch(n, a, b, w, h);
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static long binarySearch(long n, long a, long b, long w, long h) {
        long blockW = Math.min(a, b);
        long blockH = Math.max(a, b);
        long fieldW = Math.min(w, h);
        long fieldH = Math.max(w, h);
        long l = 0;
        long r = fieldW;
        while (l < r) {
            long mid = l + ((r - l) / 2);
            long amount =
                    Math.max(
                            (fieldW / (blockW + 2 * mid)) * (fieldH / (blockH + 2 * mid)),
                            (fieldW / (blockH + 2 * mid)) * (fieldH / (blockW + 2 * mid)));
            if (amount >= n) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l - 1;
    }

    @Test
    public void test_01() {
        provideConsoleInput("1 1 1 1 1\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1 1 1 3 3\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("3 1 1 5 9\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("3 1 1 5 15\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("19 2 7 253 211\n");
        main(new String[0]);
        String expected = "22\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
