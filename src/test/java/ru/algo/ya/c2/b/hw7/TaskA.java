package ru.algo.ya.c2.b.hw7;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/29396/problems/A/
Закраска прямой

Ограничение времени 	3 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

На числовой прямой окрасили N отрезков. Известны координаты левого и правого концов каждого отрезка (Li и Ri). Найти
длину окрашенной части числовой прямой.

Формат ввода В первой строке находится число N, в следующих N строках - пары Li и Ri. Li и Ri - целые, -109 ≤ Li ≤ Ri ≤
109, 1 ≤ N ≤ 15 000

Формат вывода Вывести одно число - длину окрашенной части прямой.

 */

public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine().trim());
            int[][] points = new int[N][2];
            for (int i = 0; i < N; i++) {
                int[] point = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                points[i] = point;
            }
            Arrays.sort(points, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
            int r = points[0][1];
            int l = points[0][0];
            int len = 0;
            for (int i = 1; i < N; i++) {
                int[] point = points[i];
                if (point[0] > r) {
                    len += (r - l);
                    l = point[0];
                    r = point[1];
                } else {
                    if (point[1] > r) {
                        r = point[1];
                    }
                }
            }
            len += (r - l);
            System.out.println(len);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1\n" +
                "10 20\n");
        main(new String[0]);
        String expected = "10\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1\n" +
                "10 10\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("2\n" +
                "10 20\n" +
                "20 40\n");
        main(new String[0]);
        String expected = "30\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}