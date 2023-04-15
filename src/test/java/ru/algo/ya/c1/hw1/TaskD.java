package ru.algo.ya.c1.hw1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/27393/problems/D/
Уравнение с корнем
*/

public class TaskD extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = Integer.valueOf(reader.readLine());
            int b = Integer.valueOf(reader.readLine());
            int c = Integer.valueOf(reader.readLine());

            String answer = "NO SOLUTION";
            if (c < 0) {
                answer = "NO SOLUTION";
            } else if (a + b == c * c && (2 * a + b) == c * c) {
                answer = "MANY SOLUTIONS";
            } else {
                Double res = (Math.pow(c, 2) - b) / a;
                if (res.doubleValue() - res.longValue() == 0) {
                    answer = String.valueOf(res.longValue());
                } else {
                    answer = "NO SOLUTION";
                }
            }
            System.out.println(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1\n" +
                "0\n" +
                "0\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1\n" +
                "2\n" +
                "3\n");
        main(new String[0]);
        String expected = "7\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("1\n" +
                "2\n" +
                "-3\n");
        main(new String[0]);
        String expected = "NO SOLUTION\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
