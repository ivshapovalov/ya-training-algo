package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/45468/problems/4/
Контрольная работа

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Петя и Вася — одноклассники и лучшие друзья, поэтому они во всём помогают друг другу. Завтра у них контрольная по
математике, и учитель подготовил целых K вариантов заданий.

В классе стоит один ряд парт, за каждой из них (кроме, возможно, последней) на контрольной будут сидеть ровно два
ученика. Ученики знают, что варианты будут раздаваться строго по порядку: правый относительно учителя ученик первой
парты получит вариант 1, левый — вариант 2, правый ученик второй парты получит вариант 3 (если число вариантов больше
двух) и т.д. Так как K может быть меньше чем число учеников N, то после варианта K снова выдаётся вариант 1. На
последней парте в случае нечётного числа учеников используется только место 1.

Петя самым первым вошёл в класс и сел на своё любимое место. Вася вошёл следом и хочет получить такой же вариант, что и
Петя, при этом сидя к нему как можно ближе. То есть между ними должно оказаться как можно меньше парт, а при наличии
двух таких мест с равным расстоянием от Пети Вася сядет позади Пети, а не перед ним. Напишите программу, которая
подскажет Васе, какой ряд и какое место (справа или слева от учителя) ему следует выбрать. Если же один и тот же вариант
Вася с Петей писать не смогут, то выдайте одно число -1.

Формат ввода
В первой строке входных данных находится количество учеников в классе 2≤N≤109. Во второй строке — количество
подготовленных для контрольной вариантов заданий 2≤K≤N. В третьей строке — номер ряда, на который уже сел Петя, в
четвёртой — цифра 1, если он сел на правое место, и 2, если на левое.

Формат вывода
Если Вася никак не сможет писать тот же вариант, что и Петя, то выведите -1. Если решение существует, то выведите два
числа — номер ряда, на который следует сесть Васе, и 1, если ему надо сесть на правое место, или 2, если на левое.
Разрешается использовать только первые N мест в порядке раздачи вариантов.
*/

public class Task04 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            int k = Integer.valueOf(reader.readLine().trim());
            int row = Integer.valueOf(reader.readLine().trim());
            int col = Integer.valueOf(reader.readLine().trim());

            int num = (row - 1) * 2 + col;
            int numBefore = (num - k);
            int rowBefore = getRowFromNumber(numBefore);
            int rowsBetweenBefore = Math.abs(rowBefore - row) - 1;
            int numAfter = (num + k);
            int rowAfter = getRowFromNumber(numAfter);
            int rowsBetweenAfter = Math.abs(rowAfter - row) - 1;
            if (numBefore <= 0 && numAfter > n) {
                System.out.println(-1);
            } else if (numBefore <= 0) {
                int colAfter = numAfter % 2 == 0 ? 2 : 1;
                System.out.println(rowAfter + " " + colAfter);
            } else if (numAfter > n) {
                int colBefore = numBefore % 2 == 0 ? 2 : 1;
                System.out.println(rowBefore + " " + colBefore);
            } else if (rowsBetweenBefore >= rowsBetweenAfter) {
                int colAfter = numAfter % 2 == 0 ? 2 : 1;
                System.out.println(rowAfter + " " + colAfter);
            } else if (rowsBetweenBefore < rowsBetweenAfter) {
                int colBefore = numBefore % 2 == 0 ? 2 : 1;
                System.out.println(rowBefore + " " + colBefore);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int getRowFromNumber(int number) {
        return number / 2 + (number % 2 == 0 ? 0 : 1);
    }

    @Test
    public void test_01() {
        provideConsoleInput("25\n" +
                "2\n" +
                "1\n" +
                "2\n\n");
        main(new String[0]);
        String expected = "2 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("25\n" +
                "13\n" +
                "7\n" +
                "1\n\n");
        main(new String[0]);
        String expected = "-1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("11\n" +
                "5\n" +
                "3\n" +
                "2\n\n");
        main(new String[0]);
        String expected = "1 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("12\n" +
                "6\n" +
                "3\n" +
                "2\n\n");
        main(new String[0]);
        String expected = "6 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("12\n" +
                "6\n" +
                "6\n" +
                "2\n\n");
        main(new String[0]);
        String expected = "3 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
