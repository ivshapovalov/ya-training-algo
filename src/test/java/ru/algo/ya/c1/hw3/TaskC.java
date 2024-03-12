package ru.algo.ya.c1.hw3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

 

/*
https://contest.yandex.ru/contest/27663/problems/C/
Кубики
*/

public class TaskC extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> nums =
                    Arrays.stream(r.readLine().split(" "))
                            .map(el -> Integer.valueOf(el))
                            .collect(Collectors.toList());
            Set<Integer> first = new TreeSet<>();
            Set<Integer> second = new TreeSet<>();
            Set<Integer> common = new TreeSet<>();
            for (int i = 0; i < nums.get(0); i++) {
                first.add(Integer.valueOf(r.readLine()));
            }

            for (int i = 0; i < nums.get(1); i++) {
                Integer cur = Integer.valueOf(r.readLine());
                if (first.remove(cur)) {
                    common.add(cur);
                } else {
                    second.add(cur);
                }
            }
            System.out.println(common.size());
            System.out.println(
                    common.stream().sorted(Comparator.naturalOrder()).map(String::valueOf)
                            .collect(Collectors.joining(" ")));

            System.out.println(first.size());
            System.out.println(first.stream().sorted(Comparator.naturalOrder())
                    .map(String::valueOf).collect(Collectors.joining(" ")));

            System.out.println(second.size());
            System.out.println(second.stream().sorted(Comparator.naturalOrder())
                    .map(String::valueOf).collect(Collectors.joining(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("4 3\n" +
                "0\n" +
                "1\n" +
                "10\n" +
                "9\n" +
                "1\n" +
                "3\n" +
                "0\n");
        main(new String[0]);
        String expected = "2\n" +
                "0 1\n" +
                "2\n" +
                "9 10\n" +
                "1\n" +
                "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("2 2\n" +
                "1\n" +
                "2\n" +
                "2\n" +
                "3\n");
        main(new String[0]);
        String expected = "1\n" +
                "2\n" +
                "1\n" +
                "1\n" +
                "1\n" +
                "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("0 0\n");
        main(new String[0]);
        String expected = "0\n" +
                "\n" +
                "0\n" +
                "\n" +
                "0\n" +
                "\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
