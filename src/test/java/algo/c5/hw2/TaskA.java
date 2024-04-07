package algo.c5.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/59540/problems/A/
Минимальный прямоугольник

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

На клетчатой плоскости закрашено K клеток. Требуется найти минимальный по площади прямоугольник, со сторонами,
параллельными линиям сетки, покрывающий все закрашенные клетки.

Формат ввода
Во входном файле, на первой строке, находится число K (1 ≤ K ≤ 100). На следующих K строках находятся пары чисел Xi и Yi
— координаты закрашенных клеток (|Xi|, |Yi| ≤ 109).

Формат вывода
Выведите в выходной файл координаты левого нижнего и правого верхнего углов прямоугольника.

*/

public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int k = Integer.parseInt(r.readLine());

            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;
            for (int i = 1; i <= k; i++) {
                int[] coords = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
                minX = Math.min(minX, coords[0]);
                minY = Math.min(minY, coords[1]);
                maxX = Math.max(maxX, coords[0]);
                maxY = Math.max(maxY, coords[1]);
            }
            System.out.println(minX + " " + minY + " " + maxX + " " + maxY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_001() {
        provideConsoleInput("4\n" +
                "1 3\n" +
                "3 1\n" +
                "3 5\n" +
                "6 3\n");
        main(new String[0]);
        String expected = "1 1 6 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
