package ru.algo.ya.c4.hw0;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/53027/problems/B/
Сложить две дроби

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Даны две рациональные дроби: a/b и c/d. Сложите их и результат представьте в виде несократимой дроби m/n.

Формат ввода
Программа получает на вход 4 натуральных числа a, b, c, d, каждое из которых не больше 100.

Формат вывода
Программа должна вывести два натуральных числа m и n такие, что m/n=a/b+c/d и дробь m/n – несократима.

*/

public class TaskB extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine().trim().split(" ")).mapToInt(el -> Integer.parseInt(el)).toArray();
            int a = input[0];
            int b = input[1];
            int c = input[2];
            int d = input[3];

            long m = a + c;
            long n = b;
            if (b != d) {
                m = (a * d) + (c * b);
                n = b * d;
            }

            Fraction fraction = new Fraction(m, n);
            fraction.simplify();
            System.out.println(fraction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1 2 1 2\n");
        main(new String[0]);
        String expected = "1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_12() {
        provideConsoleInput("13 42 22 70\n");
        main(new String[0]);
        String expected = "131 210\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("2 6 1 5\n");
        main(new String[0]);
        String expected = "8 15\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("2 6 4 6\n");
        main(new String[0]);
        String expected = "1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("2 6 2 3\n");
        main(new String[0]);
        String expected = "1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_004() {
        provideConsoleInput("80 3 10 3\n");
        main(new String[0]);
        String expected = "30 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_005() {
        provideConsoleInput("33 100 67 100\n");
        main(new String[0]);
        String expected = "1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_006() {
        provideConsoleInput("2 10 2 20\n");
        main(new String[0]);
        String expected = "3 10\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    public static class Fraction {
        private long a;
        private long b;

        Fraction(long a, long b) {
            this.a = a;
            this.b = b;
        }

        public String toString() {
            return a + " " + b;
        }

        public Fraction simplify() {
            long limit = Math.min(a, b);

            for (long i = 2; i <= limit; i++) {
                if (a % i == 0 && b % i == 0) {
                    a /= i;
                    b /= i;
                    if (a == b) {
                        a = 1;
                        b = 1;
                    }
                    i--;
                }
            }
            return this;
        }
    }

}
