package ru.algo.ya.open.summer2023.week1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MINUTES;

/*
https://contest.yandex.ru/contest/50158/problems/D/
Разница во времени

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Каждые сутки на вокзал прибывает n электричек. По заданному расписанию прибытия электричек определите минимальное время между прибытием двух разных электричек.

Формат ввода
В первой строке задано число n (1 ≤ n ≤ 2 × 104) — количество электричек.
Во второй строке задано n моментов времени в формате HH:MM (0 ≤ HH ≤ 23, 0 ≤ MM ≤ 59) через пробел.

Формат вывода
Выведите одно число — минимальное время в минутах между прибытием двух электричек.
 */

public class TaskD extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int num = Integer.parseInt(reader.readLine().trim());
            String[] schedule = reader.readLine().trim().split(" ");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            List<LocalTime> sortedSchedule = Arrays.stream(schedule)
                    .map(time -> LocalTime.parse(time, dtf))
                    .sorted(new Comparator<LocalTime>() {
                        @Override
                        public int compare(LocalTime o1, LocalTime o2) {
                            return o1.compareTo(o2);
                        }
                    }).collect(Collectors.toList());
            long diff = Integer.MAX_VALUE;
            for (int i = 0; i < sortedSchedule.size() - 1; i++) {
                diff = Math.min(diff, Math.abs(MINUTES.between(sortedSchedule.get(i), sortedSchedule.get(i + 1))));
            }
            diff = Math.min(diff,
                    1440 - Math.abs(MINUTES.between(sortedSchedule.get(0),
                            sortedSchedule.get(sortedSchedule.size() - 1))));

            System.out.println(diff);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("2\n" +
                "23:59 00:00\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("3\n" +
                "00:00 23:59 00:00\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("3\n" +
                "23:59 00:02 00:10\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}