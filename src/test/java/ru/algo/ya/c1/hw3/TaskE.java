package ru.algo.ya.c1.hw3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

 

/*
https://contest.yandex.ru/contest/27663/problems/E/
OpenCalculator
*/

public class TaskE extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> buttons =
                    Arrays.stream(r.readLine().split(" "))
                            .map(el -> Integer.valueOf(el))
                            .collect(Collectors.toList());
            String text = r.readLine();

            Set<Integer> digits = new HashSet<>();
            for (int i = 0; i < text.length(); i++) {
                digits.add(text.charAt(i) - '0');
            }
            digits.removeAll(buttons);
            System.out.println(digits.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1 2 3\n" +
                "1123\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1 2 3\n" +
                "1001\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("5 7 3\n" +
                "123\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
