package algo.c4.sprint;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/53033/problems/B
Зеркальная z-функция

Ограничение времени 	2 секунды
Ограничение памяти 	512Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt


Строка S состоит из N букв. Зеркальная z-функция определяется для индекса i как максимально возможное k, такое что:

S[1] + S[2] + S[3] + … + S[k] = S[i] + S[i–1] + S[i–2] + ... + S[i–k+1]

Определите значение зеркальной z-функции для всех i от 1 до N. Формат ввода

В первой строке записано одно число N (1 ≤ N ≤ 200000). Во второй строке записана строка длиной N символов, состоящая
только из заглавных и строчных латинских букв. Формат вывода

Выведите N чисел — значения функции для i от 1 до N.

*/

public class TaskB extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int x = Integer.parseInt(r.readLine().trim());
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
        int lo = 0;
        int hi = i / 2;
        while (lo <= hi) {
            int len = (int) Math.ceil(1.0 * (lo + hi) / 2);
            if (len == 0) break;
            if (isPrefixEqualsReverseSuffix(stringHash, s, i, len)) {
                lo = len + 1;
            } else {
                hi = len - 1;
            }
        }
        return lo == 0 ? 0 : hi;
    }

    private static boolean isPrefixEqualsReverseSuffix(StringHash stringHash, String s, int i, int len) {
        int n = s.length();
        long[] pow = stringHash.pow;
        long[] hash = stringHash.hash;
        long[] reverseHash = stringHash.reverseHash;
        int p = stringHash.p;
        return (hash[len] + reverseHash[n - i + len - 1] * pow[len]) % p ==
                (reverseHash[n - i - 1] + hash[0] * pow[len]) % p;
    }

    @Test
    public void test_01() {
        provideConsoleInput("5\n" +
                "BBABB\n");
        main(new String[0]);
        String expected = "1 2 0 1 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("49\n" +
                "burannarubabyrrybaglipspiritmatankollokvzumbboyus\n");
        main(new String[0]);
        String expected = "1 0 0 0 0 0 0 0 0 10 0 1 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 0 0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    private static class StringHash {
        final long[] pow;
        final long[] hash;
        final long[] reverseHash;
        final int p = 1_000_000_000 + 7;

        public StringHash(String s) {
            pow = new long[s.length()];
            hash = new long[s.length()];
            reverseHash = new long[s.length()];
            int length = s.length();

            pow[0] = 1;

            hash[0] = 0;
            reverseHash[0] = 0;
            int x = 257;//257
            for (int i = 1; i < s.length(); i++) {
                pow[i] = (pow[i - 1] * x) % p;
                hash[i] = (hash[i - 1] * x + (s.charAt(i))) % p;
                reverseHash[i] = (reverseHash[i - 1] * x + (s.charAt(length - i))) % p;
            }
        }
    }
}
