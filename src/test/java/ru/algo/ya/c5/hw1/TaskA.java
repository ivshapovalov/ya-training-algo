package ru.algo.ya.c5.hw1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/53029/problems/A/
Покраска деревьев

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Вася и Маша участвуют в субботнике и красят стволы деревьев в белый цвет. Деревья растут вдоль улицы через равные
промежутки в 1 метр. Одно из деревьев обозначено числом ноль, деревья по одну сторону занумерованы положительными
числами 1,2 и т.д., а в другую — отрицательными −1,−2 и т.д.

Ведро с краской для Васи установили возле дерева P, а для Маши — возле дерева Q. Ведра с краской очень тяжелые и Вася с
Машей не могут их переставить, поэтому они окунают кисть в ведро и уже с этой кистью идут красить дерево. Краска на
кисти из ведра Васи засыхает, когда он удаляется от ведра более чем на V метров, а из ведра Маши — на M метров.
Определите, сколько деревьев может быть покрашено.

Формат ввода
В первой строке содержится два целых числа P и V — номер
дерева, у которого стоит ведро Васи и на сколько деревьев он может от него удаляться.

В второй строке содержится два целых числа Q и M — аналогичные данные для Маши.

Все числа целые и по модулю не превосходят 108.

Формат вывода
Выведите одно число — количество деревьев, которые могут быть покрашены.

*/

public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] first = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int[] second = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();

            Range r1 = new Range(first[0] - first[1], first[0] + first[1]);
            Range r2 = new Range(second[0] - second[1], second[0] + second[1]);
            int result = 0;
            if (r1.max < r2.min || r2.max < r1.min) {
                result = (r1.max - r1.min) + (r2.max - r2.min) + 2;
            } else if (r1.max <= r2.min || r2.max <= r1.min) {
                result = (r1.max - r1.min) + (r2.max - r2.min) + 1;
            } else if (r1.min >= r2.min && r1.max <= r2.max) {
                result = r2.max - r2.min + 1;
            } else if (r1.min <= r2.min && r1.max >= r2.max) {
                result = r1.max - r1.min + 1;
            } else if (r1.min <= r2.min && r1.max <= r2.max && r1.max >= r2.min) {
                result = (r2.max - r1.min) + 1;
            } else if (r2.min <= r1.min && r2.max <= r1.max && r2.max >= r1.min) {
                result = (r1.max - r2.min) + 1;
            }

            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    record Range(int min, int max) {
    }

    @Test
    public void test_001() {
        provideConsoleInput("0 7\n" +
                "12 5\n");
        main(new String[0]);
        String expected = "25\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("2 3\n" +
                "10 3\n");
        main(new String[0]);
        String expected = "14\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_008() {
        provideConsoleInput("0 1\n" +
                "0 2\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}
