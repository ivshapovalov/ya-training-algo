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
https://contest.yandex.ru/contest/27883/problems/E/
Кассы
*/
public class TaskE extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            Map<Integer, Integer> events = new LinkedHashMap<>();
            for (int i = 0; i < n; i++) {
                int[] window = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                int timeOpen = window[0] * 60 + window[1];
                int timeClose = window[2] * 60 + window[3];
                if (timeOpen == timeClose) {
                    events.put(0, events.getOrDefault(0, 0) + 1);
                } else if (timeOpen > timeClose) {
                    events.put(0, events.getOrDefault(0, 0) + 1);
                    events.put(timeClose, events.getOrDefault(timeClose, 0) - 1);
                    events.put(timeOpen, events.getOrDefault(timeOpen, 0) + 1);
                } else {
                    events.put(timeOpen, events.getOrDefault(timeOpen, 0) + 1);
                    events.put(timeClose, events.getOrDefault(timeClose, 0) + -1);
                }
            }
            int online = 0;
            int timeStarted = -1;
            int time = 0;
            for (Map.Entry<Integer, Integer> event :
                    events.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList())) {
                online += event.getValue();
                if (online == n) {
                    timeStarted = event.getKey();
                } else if (timeStarted != -1) {
                    time += event.getKey() - timeStarted;
                    timeStarted = -1;
                }

            }
            if (timeStarted != -1) {
                time += 24 * 60 - timeStarted;
            }
            System.out.println(time);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_1() {
        provideConsoleInput("3\n" +
                "1 0 23 0\n" +
                "12 0 12 0\n" +
                "22 0 2 0\n");
        main(new String[0]);
        String expected = "120\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_3() {
        provideConsoleInput("2\n" +
                "9 30 14 0\n" +
                "14 15 21 0\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_2() {
        provideConsoleInput("2\n" +
                "14 00 18 00\n" +
                "10 00 14 01\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
