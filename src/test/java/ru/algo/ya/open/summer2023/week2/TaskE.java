package ru.algo.ya.open.summer2023.week2;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/50242/problems/E/
Удаление чисел

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дан массив a из n чисел. Найдите минимальное количество чисел, после удаления которых попарная разность оставшихся чисел по модулю не будет превышать 1, то есть после удаления ни одно число не должно отличаться от какого-либо другого более чем на 1.

Формат ввода
Первая строка содержит одно целое число n (1≤n≤2⋅105) — количество элементов массива a.
Вторая строка содержит n целых чисел a1,a2,…,an (0≤ai≤105) — элементы массива a.

Формат вывода
Выведите одно число — ответ на задачу.
 */

public class TaskE extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            Map<Integer, Long> freq = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int num = input[i];
                freq.compute(num, ((k, v) -> (v == null) ? 1 : v + 1));
            }
            long sum = 0;
            for (int i = 0; i < n; i++) {
                long curSum = freq.get(input[i]) +
                        Math.max(freq.getOrDefault(input[i] - 1, 0L), freq.getOrDefault(input[i] + 1, 0L));
                if (curSum > sum) {
                    sum = curSum;
                }
            }

            System.out.println(n - sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("5\n" +
                "1 2 3 4 5\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("10\n" +
                "1 1 2 3 5 5 2 2 1 5\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("10\n" +
                "1 3 1 3 5 7 8 3 8 3\n");
        main(new String[0]);
        String expected = "6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}