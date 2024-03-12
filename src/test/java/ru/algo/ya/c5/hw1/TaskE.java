package ru.algo.ya.c5.hw1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/53029/problems/E/
Прибыльный стартап

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

k друзей организовали стартап по производству укулеле для кошек. На сегодняшний день прибыль составила n рублей. Вы, как
главный бухгалтер компании, хотите в каждый из ближайших d дней приписывать по одной цифре в конец числа, выражающего
прибыль. При этом в каждый из дней прибыль должна делиться на k.

Формат ввода
В единственной строке входных данных через
пробел записаны три числа: n,k,d — изначальная прибыль, количество учредителей компании и количество дней, которое вы
собираетесь следить за прибылью (1≤n,k≤109,1≤d≤105). НЕ гарантируется, что n делится на k.

Формат вывода
Выведите одно
целое число x — прибыль компании через d дней. Первые цифры числа x должны совпадать с числом n. Все префиксы числа x,
которые длиннее числа n на 1,2,…,d цифр, должны делиться на k. Если возможных ответов несколько, выведите любой из них.
Если ответа не существует, выведите −1.

*/

public class TaskE extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            long n = input[0];
            int k = input[1];
            int d = input[2];

            StringBuilder result = new StringBuilder(String.valueOf(n));
            boolean divided = false;
            for (int i = 1; i <= d; i++) {
                boolean found = false;
                for (int j = 0; j < 9; j++) {
                    if (divided) {
                        if (j % k == 0) {
                            result.append(j);
                            found = true;
                            break;
                        }
                    } else {
                        long nextN = Long.parseLong(String.valueOf(n) + j);
                        if (nextN % k == 0) {
                            divided = true;
                            n = nextN;
                            if (!result.isEmpty()) {
                                result.delete(0, result.length());
                            }
                            result.append(String.valueOf(n));
                            found = true;
                            break;
                        }
                    }

                }
                if (!found) {
                    result = new StringBuilder("-1");
                    break;
                }
            }
            System.out.println(result.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_001() {
        provideConsoleInput("21 108 1\n");
        main(new String[0]);
        String expected = "216\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("5 12 4\n");
        main(new String[0]);
        String expected = "-1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_029() throws FileNotFoundException {
        String testNumber = "029";
        provideConsoleInput("1000000000 1000000000 100000\n");
        main(new String[0]);
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        String expected = getFileContent(answerFilePath);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
