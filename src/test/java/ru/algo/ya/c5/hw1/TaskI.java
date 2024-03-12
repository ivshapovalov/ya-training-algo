package ru.algo.ya.c5.hw1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/53029/problems/I/
Расписание
Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Как же Илье надоело учиться! Сначала школа, потом университет... Вот, наконец, наступил тот долгожданный день, когда
Илье утром не надо ехать на учебу. Но, к несчастью для Ильи, оказалось, что после окончания университета начинается
самое трудное — надо устраиваться на работу.

Во всемирно известной фирме «Goondex», в которую устроился Илья, принято очень много работать, в частности, для
сотрудников установлена шестидневная рабочая неделя. Но, в качестве бонуса, «Goondex» каждый год предлагает своим
сотрудникам выбрать любой день недели в качестве выходного. В свою очередь, оставшиеся шесть дней недели будут рабочими.

Илья сообразил, что с учётом государственных праздников (которые всегда являются выходными) с помощью правильного выбора
выходного дня недели можно варьировать количество рабочих дней в году. Теперь он хочет знать, какой день недели ему
следует выбрать в качестве выходного, чтобы отдыхать как можно больше дней в году, или, наоборот, демонстрировать чудеса
трудолюбия, работая по максимуму.

Формат ввода
В первой строке входных данных находится одно целое число N (0 ≤ N ≤ 366) — количество государственных праздников.

Во второй строке содержится одно целое число year (1800 ≤ year ≤ 2100) — год, в который необходимо помочь Илье.

В каждой из последующих N строк расположено по паре чисел day month (day — целое число, month — слово, между day и month
ровно один пробел), обозначающих, что день day месяца month является государственным праздником.

В последней строке расположено слово  — день недели первого января в год year.

Гарантируется, что все даты указаны корректно (в том числе указанный день недели первого января действительно является
днём недели первого января соответствующего года year) и все дни государственных праздников различны.

Формат вывода

Выведите через пробел два дня недели — лучший и худший варианты дней недели для выходного (то есть дни недели, для
которых достигается соответственно максимальное и минимальное количество выходных дней в году). Если возможных вариантов
ответа несколько, выведите любой из них.

*/

public class TaskI extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(r.readLine());
            int year = Integer.parseInt(r.readLine());
            Map<String, Integer> holidays = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                String[] input = r.readLine().split(" ");
                Integer day = Integer.parseInt(input[0]);
                LocalDate ld = LocalDate.of(year, Month.valueOf(input[1].toUpperCase()), day);
                String weekday = ld.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.of("en"));
                holidays.put(weekday, holidays.getOrDefault(weekday, 0) + 1);
            }
            String firstDayOfYear = r.readLine();
            LocalDate currentDay = LocalDate.of(year, 1, 1);
            Map<String, Integer> weekdays = new HashMap<>();
            while (currentDay.getYear() == year) {
                String weekday = currentDay.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.of("en"));
                weekdays.put(weekday, weekdays.getOrDefault(weekday, 0) + 1);
                currentDay = currentDay.plusDays(1);
            }
            ArrayList<String> weekdayNames = new ArrayList<>(weekdays.keySet());
            for (String weekdayName : weekdayNames) {
                weekdays.put(weekdayName, weekdays.getOrDefault(weekdayName, 0) - holidays.getOrDefault(weekdayName, 0));
            }
            List<Map.Entry<String, Integer>> collect = weekdays.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
            System.out.println( collect.get(collect.size() - 1).getKey()+" " +collect.get(0).getKey());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_001() {
        provideConsoleInput("2\n" +
                "2015\n" +
                "1 January\n" +
                "8 January\n" +
                "Thursday\n");
        main(new String[0]);
        String expected = "Monday Thursday\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("3\n" +
                "2013\n" +
                "1 January\n" +
                "8 January\n" +
                "15 January\n" +
                "Tuesday\n");
        main(new String[0]);
        String expected = "YES\n" +
                "0.3000000000\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("3\n" +
                "2013\n" +
                "6 February\n" +
                "13 February\n" +
                "20 February\n" +
                "Tuesday\n");
        main(new String[0]);
        String expected = "Tuesday Wednesday\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
