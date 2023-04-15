package ru.algo.ya.c1.hw2;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://contest.yandex.ru/contest/27472/problems/F/
Симметричная последовательность
*/

public class TaskF extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(r.readLine());
            List<Integer> nums = Arrays.stream(r.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            int result = -1;
            for (int start = 0; start < nums.size(); start++) {
                boolean found = true;
                for (int i = 0; i <= (nums.size() - 1 - start) / 2; i++) {
                    if (nums.get(start + i) != nums.get(nums.size() - 1 - i)) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    result = start;
                    break;
                }
            }
            System.out.println(result);
            if (result != 0) {
                int max = result;
                String sequence = IntStream.range(0, result)
                        .mapToObj(i -> String.valueOf(nums.get(max - i - 1))).collect(Collectors.joining(" "));
                System.out.println(sequence);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("9\n" +
                "1 2 3 4 5 4 3 2 1\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("5\n" +
                "1 2 1 2 2\n");
        main(new String[0]);
        String expected = "3\n" +
                "1 2 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("5\n" +
                "1 2 3 4 5\n");
        main(new String[0]);
        String expected = "4\n" +
                "4 3 2 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
