package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
https://contest.yandex.ru/contest/45468/problems/20/
Пирамидальная сортировка

Ограничение времени 	2 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Отсортируйте данный массив. Используйте пирамидальную сортировку.

Формат ввода
Первая строка входных данных содержит количество элементов в массиве N, N ≤ 105. Далее задаются N целых чисел, не
превосходящих по абсолютной величине 109.

Формат вывода
Выведите эти числа в порядке неубывания.

*/
public class Task20 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.naturalOrder());
            Arrays.stream(reader.readLine().trim().split(" "))
                    .forEach(el -> {
                        q.add(Integer.valueOf(el));
                    });

            StringBuilder sb = new StringBuilder();
            while (!q.isEmpty()) {
                sb.append(q.poll() + " ");
            }
            System.out.print(sb.substring(0, sb.length() - 1) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1\n" +
                "1\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("2\n" +
                "3 1\n");
        main(new String[0]);
        String expected = "1 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("3\n" +
                "3 1 2\n");
        main(new String[0]);
        String expected = "1 2 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
