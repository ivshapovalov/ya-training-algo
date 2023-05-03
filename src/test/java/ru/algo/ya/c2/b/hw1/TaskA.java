package ru.algo.ya.c2.b.hw1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/28730/problems/A/
Interactor

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Лена руководит разработкой тестирующей системы, в которой реализованы интерактивные задачи.

До заверщения очередной стадии проекта осталось написать модуль, определяющий итоговый вердикт системы для интерактивной
задачи. Итоговый вердикт определяется из кода завершения задачи, вердикта интерактора и вердикта чекера по следующим
правилам:

    Вердикт чекера и вердикт интерактора — это целые числа от 0 до 7 включительно. Код завершения задачи — это целое
    число от -128 до 127 включительно. Если интерактор выдал вердикт 0, итоговый вердикт равен 3 в случае, если
    программа завершилась с ненулевым кодом, и вердикту чекера в противном случае. Если интерактор выдал вердикт 1,
    итоговый вердикт равен вердикту чекера. Если интерактор выдал вердикт 4, итоговый вердикт равен 3 в случае, если
    программа завершилась с ненулевым кодом, и 4 в противном случае. Если интерактор выдал вердикт 6, итоговый вердикт
    равен 0. Если интерактор выдал вердикт 7, итоговый вердикт равен 1. В остальных случаях итоговый вердикт равен
    вердикту интерактора.

Ваша задача — реализовать этот модуль.

Формат ввода
Входной файл состоит из трёх строк. В первой задано целое число r (−128≤r≤127) — код завершения задачи, во
второй — целое число i (0≤i≤7) — вердикт интерактора, в третьей — целое число c (0≤c≤7) — вердикт чекера.

Формат вывода
Выведите одно целое число от 0 до 7 включительно — итоговый вердикт системы.
 */

public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int resultCode = Integer.parseInt(reader.readLine().trim());
            int interactorCode = Integer.parseInt(reader.readLine().trim());
            int checkerCode = Integer.parseInt(reader.readLine().trim());

            int answer = 0;
            switch (interactorCode) {
                case 0 -> answer = (resultCode == 0) ? checkerCode : 3;
                case 1 -> answer = checkerCode;
                case 4 -> answer = (resultCode == 0) ? 4 : 3;
                case 6 -> answer = 0;
                case 7 -> answer = 1;
                default -> answer = interactorCode;
            }
            System.out.println(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("0\n" +
                "0\n" +
                "0\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("-1\n" +
                "0\n" +
                "1\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("42\n" +
                "1\n" +
                "6\n");
        main(new String[0]);
        String expected = "6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("44\n" +
                "7\n" +
                "4\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("1\n" +
                "4\n" +
                "0\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        provideConsoleInput("-3\n" +
                "2\n" +
                "4\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}