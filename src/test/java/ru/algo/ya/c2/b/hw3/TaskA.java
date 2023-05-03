package ru.algo.ya.c2.b.hw3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/28964/problems/A/
Количество совпадающих

Ограничение времени 	3 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Даны два списка чисел, которые могут содержать до 100000 чисел каждый. Посчитайте, сколько чисел содержится одновременно
как в первом списке, так и во втором. Примечание. Эту задачу на Питоне можно решить в одну строчку.

Формат ввода
Вводятся два списка чисел. Все числа каждого списка находятся на отдельной строке.

Формат вывода
Выведите ответ на задачу.
 */

public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Set<Integer> list1 = Arrays.stream(reader.readLine().trim().split(" "))
                    .map(el -> Integer.valueOf(el)).collect(Collectors.toSet());
            Set<Integer> list2 = Arrays.stream(reader.readLine().trim().split(" "))
                    .map(el -> Integer.valueOf(el)).collect(Collectors.toSet());

            list1.retainAll(list2);
            System.out.println(list1.stream().count());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1 3 2\n" +
                "4 3 2\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1 2 6 4 5 7\n" +
                "10 2 3 4 8\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("1 7 3 8 10 2 5\n" +
                "6 5 2 8 4 3 7\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}