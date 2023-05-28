package ru.algo.ya.c2.b.hw5;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/29075/problems/B/
Максимальная сумма

Ограничение времени 	3 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt


В этой задаче вам требуется найти непустой отрезок массива с максимальной суммой.
Формат ввода
В первой строке входных данных записано единственное число n (1≤n≤3⋅105) -  размер массива.
Во второй строке записано n целых чисел ai (−109≤ai≤109) - сам массив.

Формат вывода
Выведите одно число - максимальную сумму на отрезке в данном массиве.

 */

public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int size = Integer.parseInt(reader.readLine().trim());
            int[] arr = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            long[] prefix = new long[size + 1];
            Arrays.fill(prefix, 0);
            int minPrefSumIndex = 0;
            long maxSum = arr[0];
            long curSum;
            for (int i = 1; i < size + 1; i++) {
                prefix[i] = prefix[i - 1] + arr[i - 1];
                if (prefix[i - 1] < prefix[minPrefSumIndex]) {
                    minPrefSumIndex = i - 1;
                }
                curSum = prefix[i] - prefix[minPrefSumIndex];
                if (maxSum < curSum) {
                    maxSum = curSum;
                }
            }
            System.out.println(maxSum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("4\n" +
                "1 2 3 4\n");
        main(new String[0]);
        String expected = "10\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("4\n" +
                "5 4 -10 4\n");
        main(new String[0]);
        String expected = "9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("4\n" +
                "-1 -2 -3 -4\n");
        main(new String[0]);
        String expected = "-1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_4() {
        provideConsoleInput("5\n" +
                "450402558 -840167367 -231820501 586187125 -627664644 \n");
        main(new String[0]);
        String expected = "586187125\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_m01() {
        provideConsoleInput("14\n" +
                "-8 -1 5 -3 -1 3 3 4 -8 2 -8 10 3 -7\n");
        main(new String[0]);
        String expected = "13\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}