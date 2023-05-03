package ru.algo.ya.c2.b.hw2;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/28738/problems/B/
Дома и магазины

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

На Новом проспекте построили подряд 10 зданий. Каждое здание может быть либо жилым домом, либо магазином, либо офисным
зданием.

Но оказалось, что жителям некоторых домов на Новом проспекте слишком далеко приходится идти до ближайшего магазина. Для
разработки плана развития общественного транспорта на Новом проспекте мэр города попросил вас выяснить, какое же
наибольшее расстояние приходится преодолевать жителям Нового проспекта, чтобы дойти от своего дома до ближайшего
магазина.

Формат ввода
Программа получает на вход десять чисел, разделенных пробелами. Каждое число задает тип здания на Новом проспекте: число
1 обозначает жилой дом, число 2 обозначает магазин, число 0 обозначает офисное здание. Гарантируется, что на Новом
проспекте есть хотя бы один жилой дом и хотя бы один магазин.

Формат вывода
Выведите одно целое число: наибольшее расстояние от дома до ближайшего к нему магазина. Расстояние между двумя соседними
домами считается равным 1 (то есть если два дома стоят рядом, то между ними расстояние 1, если между двумя домами есть
еще один дом, то расстояние между ними равно 2 и т.д.)
 */

public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] coords = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            Map<Integer, Integer> dist = new HashMap<>();
            int prevShop=-1;
            Deque<Integer> houses = new ArrayDeque<>();
            for (int i = 0; i < coords.length; i++) {
                if (coords[i] == 2) {
                    prevShop=i;
                    while (!houses.isEmpty()) {
                        int houseIndex = houses.poll();
                        int curDistance = i - houseIndex;
                        int prevDistance = dist.getOrDefault(houseIndex, Integer.MAX_VALUE);
                        if (prevDistance <= curDistance) break;
                        dist.put(houseIndex, curDistance);
                    }
                } else if (coords[i] == 1) {
                    houses.push(i);
                    if (prevShop!=-1) {
                        dist.put(i, i - prevShop);
                    }

                }
            }
            System.out.println(dist.values().stream().sorted(Comparator.reverseOrder()).findFirst().get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("2 0 1 1 0 1 0 2 1 2\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
    @Test
    public void test_03() {
        provideConsoleInput("0 1 0 0 1 2 2 1 0 2\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_101() {
        provideConsoleInput("2 0 0 1 0 2\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_102() {
        provideConsoleInput("0 1 0 2 0 0 1\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_103() {
        provideConsoleInput("1 2 1\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
    @Test
    public void test_104() {
        provideConsoleInput("1 2 1 1\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
    @Test
    public void test_105() {
        provideConsoleInput("2 1 2\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}