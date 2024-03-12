package ru.algo.ya.open.summer2023.week1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/50158/problems/C/
Купить и продать

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

У вас есть 1000$, которую вы планируете эффективно вложить. Вам даны цены за 1000 кубометров газа за n дней. Можно один раз купить газ на все деньги в день i и продать его в один из последующих дней j, i < j.

Определите номера дней для покупки и продажи газа для получения максимальной прибыли.

Формат ввода
В первой строке вводится число дней n (1 ≤ n ≤ 100000).
Во второй строке вводится n чисел — цены за 1000 кубометров газа в каждый из дней. Цена — целое число от 1 до 5000. Дни нумеруются с единицы.

Формат вывода
Выведите два числа i и j — номера дней для покупки и продажи газа. Если прибыль получить невозможно, выведите два нуля.

 */

public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int days = Integer.parseInt(reader.readLine().trim());
            int[] prices = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int[] maxResult = new int[]{0, 0, 0};
            int iBuy = 0;
            int maxProfit = 0;
            for (int iSell = 1; iSell < prices.length; iSell++) {
                if (prices[iSell] < prices[iBuy]) iBuy = iSell;
                int amount = 1000 / prices[iBuy];
                int profit = amount * (prices[iSell] - prices[iBuy]);
                if (profit >= maxProfit) {
                    maxProfit = profit;
                    maxResult = new int[]{profit, iBuy, iSell};
                }
            }
            if (maxResult[0] <= 0) {
                System.out.println("0 0");
            } else {
                System.out.println((maxResult[1] + 1) + " " + (maxResult[2] + 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("6\n" +
                "10 3 5 3 11 9\n");
        main(new String[0]);
        String expected = "2 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("4\n" +
                "5 5 5 5\n");
        main(new String[0]);
        String expected = "0 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("4\n" +
                "5 4 3 2\n");
        main(new String[0]);
        String expected = "0 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("1\n" +
                "5\n");
        main(new String[0]);
        String expected = "0 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("4\n" +
                "5 9 4 6\n");
        main(new String[0]);
        String expected = "1 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_004() {
        provideConsoleInput("4\n" +
                "5 9 4 9\n");
        main(new String[0]);
        String expected = "3 4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_005() {
        provideConsoleInput("6\n" +
                "5 8 4 9 3 5\n");
        main(new String[0]);
        String expected = "3 4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_006() {
        provideConsoleInput("6\n" +
                "10 8 7 6 5 5\n");
        main(new String[0]);
        String expected = "0 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_007() {
        provideConsoleInput("4\n" +
                "10000 20000 1000 3000\n");
        main(new String[0]);
        String expected = "3 4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}