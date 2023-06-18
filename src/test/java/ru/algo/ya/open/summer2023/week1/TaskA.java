package ru.algo.ya.open.summer2023.week1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/*
https://contest.yandex.ru/contest/50158/problems/A/
Строительство лесенок

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Вася занимается строительством лесенок из блоков. Лесенка состоит из ступенек, при этом i-ая ступенька должна состоять ровно из i блоков.

По заданному числу блоков n определите максимальное количество ступенек в лесенке, которую можно построить из этих блоков.

Формат ввода
Ввводится одно число n (1 ≤ n ≤ 231 - 1).

Формат вывода
Выведите одно число — количество ступенек в лесенке.

 */

public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int remain=n;
            int l = 1;
            do {
                remain-=l;
                l++;
            } while (remain -l >=0);
            System.out.println(--l);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("5\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("8\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
    @Test
    public void test_001() {
        provideConsoleInput("1\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("7\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput(Integer.MAX_VALUE+"\n");
        main(new String[0]);
        String expected = "65535\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}