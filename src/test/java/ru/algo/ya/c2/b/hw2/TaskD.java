package ru.algo.ya.c2.b.hw2;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/28738/problems/D/
Лавочки в атриуме

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В атриуме нового корпуса ФНК урбанисты установили модные гранитные лавочки (на которых холодно сидеть зимой и жарко
летом). Лавочка устроена следующим образом: несколько одинаковых кубических гранитных блоков ставятся в ряд, а на них
кладется гранитная плита.

При этом блоки располагаются так, чтобы плита не падала: для этого достаточно, чтобы и слева, и справа от центра плиты
был хотя бы один гранитный блок или его часть (в частности, если центр плиты приходится на середину какого-нибудь блока,
то и слева, и справа от центра плиты находится часть блока, и плита не падает).

На ФНК много певокурсников (но это только пока не произошли отчисления за списывания на курсе ОиМП) и им не хватает
стульев в аудиториях. Студенты обнаружили, что блоки можно использовать в аудитории в качестве сиденья. Можно по одному
вытаскивать блоки, находящиеся с краю (как слева, так и справа). Они хотят вытащить из-под лавочки как можно больше
блоков так, чтобы она при этом не упала (передвигать оставшиеся блоки нельзя). Определите, какие блоки они должны
оставить.

Формат ввода
В первой строке входных данных содержатся два числа: L — длина лавочки и K — количество гранитных блоков-ножек. Оба
числа натуральные и не превышают 10 000.

Во второй строке следуют K различных целых неотрицательных чисел, задающих положение каждой ножки. Положение ножки
определяется расстоянием от левого края плиты до левого края ножки (ножка — это куб размером 1×1×1). Ножки перечислены
слева направо (то есть начиная с ножки с меньшим расстоянием до левого края).

Формат вывода
Требуется перечислить ножки, которые студентам нужно оставить. Для каждой ножки нужно выдать ее положение, как оно
задано во входных данных. Ножки следует перечислять слева направо, в том порядке, в котором они встречаются во входных
данных.
 */

public class TaskD extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int L = input[0];
            int K = input[1];
            int[] legs = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            if (L % 2 == 1) {
                int foundIndex = Arrays.binarySearch(legs, L / 2);
                if (foundIndex > 0) {
                    System.out.println(legs[foundIndex]);
                } else {
                    foundIndex = -foundIndex - 1;
                    if (foundIndex >= legs.length) {
                        System.out.println(legs[legs.length - 1]);
                    } else {
                        System.out.println(legs[foundIndex - 1] + " " + legs[foundIndex]);
                    }
                }
            } else {
                int foundIndex = Arrays.binarySearch(legs, L / 2 - 1);
                if (foundIndex > 0) {
                    System.out.println(legs[foundIndex] + " " + legs[foundIndex + 1]);
                } else {
                    foundIndex = -foundIndex - 1;
                    if (foundIndex >= legs.length) {
                        System.out.println(legs[legs.length - 1]);
                    } else {
                        System.out.println(legs[foundIndex - 1] + " " + legs[foundIndex]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("5 2\n" +
                "0 2\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("13 4\n" +
                "1 4 8 11\n");
        main(new String[0]);
        String expected = "4 8\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("14 6\n" +
                "1 6 8 11 12 13\n");
        main(new String[0]);
        String expected = "6 8\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("4 4\n" +
                "0 1 2 3");
        main(new String[0]);
        String expected = "1 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}