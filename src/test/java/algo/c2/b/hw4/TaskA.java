package algo.c2.b.hw4;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/28970/problems/A/
Толя-Карп и новый набор структур, часть 2

 	                    Все языки 	            Scala 2.13.4
Ограничение времени 	3 секунды 	            5 секунд
Ограничение памяти 	    255Mb 	                256Mb
Ввод 	            стандартный ввод или input.txt
Вывод 	            стандартный вывод или output.txt

Толя-Карп запросил для себя n посылок с «Аллигатор-экспресс».

Посылка представляет из себя ящик. Внутри ящика лежит целое число ai. Номер на ящике di указывает на цвет числа,
лежащего внутри.

Толю-Карпа интересует, чему будут равны значения чисел, если сложить между собой все те, что имеют одинаковый цвет.
Напишите, пожалуйста, программу, которая выводит результат.

Формат ввода
В первой строке одно число n (0 ≤ n ≤ 2*105).

В следующих n строках заданы по два числа: цвет числа в ящике di и значение числа ai (-1018 ≤ di, ai ≤ 1018).

Гарантируется, что сумма чисел одного цвета не превышает 1018.

Формат вывода
Выведите в порядке возрастания номера цвета пары чисел, каждая в новой строке: номер цвета и сумму всех чисел данного
цвета.

 */

public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().trim());
            Map<Long, Long> colors = new HashMap<>();
            for (int i = 0; i < n; i++) {
                List<Long> input = Arrays.stream(reader.readLine().trim().split(" "))
                        .map(el -> Long.valueOf(el)).collect(Collectors.toList());
                long color = input.get(0);
                long num = input.get(1);
                colors.put(color, colors.getOrDefault(color, 0L) + num);
            }
            colors.entrySet().stream().sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("7\n" +
                "1 5\n" +
                "10 -5\n" +
                "1 10\n" +
                "4 -2\n" +
                "4 3\n" +
                "4 1\n" +
                "4 0\n");
        main(new String[0]);
        String expected = "1 15\n" +
                "4 2\n" +
                "10 -5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("5\n" +
                "5 -10000\n" +
                "-5 100000000000\n" +
                "10 2000000000000\n" +
                "-5 -300000000000\n" +
                "0 10000000000000\n");
        main(new String[0]);
        String expected = "-5 -200000000000\n" +
                "0 10000000000000\n" +
                "5 -10000\n" +
                "10 2000000000000\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
