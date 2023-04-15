package ru.algo.ya.c1.hw3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27663/problems/B/
Пересечение множеств
*/

public class TaskB extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            Set<String> set1 =
                    Arrays.stream(r.readLine().split(" "))
                            .collect(Collectors.toSet());
            System.out.println(Arrays.stream(r.readLine().split(" "))
                    .filter(el -> set1.contains(el))
                    .map(el -> Integer.valueOf(el))
                    .sorted(Comparator.naturalOrder())
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1 3 2\n" +
                "4 3 2\n");
        main(new String[0]);
        String expected = "2 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1 2 6 4 5 7\n" +
                "10 2 3 4 8\n");
        main(new String[0]);
        String expected = "2 4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
