package algo.c4.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/53030/problems/B/
Основание строки

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Строка S была записана много раз подряд, после чего от получившейся строки взяли префикс и дали вам. Ваша задача определить минимально возможную длину исходной строки S.

Формат ввода
В первой и единственной строке входного файла записана строка, которая содержит только латинские буквы, длина строки не превышает 50000 символов.

Формат вывода
Выведите ответ на задачу.

*/

public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String s = " " + r.readLine().trim();

            long[] pow = new long[s.length() + 1];
            long[] hash = new long[s.length() + 1];
            pow[0] = 1;

            hash[0] = 0;
            int x = 257;
            int p = 1_000_000_000 + 7;
            for (int i = 1; i < s.length(); i++) {
                pow[i] = (pow[i - 1] * x) % p;
                hash[i] = (hash[i - 1] * x + (s.charAt(i))) % p;
            }

            Integer result = s.length() - 1;
            for (int len = s.length() - 2; len >= 1; len--) {
                boolean prefixEqualsSuffix = isPrefixEqualsSuffix(pow, hash, p, len, 1, s.length() - len);
                if (prefixEqualsSuffix) {
                    result = s.length() - (len + 1);
                    break;
                }
            }
            System.out.println(result);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isPrefixEqualsSuffix(long[] pow, long[] hash, int p, int len, int from1, int from2) {
        boolean result = (hash[from1 + len - 1] + hash[from2 - 1] * pow[len]) % p == (hash[from2 + len - 1] + hash[from1 - 1] * pow[len]) % p;
        return result;
    }

    @Test
    public void test_01() {
        provideConsoleInput("zzz\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("bcabcab\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("abcabca\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("zbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzz\n");
        main(new String[0]);
        String expected = "113\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("zbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbzbza\n");
        main(new String[0]);
        String expected = "114\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}
