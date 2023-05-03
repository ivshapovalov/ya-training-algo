package ru.algo.ya.c2.b.hw1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/28730/problems/B/
Кольцевая линия метро

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Витя работает недалеко от одной из станций кольцевой линии Московского метро, а живет рядом с другой станцией той же
линии. Требуется выяснить, мимо какого наименьшего количества промежуточных станций необходимо проехать Вите по кольцу,
чтобы добраться с работы домой.

Формат ввода
Станции пронумерованы подряд натуральными числами 1, 2, 3, …, N (1-я станция – соседняя с N-й), N не превосходит 100.

Вводятся три числа: сначала N – общее количество станций кольцевой линии, а затем i и j – номера станции, на которой
Витя садится, и станции, на которой он должен выйти. Числа i и j не совпадают. Все числа разделены пробелом.

Формат вывода
Требуется выдать минимальное количество промежуточных станций (не считая станции посадки и высадки), которые необходимо
проехать Вите.
 */

public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int i = input[1];
            int j = input[2];
            int answer = Math.min(Math.abs(i - j) - 1, n - Math.abs(i - j) - 1);
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("100 5 6\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("10 1 9\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}