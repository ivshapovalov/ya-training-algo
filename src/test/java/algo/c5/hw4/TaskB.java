package algo.c5.hw4;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/*
https://contest.yandex.ru/contest/59542/problems/B/
Быстрый поиск в массиве

Ограничение времени 	3 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

 Дан массив из N целых чисел. Все числа от −109 до 109.

Нужно уметь отвечать на запросы вида “Cколько чисел имеют значения отL доR?”.
Формат ввода
Число N (1≤N≤105). Далее N целых чисел.

Затем число запросов K (1≤K≤105).

Далее K пар чисел L,R (−109≤L≤R≤109) — собственно запросы.

Формат вывода
Выведите K чисел — ответы на запросы.

*/

public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            long n = Long.parseLong(r.readLine());
            if (n == 0) {
                System.out.println(0);
                return;
            }
            long total = 1L;
            long amount = 1L;
            BigInteger limit = BigInteger.valueOf(n);
            long i = 0;
            for (i = 2; i <= n; i++) {
                amount = amount + i;
                total = total + amount;
                if (BigInteger.valueOf(total).add(BigInteger.valueOf(amount - 1)).compareTo(limit) > 0) {
                    break;
                }
            }
            System.out.println(i - 1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_001() {
        provideConsoleInput("7\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("2\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_009() {
        provideConsoleInput("15\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_026() {
        provideConsoleInput("9000000\n");
        main(new String[0]);
        String expected = "375\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_042() {
        provideConsoleInput("117055765888857794\n");
        main(new String[0]);
        String expected = "888887\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("4\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("5\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("6\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("0\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        provideConsoleInput("9000000000000000000\n");
        main(new String[0]);
        String expected = "3779761\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}
