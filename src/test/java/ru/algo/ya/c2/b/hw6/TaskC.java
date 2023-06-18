package ru.algo.ya.c2.b.hw6;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/29188/problems/С/
Корень кубического уравнения

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	cubroot.in
Вывод 	cubroot.out

Дано кубическое уравнение ax3+bx2+cx+d=0 (a≠0). Известно, что у этого уравнения есть ровно один корень. Требуется его найти.
Формат ввода

Во входном файле через пробел записаны четыре целых числа: .
Формат вывода

Выведите единственный корень уравнения с точностью не менее 5 знаков после десятичной точки.

 */

public class TaskC extends ContestTask {

    private static final double EPSILON = 1e-5;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] nums = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            double result = binSearch(-100000, 100000, nums[0], nums[1], nums[2], nums[3]);
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double binSearch(double l, double r, int a, int b, int c, int d) {
        if (a < 0) {
            while (r - l > EPSILON) {
                double m = l + (r - l) / 2;
                if (res(a, b, c, d, m) < 0) {
                    r = m;
                } else {
                    l = m;
                }
            }
        } else {
            while (r - l > EPSILON) {
                double m = l + (r - l) / 2;
                if (res(a, b, c, d, m) > 0) {
                    r = m;
                } else {
                    l = m;
                }
            }
        }
        return l;
    }

    private static double res(int a, int b, int c, int d, double x) {
        return a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
    }

    @Test
    public void test_01() {
        provideConsoleInput("1 -3 3 -1\n");
        main(new String[0]);
        String expected = "1.0000036491\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("-1 -6 -12 -7\n");
        main(new String[0]);
        String expected = "-1.0000000111\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}