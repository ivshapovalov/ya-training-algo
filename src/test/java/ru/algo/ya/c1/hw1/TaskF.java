package ru.algo.ya.c1.hw1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://contest.yandex.ru/contest/27393/problems/F/
Расстановка ноутбуков
*/

public class TaskF extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el.trim()).intValue()).toArray();
            int x1 = input[0];
            int y1 = input[1];
            int x2 = input[2];
            int y2 = input[3];

            int length1 = Math.max(x1, y1);
            int width1 = Math.min(x1, y1);
            int length2 = Math.max(x2, y2);
            int width2 = Math.min(x2, y2);

            Map<Integer, List<String>> cases = new HashMap<>();
            Integer square = (length1 + length2) * Math.max(width1, width2);
            List<String> squareCases = cases.getOrDefault(square, new ArrayList<>());
            squareCases.add((length1 + length2) + " " + Math.max(width1, width2));
            cases.put(square, squareCases);

            square = (width1 + length2) * Math.max(length1, width2);
            squareCases = cases.getOrDefault(square, new ArrayList<>());
            squareCases.add((width1 + length2) + " " + Math.max(length1, width2));
            cases.put(square, squareCases);

            square = (length1 + width2) * Math.max(width1, length2);
            squareCases = cases.getOrDefault(square, new ArrayList<>());
            squareCases.add((length1 + width2) + " " + Math.max(width1, length2));
            cases.put(square, squareCases);

            square = (width1 + width2) * Math.max(length1, length2);
            squareCases = cases.getOrDefault(square, new ArrayList<>());
            squareCases.add((width1 + width2) + " " + Math.max(length1, length2));
            cases.put(square, squareCases);

            System.out.println(cases.entrySet().stream()
                    .min(Comparator.comparingInt(el -> el.getKey())).get().getValue().get(0));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("10 2 2 10\n");
        main(new String[0]);
        String expected = "20 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("5 7 3 2\n");
        main(new String[0]);
        String expected = "9 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
