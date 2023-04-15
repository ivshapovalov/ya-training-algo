package ru.algo.ya.c1.hw3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

 

/*
https://contest.yandex.ru/contest/27663/problems/G/
Черепахи
*/

public class TaskG extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            Integer num = Integer.valueOf(r.readLine());
            int[][] words = new int[num][2];
            for (int i = 0; i < num; i++) {
                words[i] = Arrays.stream(r.readLine().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            }
            Set<Integer> result = new HashSet<>();
            for (int i = 0; i < words.length; i++) {
                if (words[i][0] >= 0 && words[i][1] >= 0
                        && words[i][0] + words[i][1] == words.length - 1) {
                    result.add(words[i][0]);
                }
            }
            System.out.println(result.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "2 0\n" +
                "0 2\n" +
                "2 2\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("5\n" +
                "0 4\n" +
                "1 3\n" +
                "2 2\n" +
                "3 1\n" +
                "4 0\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("10\n" +
                "9 1\n" +
                "8 1\n" +
                "7 2\n" +
                "6 2\n" +
                "5 3\n" +
                "4 4\n" +
                "3 6\n" +
                "2 7\n" +
                "1 9\n" +
                "0 8\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
