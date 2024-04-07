package algo.c5.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/59540/problems/B/
Продавец рыбы

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Вася решил заняться торговлей рыбой. С помощью методов машинного обучения он предсказал цены на рыбу на N дней вперёд.
Он решил, что в один день он купит рыбу, а в один из следующих дней — продаст (то есть совершит или ровно одну покупку и
продажу или вообще не совершит покупок и продаж, если это не принесёт ему прибыли). К сожалению, рыба — товар
скоропортящийся и разница между номером дня продажи и номером дня покупки не должна превышать K.

Определите, какую максимальную прибыль получит Вася.

Формат ввода
В первой строке входных данных задаются числа N и K (1 ≤ N ≤ 10000, 1 ≤ K ≤ 100).

Во второй строке задаются цены на рыбу в каждый из N дней. Цена — целое число, которое может находится в пределах от 1
до 109.

Формат вывода
Выведите одно число — максимальную прибыль, которую получит Вася.

*/

public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int n = input[0];
            int k = input[1];
            int[] prices = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int maxProfit = 0;
            for (int indexBuy = 0; indexBuy < prices.length; indexBuy++) {
                for (int indexSell = indexBuy + 1; indexSell < prices.length; indexSell++) {
                    if (indexSell - indexBuy <= k) {
                        int profit = prices[indexSell] - prices[indexBuy];
                        if (profit > maxProfit) {
                            maxProfit = profit;
                        }
                    }
                }
            }
            System.out.println(maxProfit == Integer.MIN_VALUE ? 0 : maxProfit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_001() {
        provideConsoleInput("5 2\n" +
                "1 2 3 4 5\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("5 2\n" +
                "5 4 3 2 1\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_008() {
        provideConsoleInput("4 2\n" +
                "2 2 4 5\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
