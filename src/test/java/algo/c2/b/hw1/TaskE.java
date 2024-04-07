package algo.c2.b.hw1;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/28730/problems/E/
Точка и треугольник

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

На координатной плоскости расположены равнобедренный прямоугольный треугольник ABC с длиной катета d и точка X. Катеты
треугольника лежат на осях координат, а вершины расположены в точках: A (0,0), B (d,0), C (0,d).

Напишите программу, которая определяет взаимное расположение точки X и треугольника. Если точка X расположена внутри или
на сторонах треугольника, выведите 0. Если же точка находится вне треугольника, выведите номер ближайшей к ней вершины.

Формат ввода
Сначала вводится натуральное число d (не превосходящее 1000), а затем координаты точки X – два целых числа из диапазона
от –1000 до 1000.

Формат вывода
Если точка лежит внутри, на стороне треугольника или совпадает с одной из вершин, то выведите число 0. Если точка лежит
вне треугольника, то выведите номер вершины треугольника, к которой она расположена ближе всего (1 – к вершине A, 2 – к
B, 3 – к C). Если точка расположена на одинаковом расстоянии от двух вершин, выведите ту вершину, номер которой меньше.
 */

public class TaskE extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int d = Integer.parseInt(reader.readLine().trim());
            int[] coords = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int x1 = 0, y1 = 0;
            int x2 = d, y2 = 0;
            int x3 = 0, y3 = d;
            int x0 = coords[0];
            int y0 = coords[1];


            if (
                    (
                            (x1 - x0) * (y2 - y1) - (x2 - x1) * (y1 - y0) >= 0 &&
                                    (x2 - x0) * (y3 - y2) - (x3 - x2) * (y2 - y0) >= 0 &&
                                    (x3 - x0) * (y1 - y3) - (x1 - x3) * (y3 - y0) >= 0
                    )
                            ||
                            (
                                    (x1 - x0) * (y2 - y1) - (x2 - x1) * (y1 - y0) <= 0 &&
                                            (x2 - x0) * (y3 - y2) - (x3 - x2) * (y2 - y0) <= 0 &&
                                            (x3 - x0) * (y1 - y3) - (x1 - x3) * (y3 - y0) <= 0
                            )
            ) {
                System.out.println(0);
            } else {
                double distA = Math.sqrt(Math.pow(Math.abs(x0), 2) + Math.pow(Math.abs(y0), 2));
                double distB = Math.sqrt(Math.pow(Math.abs(x0 - d), 2) + Math.pow(Math.abs(y0), 2));
                double distC = Math.sqrt(Math.pow(Math.abs(x0), 2) + Math.pow(Math.abs(y0 - d), 2));
                Map<Integer, Double> map = new HashMap<>();
                map.put(1, distA);
                map.put(2, distB);
                map.put(3, distC);
                Map.Entry<Integer, Double> result =
                        map.entrySet().stream().sorted((entry1, entry2) -> (Math.abs(entry1.getValue() - entry2.getValue()) < 1E-10)
                                ? entry1.getKey() - entry2.getKey()
                                : (int) (entry1.getValue() - entry2.getValue())).findFirst().get();
                System.out.println(result.getKey());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("5\n" +
                "1 1\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("3\n" +
                "-1 -1\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("4\n" +
                "4 4\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("4\n" +
                "2 2\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        provideConsoleInput("10\n" +
                "12 -1\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
