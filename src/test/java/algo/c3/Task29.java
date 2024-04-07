package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
https://contest.yandex.ru/contest/45468/problems/29/
Кафе

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Около Петиного университета недавно открылось новое кафе, в котором действует следующая система скидок: при каждой
покупке более чем на 100 рублей покупатель получает купон, дающий право на один бесплатный обед (при покупке на сумму
100 рублей и меньше такой купон покупатель не получает).

Однажды Пете на глаза попался прейскурант на ближайшие N дней. Внимательно его изучив, он решил, что будет обедать в
этом кафе все N дней, причем каждый день он будет покупать в кафе ровно один обед. Однако стипендия у Пети небольшая, и
поэтому он хочет по максимуму использовать предоставляемую систему скидок так, чтобы его суммарные затраты были
минимальны. Требуется найти минимально возможную суммарную стоимость обедов и номера дней, в которые Пете следует
воспользоваться купонами.

Формат ввода
В первой строке входного файла записано целое число N (0 ≤ N ≤ 100). В каждой из последующих N строк записано одно целое
число, обозначающее стоимость обеда в рублях на соответствующий день. Стоимость — неотрицательное целое число, не
превосходящее 300.

Формат вывода
В первой строке выдайте минимальную возможную суммарную стоимость обедов. Во второй строке выдайте два числа K1 и K2 —
количество купонов, которые останутся неиспользованными у Пети после этих N дней и количество использованных им купонов
соответственно.

В последующих K2 строках выдайте в возрастающем порядке номера дней, когда Пете следует воспользоваться купонами. Если
существует несколько решений с минимальной суммарной стоимостью, то выдайте то из них, в котором значение K1 максимально
(на случай, если Петя когда-нибудь ещё решит заглянуть в это кафе). Если таких решений несколько, выведите любое из них.

*/
public class Task29 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int days = Integer.valueOf(reader.readLine().trim()) + 1;

            List<Integer> costs = new ArrayList<>();
            int[][] dp = new int[days + 1][days + 1];
            Arrays.fill(dp[0], -1);
            costs.add(0);
            for (int i = 1; i < days; i++) {
                int cost = Integer.valueOf(reader.readLine().trim());
                costs.add(cost);
                Arrays.fill(dp[i], -1);
            }
            if (days > 1) {
                if (costs.get(1) > 100) {
                    dp[1][1] = costs.get(1);
                } else {
                    dp[1][0] = costs.get(1);
                }
            }

            int useCoupon = 0;
            int skipCoupon = 0;
            int gainCoupon = 0;
            int min = 0;

            for (int day = 2; day < days; day++) {
                int cost = costs.get(day);

                for (int coupon = 0; coupon < days; coupon++) {
                    useCoupon = (cost > 0 && dp[day - 1][coupon + 1] >= 0) ?
                            dp[day - 1][coupon + 1] : Integer.MAX_VALUE;
                    skipCoupon = (cost <= 100 && dp[day - 1][coupon] >= 0) ?
                            dp[day - 1][coupon] + cost : Integer.MAX_VALUE;
                    gainCoupon = (cost > 100 && coupon >= 1 && dp[day - 1][coupon - 1] >= 0) ?
                            dp[day - 1][coupon - 1] + cost : Integer.MAX_VALUE;

                    min = Math.min(useCoupon, Math.min(skipCoupon, gainCoupon));

                    if (min == Integer.MAX_VALUE) {
                        min = -1;
                    }

                    dp[day][coupon] = min;
                }

            }

            int currentIndex = 0;
            int minTotalCost = Integer.MAX_VALUE;

            for (int coupon = days - 1; coupon >= 0; coupon--) {
                if (dp[days - 1][coupon] > 0 && dp[days - 1][coupon] < minTotalCost) {
                    minTotalCost = dp[days - 1][coupon];
                    currentIndex = coupon;
                }
            }

            int couponsLeft = currentIndex;
            int couponsUsed = 0;
            Stack<Integer> daysUsed = new Stack<>();

            for (int day = days - 1; day > 0; day--) {
                if (currentIndex < days && dp[day][currentIndex] == dp[day - 1][currentIndex + 1]) {
                    ++couponsUsed;
                    daysUsed.push(day);
                    ++currentIndex;
                } else if (currentIndex > 0 && dp[day][currentIndex] == dp[day - 1][currentIndex - 1] + costs.get(day)) {
                    --currentIndex;
                }
            }

            if (minTotalCost == Integer.MAX_VALUE) {
                minTotalCost = 0;
            }

            if (couponsLeft < 0) {
                couponsLeft = 0;
            }

            if (couponsLeft < 0) {
                couponsLeft = 0;
            }

            System.out.print(minTotalCost + "\n");
            System.out.print(couponsLeft + " " + couponsUsed + "\n");
            if (!daysUsed.isEmpty()) {
                while (!daysUsed.isEmpty()) {
                    System.out.print(daysUsed.pop() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("5\n" +
                "35\n" +
                "40\n" +
                "101\n" +
                "59\n" +
                "63\n");
        main(new String[0]);
        String expected = "235\n" +
                "0 1\n" +
                "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("7\n" +
                "100\n" +
                "32\n" +
                "44\n" +
                "24\n" +
                "100\n" +
                "40\n" +
                "50\n");
        main(new String[0]);
        String expected = "390\n" +
                "0 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02_1() {
        provideConsoleInput("5\n" +
                "101\n" +
                "56\n" +
                "1050\n" +
                "98\n" +
                "99\n");
        main(new String[0]);
        String expected = "354\n" +
                "0 1\n" +
                "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("5\n" +
                "110\n" +
                "110\n" +
                "70\n" +
                "70\n" +
                "50\n");
        main(new String[0]);
        String expected = "270\n" +
                "0 2\n" +
                "3\n" +
                "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("8\n" +
                "56\n" +
                "113\n" +
                "67\n" +
                "86\n" +
                "94\n" +
                "105\n" +
                "87\n" +
                "102");
        main(new String[0]);
        String expected = "514\n" +
                "0 2\n" +
                "5\n" +
                "8\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("1\n" +
                "300\n");
        main(new String[0]);
        String expected = "300\n" +
                "1 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        provideConsoleInput("0\n");
        main(new String[0]);
        String expected = "0\n" +
                "0 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
