package algo.c5.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
https://contest.yandex.ru/contest/59541/problems/G/
Построить квадрат

Ограничение времени 	2 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Задано множество, состоящее из N различных точек на плоскости. Координаты всех точек — целые числа. Определите, какое
минимальное количество точек нужно добавить во множество, чтобы нашлось четыре точки, лежащие в вершинах квадрата.

Формат ввода
В первой строке вводится число N (1 ≤ N ≤ 2000) — количество точек.

В следующих N строках вводится по два числа xi, yi (-108 ≤ xi, yi ≤ 108) — координаты точек.

Формат вывода
В первой строке выведите число K — минимальное количество точек, которые нужно добавить во множество.

В следующих K строках выведите координаты добавленных точек xi, yi через пробел. Координаты должны быть целыми и не
превышать 109 по модулю.

Если решений несколько — выведите любое из них.


*/

public class TaskG extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(r.readLine());
            List<Point> points = new ArrayList<>();
            Map<Integer, Set<Integer>> mapX = new HashMap<>();
            Map<Integer, Set<Integer>> mapY = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int[] coords = Arrays.stream(r.readLine().split(" ")).mapToInt(el -> Integer.parseInt(el)).toArray();
                points.add(new Point(coords[0], coords[1]));

                Set<Integer> listY = mapX.getOrDefault(coords[0], new HashSet<>());
                listY.add(coords[1]);
                mapX.put(coords[0], listY);

                Set<Integer> listX = mapY.getOrDefault(coords[1], new HashSet<>());
                listX.add(coords[0]);
                mapY.put(coords[1], listX);
            }
            int maxPoints = 0;
            List<Point> neededPoints = new ArrayList<>();
            br0:
            for (Point point : points) {
                int x = point.x;
                int y = point.y;
                List<Integer> listY = new ArrayList<>(mapX.get(x));
                List<Integer> listX = new ArrayList<>(mapY.get(y));
                for (Integer nextY : listY) {
                    if (nextY == y) continue;
                    int nextX = nextY + Math.abs(nextY - y);
                    if (listX.contains(nextX)) {
                        if (points.stream().filter(p -> p.x == nextX && p.y == nextY).findAny().isPresent()) {
                            maxPoints = 4;
                            neededPoints = new ArrayList<>();
                            break br0;
                        } else {
                            if (maxPoints < 3) {
                                maxPoints = 3;
                                neededPoints = List.of(new Point(nextX, nextY));
                            }
                        }
                    } else {
                        if (maxPoints < 2) {
                            maxPoints = 2;
                            neededPoints = List.of(new Point(x + nextY, nextY), new Point(x + nextY, y));
                        }
                    }
                }
            }
            System.out.println(4 - maxPoints);
            neededPoints.forEach(point -> System.out.println(point.x + " " + point.y));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_001() {
        provideConsoleInput("2\n" +
                "0 1\n" +
                "1 0\n");
        main(new String[0]);
        String expected = "2\n" +
                "0 0\n" +
                "1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("3\n" +
                "0 2\n" +
                "2 0\n" +
                "2 2\n");
        main(new String[0]);
        String expected = "1\n" +
                "0 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("4\n" +
                "-1 1\n" +
                "1 1\n" +
                "-1 -1\n" +
                "1 -1\n");
        main(new String[0]);
        String expected = "0  \n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    record Point(int x, int y) {
    }

}
