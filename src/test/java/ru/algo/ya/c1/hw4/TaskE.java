package ru.algo.ya.c1.hw4;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/27665/problems/E/
Пирамида
*/

public class TaskE extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(r.readLine());
            Map<Long, Long> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                long[] block =
                        Arrays.stream(r.readLine().split(" ")).mapToLong(el -> Long.valueOf(el).longValue()).toArray();
                if (map.containsKey(block[0])) {
                    long prevH = map.get(block[0]);
                    if (prevH < block[1]) {
                        map.put(block[0], block[1]);
                    }
                } else {
                    map.put(block[0], block[1]);
                }
            }
            System.out.println(map.values().stream().mapToLong(el -> el).sum());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "3 1\n" +
                "2 2\n" +
                "3 3\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("3\n" +
                "3 1000000000\n" +
                "2 1000000000\n" +
                "3 1000000000\n");
        main(new String[0]);
        String expected = "2000000000\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
