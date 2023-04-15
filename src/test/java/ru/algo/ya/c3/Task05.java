package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/45468/problems/5/
Хорошая Строка

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

На день рождения маленький Ипполит получил долгожданный подарок — набор дощечек с написанными на них буквами латинского
алфавита. Теперь-то ему будет чем заняться долгими вечерами, тем более что мама обещала подарить ему в следующем году
последовательность целых неотрицательных чисел, если он хорошо освоит этот набор. Ради такого богатства Ипполит готов на
многое.

Прямо сейчас юный исследователь полностью поглощён изучением хорошести строк. Хорошестью строки называется количество
позиций от 1 до L-1 (где L — длина строки), таких, что следующая буква в строке является следующей по алфавиту.
Например, хорошесть строки "abcdefghijklmnopqrstuvwxyz" равна 25, а строки "abdc" — только 1.

Ипполит размышляет над решением закономерно возникающей задачи: чему равна максимально возможная хорошесть строки,
которую можно собрать, используя дощечки из данного набора? Вы-то и поможете ему с ней справиться.

Формат ввода
Первая строка ввода содержит единственное целое число N — количество различных букв в наборе (1≤N≤26). Обратите
внимание: в наборе всегда используются N первых букв латинского алфавита.
Следующие N строк содержат целые положительные числа ci — количество букв соответствующего типа (1≤ci≤109). Таким
образом, первое число означает количество букв "a", второе число задаёт количество букв "b" и так далее.

Формат вывода
Выведите единственное целое число — максимально возможную хорошесть строки, которую можно собрать из имеющихся дощечек.
*/

public class Task05 extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            int[] nums = new int[n];
            long answer = 0;
            int num = Integer.valueOf(reader.readLine().trim());
            nums[0] = num;
            for (int i = 1; i < n; i++) {
                num = Integer.valueOf(reader.readLine().trim());
                nums[i] = num;
                int min = Math.min(num, nums[i - 1]);
                answer += min;
            }
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "1\n" +
                "1\n" +
                "1\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("2\n" +
                "3\n" +
                "4\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("5\n" +
                "1\n" +
                "2\n" +
                "3\n" +
                "2\n" +
                "1\n");
        main(new String[0]);
        String expected = "6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("4\n" +
                "1000000000\n" +
                "1000000000\n" +
                "1000000000\n" +
                "1000000000\n");
        main(new String[0]);
        String expected = "3000000000\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
