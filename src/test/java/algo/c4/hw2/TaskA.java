package algo.c4.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/53030/problems/A/
Равенство подстрок

Ограничение времени 	15 секунд
Ограничение памяти 	512Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дана строка S, состоящая из строчных латинских букв.

Определите, совпадают ли строки одинаковой длины L, начинающиеся с позиций A и B.

Формат ввода
В первой строке записана S (1 ≤ |S| ≤ 2 ⋅ 105), состоящая из строчных латинских букв.

Во второй строке записано число Q (1 ≤ Q ≤ 2 ⋅ 105) — количество запросов.

В следющих Q строках записаны запросы: целые числа L, A и B (1 ≤ L ≤ |S|, 0 ≤ A, B ≤ (|S| - L)) — длина подстрок и
позиции, с которых они начинаются.

Формат вывода
Если строки совпадают — выведите "yes", иначе — "no".

*/

public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String s = " " + r.readLine().trim();
            StringHash stringHash = new StringHash(s);

            int N = Integer.parseInt(r.readLine().trim());
            for (int i = 0; i < N; i++) {
                int[] input = Arrays.stream(r.readLine().trim().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
                int L = input[0];
                int A = input[1];
                int B = input[2];
                boolean result = isEquals(stringHash, L, A + 1, B + 1);
                System.out.println(result ? "yes" : "no");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isEquals(StringHash stringHash, int len, int from1, int from2) {
        long[] pow = stringHash.pow;
        long[] hash = stringHash.hash;
        int p = stringHash.p;
        boolean result = (hash[from1 + len - 1] + hash[from2 - 1] * pow[len]) % p == (hash[from2 + len - 1] + hash[from1 - 1] * pow[len]) % p;
        return result;
    }

    @Test
    public void test_01() {
        provideConsoleInput("acabaca\n" +
                "3\n" +
                "4 3 2\n" +
                "3 4 0\n" +
                "2 0 1\n");
        main(new String[0]);
        String expected = "no\n" +
                "yes\n" +
                "no\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("caeabaeadedcbdcdccec\n" +
                "10\n" +
                "13 4 3\n" +
                "2 12 15\n" +
                "10 1 3\n" +
                "3 8 15\n" +
                "13 5 6\n" +
                "7 2 6\n" +
                "9 8 8\n" +
                "19 0 0\n" +
                "19 0 0\n" +
                "6 7 13\n");
        main(new String[0]);
        String expected = "no\n" +
                "no\n" +
                "no\n" +
                "no\n" +
                "no\n" +
                "no\n" +
                "yes\n" +
                "yes\n" +
                "yes\n" +
                "no\n";
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
                hash[i] = (hash[i - 1] * x + (s.charAt(i) - 'a')) % p;
            }
        }
    }

}
