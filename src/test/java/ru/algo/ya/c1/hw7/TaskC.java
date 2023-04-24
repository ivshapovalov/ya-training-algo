package ru.algo.ya.c1.hw7;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27883/problems/C/
Рассадка в аудитории

Ограничение времени     	1 секунда
Ограничение памяти 	        64Mb
Ввод 	                    стандартный ввод или input.txt
Вывод	                    стандартный вывод или output.txt

Экзамен по берляндскому языку проходит в узкой и длинной аудитории. На экзамен пришло N студентов. Все они посажены в
ряд. Таким образом, позиция каждого человека задается координатой на оси Ox (эта ось ведет вдоль длинной аудитории). Два
человека могут разговаривать, если расстояние между ними меньше или равно D. Какое наименьшее количество типов билетов
должен подготовить преподаватель, чтобы никакие два студента с одинаковыми билетами не могли разговаривать? Выведите
способ раздачи преподавателем билетов.

Формат ввода
В первой строке входного файла содержится два целых числа N, D (1≤N≤10000; 0≤D≤106). Вторая строка содержит
последовательность различных целых чисел X1, X2, ..., XN, где Xi (0≤Xi≤106) обозначает координату вдоль оси Ox i-го
студента

Формат вывода
В первую строчку выходного файла выведите количество вариантов, а во вторую, разделяя пробелами, номера вариантов
студентов в том порядке, в каком они перечислены во входном файле.
*/
public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input =
                    Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int d = input[1];
            int[] points =
                    Arrays.stream(reader.readLine().trim().split(" "))
                            .mapToInt(el -> Integer.valueOf(el).intValue())
                            .toArray();
            Map<Integer, Integer> indices = new HashMap<>();
            for (int i = 0; i < points.length; i++) {
                indices.put(i, points[i]);
            }

            List<Map.Entry<Integer, Integer>> sorted =
                    indices.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

            int startIndex = 0;
            Map<Integer, Integer> events = new LinkedHashMap<>();
            Map<Integer, Integer> lastPlaces = new HashMap<>();
            events.put(sorted.get(0).getKey(), 1);
            lastPlaces.put(1, 0);
            int max = 0;
            int prevMax = 0;
            int ticket = 2;
            for (int endIndex = 1; endIndex < n; endIndex++) {
                if (sorted.get(endIndex).getValue() - sorted.get(startIndex).getValue() <= d) {
                    //check by actual ticket
                    if (endIndex - ticket >= 0 && lastPlaces.containsKey(ticket) && sorted.get(endIndex).getValue() - sorted.get(lastPlaces.get(ticket)).getValue() <= d) {
                        ticket = prevMax + 1;
                        endIndex = startIndex;
                        startIndex = lastPlaces.get(1);
                        events.put(sorted.get(endIndex).getKey(), ticket);
                        lastPlaces.put(ticket, endIndex);
                    } else {
                        events.put(sorted.get(endIndex).getKey(), ticket);
                        lastPlaces.put(ticket, endIndex);
                    }
                } else {
                    max = Math.max(max, ticket - 1);
                    prevMax = ticket - 1;
                    ticket = 1;
                    events.put(sorted.get(endIndex).getKey(), ticket);
                    startIndex = endIndex;
                }
                ticket++;

            }
            max = Math.max(max, ticket - 1);

            System.out.println(max);

            System.out.println(events.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(entry -> String.valueOf(entry.getValue()))
                    .collect(Collectors.joining(" ")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_01() {
        provideConsoleInput("4 1\n" +
                "11 1 12 2\n\n");
        main(new String[0]);
        String expected = "2\n" +
                "1 1 2 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("4 0\n" +
                "11 1 12 2\n");
        main(new String[0]);
        String expected = "1\n" +
                "1 1 1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("4 100\n" +
                "11 1 12 2\n");
        main(new String[0]);
        String expected = "4\n" +
                "3 1 4 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("10 3\n" +
                "26 27 7 10 11 23 29 2 1 4");
        main(new String[0]);
        String expected = "3\n" +
                "2 3 1 2 3 1 1 2 1 3 ";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_07() {
        provideConsoleInput("30 4\n" +
                "10 37 31 29 2 40 7 36 28 48 1 12 34 22 19 41 5 25 35 16 44 8 46 18 45 6 42 32 47 43\n");
        main(new String[0]);
        String expected = "5\n" +
                "2 1 1 5 2 2 5 5 4 5 1 3 3 2 1 3 3 3 4 4 1 1 3 5 2 4 4 2 4 5 \n";


        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_07_1() {
        provideConsoleInput("30 4\n" +
                "1 2 5 6 7 8 10 12 16 18 19 22 25 28 29 31 32 34 35 36 37 40 41 42 43 44 45 46 47 48\n");
        main(new String[0]);
        String expected = "5\n" +
                "1 2 3 4 1 2 3  1  2  1  2  3  1  2  3  1  2  3  4  1  2  3  1  2  3  4  5  1  2  3 \n";

        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


    @Test
    public void test_101() {
        provideConsoleInput("4 5\n" +
                "11 5 20 27\n\n");
        main(new String[0]);
        String expected = "1\n" +
                "1 1 1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_102() {
        provideConsoleInput("7 5\n" +
                "1 5 7 15 2 3 4\n\n");
        main(new String[0]);
        String expected = "5\n" +
                "1 5 1 1 2 3 4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_103() {
        provideConsoleInput("1 5\n" +
                "5\n");
        main(new String[0]);
        String expected = "1\n" +
                "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_104() {
        provideConsoleInput("5 5\n" +
                "0 5 10 15 20\n");
        main(new String[0]);
        String expected = "2\n" +
                "1 2 1 2 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_105() {
        provideConsoleInput("5 5\n" +
                "5 5 5 5 5\n");
        main(new String[0]);
        String expected = "5\n" +
                "1 2 3 4 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_106() {
        provideConsoleInput("5 1\n" +
                "5 5 5 5 5\n");
        main(new String[0]);
        String expected = "5\n" +
                "1 2 3 4 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_107() {
        provideConsoleInput("5 5\n" +
                "1 4 5 7 8\n");
        main(new String[0]);
        String expected = "4\n" +
                "1 2 3 4 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
