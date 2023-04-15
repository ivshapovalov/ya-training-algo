package ru.algo.ya.c1.hw3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/27663/problems/H/
Злые свинки
*/

public class TaskH extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            Integer num = Integer.valueOf(r.readLine());
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < num; i++) {
                int[] input = Arrays.stream(r.readLine().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                int x = input[0];
                int y = input[1];
                int maxY = map.getOrDefault(x, 0);
                map.put(x, Math.max(y, maxY));
            }
            System.out.println(map.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("6\n" +
                "1 1\n" +
                "2 2\n" +
                "3 3\n" +
                "2 1\n" +
                "3 2\n" +
                "3 1\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("6\n" +
                "1 1\n" +
                "2 2\n" +
                "3 3\n" +
                "2 1\n" +
                "3 2\n" +
                "3 4\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
