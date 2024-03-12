package ru.algo.ya.c4.hw2;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/53030/problems/С/
Z-функция

Ограничение времени 	2 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дана непустая строка S, длина которой N не превышает 106. Будем считать, что элементы строки нумеруются от 0 до N-1.

Вычислите z-функцию z[i] для всех i от 0 до N-1. z[i] определяется как максимальная длина подстроки, начинающейся с
позиции i и совпадающей с префиксом всей строки. z[0] = 0

Формат ввода
Одна строка длины N, 0 < N ≤ 106, состоящая из прописных латинских букв.

Формат вывода
Выведите N чисел — значения z-функции для каждой позиции, разделённые пробелом.

*/

public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String s = " " + r.readLine().trim();
            StringHash stringHash = new StringHash(s);

            Integer[] z = new Integer[s.length()];
            z[0] = 0;
            z[1] = 0;
            for (int i = 2; i < s.length(); i++) {
                z[i] = binarySearch(stringHash, s, i);
            }

            System.out.println(Arrays.stream(z).skip(1).map(el -> String.valueOf(el)).collect(Collectors.joining(" ")));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int binarySearch(StringHash stringHash, String s, int i) {
        int left = 0;
        int right = s.length() - i;
        while (left <= right) {
            int mid = (int) Math.ceil(1.0 * (left + right) / 2);
            if (mid == 0) break;
            if (isPrefixEqualsSuffix(stringHash, mid, i, 1)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left == 0 ? 0 : right;

    }

    private static boolean isPrefixEqualsSuffix(StringHash stringHash, int len, int from1, int from2) {
        long[] pow = stringHash.pow;
        long[] hash = stringHash.hash;
        int p = stringHash.p;
        boolean result = (hash[from1 + len - 1] + hash[from2 - 1] * pow[len]) % p == (hash[from2 + len - 1] + hash[from1 - 1] * pow[len]) % p;
        return result;
    }

    @Test
    public void test_01() {
        provideConsoleInput("abracadabra\n");
        main(new String[0]);
        String expected = "0 0 0 1 0 1 0 4 0 0 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    private static class StringHash {
        final long[] pow;
        final long[] hash;
        final int p = 1_000_000_000 + 7;

        public StringHash(String s) {
            pow = new long[s.length() + 1];
            hash = new long[s.length() + 1];

            pow[0] = 1;

            hash[0] = 0;
            int x = 257;
            for (int i = 1; i < s.length(); i++) {
                pow[i] = (pow[i - 1] * x) % p;
                hash[i] = (hash[i - 1] * x + (s.charAt(i))) % p;
            }
        }
    }

}
