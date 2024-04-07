package algo.c1.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27472/problems/C/
Ближайшее число
*/

public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(r.readLine());
            List<Integer> nums = Arrays.stream(r.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            int x = Integer.valueOf(r.readLine());

            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int i = 0; i < n; i++) {
                int extra = Math.abs(x - nums.get(i));
                if (min > extra) {
                    min = extra;
                    minIndex = i;
                }
            }
            System.out.println(nums.get(minIndex));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("5\n" +
                "1 2 3 4 5\n" +
                "6\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("5\n" +
                "5 4 3 2 1\n" +
                "3\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
