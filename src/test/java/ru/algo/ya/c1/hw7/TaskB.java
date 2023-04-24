package ru.algo.ya.c1.hw7;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27883/problems/B/
Точки и отрезки

Ограничение времени 	3 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дано n отрезков на числовой прямой и m точек на этой же прямой. Для каждой из данных точек определите, скольким отрезкам
они принадлежат. Точка x считается принадлежащей отрезку с концами a и b, если выполняется двойное неравенство min(a,
b)≤ x ≤ max(a, b).

Формат ввода
Первая строка содержит два целых числа n (1≤ n ≤105) – число отрезков и m (1≤ m ≤105) – число точек. В следующих n
строках по два целых числи ai и bi – координаты концов соответствующего отрезка. В последней строке m целых чисел –
координаты точек. Все числа по модулю не превосходят 109

Формат вывода
В выходной файл выведите m чисел – для каждой точки количество отрезков, в которых она содержится.

*/
public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input =
                    Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int m = input[1];
            Map<Integer, int[]> events = new LinkedHashMap<>();
            Map<Integer, Integer> result = new LinkedHashMap<>();
            List<Integer> points = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int[] line = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                Arrays.sort(line);
                int[] prevFirst = events.getOrDefault(line[0], new int[]{0, 0});
                prevFirst[0]++;
                events.put(line[0], prevFirst);
                int[] prevLast = events.getOrDefault(line[1] + 1, new int[]{0, 0});
                prevLast[0]--;
                events.put(line[1] + 1, prevLast);
            }
            Arrays.stream(reader.readLine().trim().split(" ")).forEach(
                    el -> {
                        int[] prevCheck = events.getOrDefault(Integer.valueOf(el), new int[]{0, 0});
                        prevCheck[1]++;
                        events.put(Integer.valueOf(el), prevCheck);
                        points.add(Integer.valueOf(el));
                    }
            );
            int online = 0;
            for (Map.Entry<Integer, int[]> event :
                    events.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList())) {
                online += event.getValue()[0];
                if (event.getValue()[1] > 0) {
                    result.put(event.getKey(), online);
                }
            }
            String output =
                    points.stream().map(point -> String.valueOf(result.get(point))).collect(Collectors.joining(" "));
            System.out.println(output + " ");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_01() {
        provideConsoleInput("3 2\n" +
                "0 5\n" +
                "-3 2\n" +
                "7 10\n" +
                "1 6\n");
        main(new String[0]);
        String expected = "2 0 \n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
