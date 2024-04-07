package algo.c1.hw6;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/27844/problems/F/
Очень легкая задача
*/

public class TaskF extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input =
                    Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int x = input[1];
            int y = input[2];

            long answer = binarySearch(n, x, y);
            System.out.println(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int binarySearch(int n, int x, int y) {
        int l = Math.min(x, y);
        int r = Math.max(x, y) * n;
        while (l < r) {
            int time = l + ((r - l) / 2);
            if (checkCopies(n, x, y, time)) {
                r = time;
            } else {
                l = time + 1;
            }
        }
        return l;
    }

    private static boolean checkCopies(int n, int x, int y, int time) {
        int count = ((time - Math.min(x, y)) / Math.max(x, y)) + (time / (Math.min(x, y)));
        return count >= n;
    }

    @Test
    public void test_01() {
        provideConsoleInput("4 1 1\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("5 1 2\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
