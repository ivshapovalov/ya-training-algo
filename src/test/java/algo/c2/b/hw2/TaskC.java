package algo.c2.b.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/28738/problems/C/
Изготовление палиндромов

Ограничение времени 	2 секунды
Ограничение памяти 	512Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В строкоремонтную мастерскую принесли строку, состоящую из строчных латинских букв. Заказчик хочет сделать из неё
палиндром. В мастерской могут за 1 байтландский тугрик заменить произвольную букву в строке любой выбранной заказчиком
буквой.

Какую минимальную сумму придётся заплатить заказчику за ремонт строки?
Напомним, что палиндромом называется строка, которая равна самой себе, прочитанной в обратном направлении.

Формат ввода
Входные данные содержат непустую строку, состоящую из строчных латинских букв, которую принёс заказчик.
Длина строки не превосходит 104.

Формат вывода
Выведите одно целое число — минимальную сумму, которую заказчику придётся
заплатить за превращение принесённой заказчиком строки в палиндром.
 */

public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine().trim();
            int counter = 0;
            for (int i = 0; i < line.length() / 2; i++) {
                if (line.charAt(i) != line.charAt(line.length() - 1 - i)) {
                    counter++;
                }
            }
            System.out.println(counter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("a\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("ab\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("cognitive\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
