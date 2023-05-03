package ru.algo.ya.c2.b.hw2;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/28738/problems/A/
Количество равных максимальному

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Последовательность состоит из натуральных чисел и завершается числом 0. Всего вводится не более 10000 чисел (не считая
завершающего числа 0). Определите, сколько элементов этой последовательности равны ее наибольшему элементу.

Числа, следующие за числом 0, считывать не нужно.

Формат ввода
Вводится последовательность целых чисел, оканчивающаяся числом 0 (само число 0 в последовательность не входит).

Формат вывода
Выведите ответ на задачу.
 */

public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line=reader.readLine().trim();
            int max=0;
            int counter=0;
            while(line!=null && !"0".equals(line)) {
                int cur=Integer.parseInt(line);
                if (cur>max) {
                    max=cur;
                    counter=1;
                }else if (cur==max) {
                    counter++;
                }
                line=reader.readLine().trim();
            }

            System.out.println(counter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1\n" +
                "7\n" +
                "9\n" +
                "0\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1\n" +
                "3\n" +
                "3\n" +
                "1\n" +
                "0\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}