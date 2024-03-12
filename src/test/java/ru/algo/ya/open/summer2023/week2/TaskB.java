package ru.algo.ya.open.summer2023.week2;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://contest.yandex.ru/contest/50242/problems/B/

Разноцветные палочки
Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt
Есть 10 вертикальных палочек, пронумерованных от 0 до 9 и n колец трёх цветов красного «R», зеленого «G» и голубого «B», которые на надеты на палочки. Ваша задача по строке s, кодирующей, где находится каждое из колец определить количество палочек, на которое надеты кольца всех трёх цветов.

Строка представляет из себя последовательность чётной длины, где на нечётных позициях (1, 3, 5 и т.д.) написан цвет кольца, а на чётных позициях (2, 4, 6 и т.д.) — номер палочки, на которую надето кольцо. Таким образом, кольцо номер i имеет цвет s2i−1 и находится на палочке номер s2i.

Например, строка «R2G1R1B2G2» кодирует 5 колец:

    Первое кольцо имеет красный цвет и находится на палочке 2;
    Второе кольцо имеет зеленый цвет и находится на палочке 1;
    Третье кольцо имеет красный цвет и находится на палочке 1;
    Четвертое кольцо имеет синий цвет и находится на палочке 2;
    Пятое кольцо имеет зеленый цвет и находится на палочке 2;

Формат ввода
Первая строка входных данных  — это непустая строка четной длины s (1≤|s|≤1000), состоящая из символов «R», «G» или «B» и цифр от 0 до 9.

Формат вывода
Выведите единственное целое число — количество палочек, на которых имеются кольца всех трёх цветов.
 */

public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine().trim();
            Map<Integer, Set<Character>> map = new HashMap<>();
            for (int i = 0; i < input.length() - 1; i = i + 2) {
                char color = input.charAt(i);
                int place = Integer.parseInt(String.valueOf(input.charAt(i + 1)));
                Set<Character> prev = map.getOrDefault(place, new HashSet<>());
                prev.add(color);
                map.put(place, prev);
            }

            long count = map.entrySet().stream().filter(entry -> entry.getValue().size() == 3).count();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("R2G1R1B2G2\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("R9G1B0\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}