package ru.algo.ya.c1.hw2;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/27472/problems/J/
Треугольник максима
*/

public class TaskJ extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String line = r.readLine();
            Integer n = Integer.valueOf(line);
            double left = 30;
            double right = 4000;
            double prev = 0;
            for (int i = 0; i < n; i++) {
                String[] input = r.readLine().split(" ");
                double now = Double.valueOf(input[0]);
                if (i != 0) {
                    if (Math.abs(now - prev) < 1E-6) {
                        continue;
                    }
                    String dir = input[1];
                    double cur = (prev + now) / 2;
                    switch (dir) {
                        case "closer":
                            if (now > prev) {
                                left = Math.max(left, cur);
                            } else {
                                right = Math.min(right, cur);
                            }
                            break;
                        case "further":
                            if (now < prev) {
                                left = Math.max(left, cur);
                            } else {
                                right = Math.min(right, cur);
                            }
                            break;
                    }
                    prev = now;
                } else {
                    prev = now;
                }

            }
            System.out.println(left + " " + right);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "440\n" +
                "330 closer\n" +
                "300 closer\n");
        main(new String[0]);
        String expected = "30.0 315.0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("3\n" +
                "440\n" +
                "220 closer\n" +
                "300 further\n");
        main(new String[0]);
        String expected = "30.0 260.0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("4\n" +
                "554\n" +
                "880 further\n" +
                "440 closer\n" +
                "622 closer\n");
        main(new String[0]);
        String expected = "531.0 660.0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
