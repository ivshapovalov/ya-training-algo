package algo.c1.hw5;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/27794/problems/D/
Город Че
*/

public class TaskD extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line = reader.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int r = Integer.parseInt(line[1]);

            int[] distances =
                    Arrays.stream(reader.readLine().trim().split(" "))
                            .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            System.out.println(countPossibleDatePlaces(distances, r));
        } catch (IOException ignore) {

        }
    }

    public static long countPossibleDatePlaces(int[] monuments, int min) {
        int l = 0;
        int r = 1;
        long count = 0;
        final int N = monuments.length;

        while (r < N) {
            if (monuments[r] - monuments[l] <= min) {
                r++;
            } else {
                count += N - r;
                l++;
            }
        }
        return count;
    }


    @Test
    public void test_01() {
        provideConsoleInput("4 3\n" +
                "1 3 5 9\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("4 4\n" +
                "1 3 5 8\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("6 4\n" +
                "1 3 5 8 9 10 \n");
        main(new String[0]);
        String expected = "7\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("6 4\n" +
                "1 1 1 1 1 2 \n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("2 4\n" +
                "1 5\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
