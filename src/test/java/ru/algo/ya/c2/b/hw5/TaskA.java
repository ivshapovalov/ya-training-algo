package ru.algo.ya.c2.b.hw5;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/29075/problems/A/
Префиксные суммы

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В этой задаче вам нужно будет много раз отвечать на запрос «Найдите сумму чисел на отрезке в массиве».

Формат ввода В
первой строке записано два целых числа n и q (1≤n,q≤3⋅105) - размер массива и количество запросов.

Во второй строке записаны n целых чисел ai (1≤ai≤109) - сам массив.
Далее в q строках описаны запросы к массиву. Каждый запрос описывается двумя числами l, r (1≤l≤r≤n) - левой и правой
границей отрезка, на котором нужно найти сумму.

Формат вывода
Для каждого запроса в отдельной строке выведите единственное число - сумму на соответствующем отрезке.

 */

public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();

            int n = input[0];
            int q = input[1];
            int[] arr = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            long[] sum = new long[n + 1];
            sum[0] = 0;
            sum[1] = arr[0];
            for (int i = 1; i < arr.length; i++) {
                sum[i + 1] = sum[i] + arr[i];
            }
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < q; i++) {
                int[] query = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                int from = query[0];
                int to = query[1];
                long curSum = sum[to] - sum[from] + arr[from - 1];
                result.append(curSum + "\n");
            }
            System.out.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("4 10\n" +
                "1 2 3 4\n" +
                "1 1\n" +
                "1 2\n" +
                "1 3\n" +
                "1 4\n" +
                "2 2\n" +
                "2 3\n" +
                "2 4\n" +
                "3 3\n" +
                "3 4\n" +
                "4 4\n");
        main(new String[0]);
        String expected = "1\n" +
                "3\n" +
                "6\n" +
                "10\n" +
                "2\n" +
                "5\n" +
                "9\n" +
                "3\n" +
                "7\n" +
                "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}