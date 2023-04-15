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
https://contest.yandex.ru/contest/27883/problems/D/
Реклама
*/
public class TaskD extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            Map<Integer, Integer> events = new LinkedHashMap<>();
            for (int i = 0; i < n; i++) {
                int[] client = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                if (client[1] - client[0] >= 5) {
                    events.put(client[0], events.getOrDefault(client[0], 0) + 1);
                    events.put(client[1], events.getOrDefault(client[1] + 1, 0) - 1);
                }
            }

            int prev = 0;
            int sum = 0;
            int online = 0;
            for (Map.Entry<Integer, Integer> event :
                    events.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList())) {
                online += event.getValue();

            }
            sum += n - prev;
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_1() {
        provideConsoleInput("10 3\n" +
                "1 3\n" +
                "2 4\n" +
                "9 9\n\n\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_3() {
        provideConsoleInput("10 3\n" +
                "1 3\n" +
                "3 4\n" +
                "4 9\n\n\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_2() {
        provideConsoleInput("10 2\n" +
                "1 1\n" +
                "1 2\n");
        main(new String[0]);
        String expected = "8\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_4() {
        provideConsoleInput("10 1\n" +
                "5 6\n");
        main(new String[0]);
        String expected = "8\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_5() {
        provideConsoleInput("10 1\n" +
                "0 0\n");
        main(new String[0]);
        String expected = "9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_6() {
        provideConsoleInput("10 3\n" +
                "0 4\n" +
                "1 3\n" +
                "5 7\n"

        );
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}
