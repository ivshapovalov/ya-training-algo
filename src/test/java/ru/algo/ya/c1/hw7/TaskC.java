package ru.algo.ya.c1.hw7;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27883/problems/C/
Рассадка в аудитории
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


            int[] temp = Arrays.copyOf(points, n);
            Arrays.sort(temp);

            int prevIndex = 0;
            int num = 1;
            Map<Integer, Integer> events = new LinkedHashMap<>();
            events.put(temp[0], num);
            int max = 0;
            for (int i = 1; i < n; i++) {
                if (temp[i] - temp[prevIndex] <= d) {
                    events.put(temp[i], ++num);
                } else {
                    max = Math.max(max, num);
                    num = 0;
                    events.put(temp[i], ++num);
                    prevIndex = i;
                }
            }
            System.out.println(max);
            System.out.println(Arrays.stream(points)
                    .mapToObj(el -> String.valueOf(events.get(el)))
                    .collect(Collectors.joining(" ")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_1() {
        provideConsoleInput("4 1\n" +
                "11 1 12 2\n\n");
        main(new String[0]);
        String expected = "2\n" +
                "1 1 2 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_2() {
        provideConsoleInput("4 0\n" +
                "11 1 12 2\n\n");
        main(new String[0]);
        String expected = "1\n" +
                "1 1 1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_3() {
        provideConsoleInput("4 5\n" +
                "11 5 20 27\n\n");
        main(new String[0]);
        String expected = "1\n" +
                "1 1 1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_4() {
        provideConsoleInput("7 5\n" +
                "1 5 7 15 2 3 4\n\n");
        main(new String[0]);
        String expected = "5\n" +
                "1 5 1 1 2 3 4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
