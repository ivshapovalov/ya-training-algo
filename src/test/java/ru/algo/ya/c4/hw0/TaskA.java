package ru.algo.ya.c4.hw0;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalInt;

/*
https://contest.yandex.ru/contest/53027/problems/A/
Не минимум на отрезке

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Задана последовательность целых чисел a1, a2, …, an. Задаются запросы: сказать любой элемент последовательности на
отрезке от L до R включительно, не равный минимуму на этом отрезке.

Формат ввода
В первой строке содержатся два целых числа N, 1 ≤ N ≤ 100 и M, 1 ≤ M ≤ 1000 — длина последовательности и количество
запросов соответственно.

Во второй строке — сама последовательность, 0 ≤ ai ≤ 1000.
Начиная с третьей строки перечисляются M запросов, состоящих из границ отрезка L и R, где L, R - индексы массива,
нумеруются с нуля.

Формат вывода
На каждый запрос выведите в отдельной строке ответ — любой элемент на [L, R], кроме минимального. В случае, если такого
элемента нет, выведите "NOT FOUND".

*/

public class TaskA extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine().trim().split(" ")).mapToInt(el -> Integer.parseInt(el)).toArray();
            int M = input[1];
            int[] seq = Arrays.stream(r.readLine().trim().split(" ")).mapToInt(el -> Integer.parseInt(el)).toArray();
            for (int i = 0; i < M; i++) {
                int[] bounds = Arrays.stream(r.readLine().trim().split(" ")).mapToInt(el -> Integer.parseInt(el)).toArray();
                String result = findSecondMinElement(seq, bounds[0], bounds[1]);
                System.out.println(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String findSecondMinElement(int[] seq, int lIndex, int rIndex) {
        int[] part = Arrays.copyOfRange(seq, lIndex, rIndex + 1);
        OptionalInt second = Arrays.stream(part).distinct().sorted().skip(1).findFirst();
        if (second.isEmpty()) {
            return "NOT FOUND";
        } else {
            return String.valueOf(second.getAsInt());
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("10 5\n" +
                "1 1 1 2 2 2 3 3 3 10\n" +
                "0 1\n" +
                "0 3\n" +
                "3 4\n" +
                "7 9\n" +
                "3 7\n");
        main(new String[0]);
        String expected = "NOT FOUND\n" +
                "2\n" +
                "NOT FOUND\n" +
                "10\n" +
                "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("4 2\n" +
                "1 1 1 2\n" +
                "0 2\n" +
                "0 3\n");
        main(new String[0]);
        String expected = "NOT FOUND\n" +
                "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("4 2\n" +
                "1 2 1 1\n" +
                "0 2\n" +
                "2 3\n");
        main(new String[0]);
        String expected = "2\n" +
                "NOT FOUND\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
