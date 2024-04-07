package algo.c4.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;

/*
https://contest.yandex.ru/contest/53030/problems/E/
Подпалиндромы

Ограничение времени 	3 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Строка называется палиндромом, если она читается одинаково как слева направо, так и справа налево. Например, строки
abba, ata являются палиндромами.

Вам дана строка. Ее подстрокой называется некоторая непустая последовательность подряд идущих символов. Напишите
программу, которая определит, сколько подстрок данной строки является палиндромами. Формат ввода

Вводится одна строка, состоящая из прописных латинских букв. Длина строки не превышает 100000 символов. Формат вывода

Выведите одно число — количество подстрок данной строки, которые являются палиндромами

*/

public class TaskE extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String s = " " + r.readLine().trim();

            StringHash stringHash = new StringHash(s);
            int n = s.length();
            long count = 0;
            for (int center = 1; center < n; center++) {
                int left = center;
                int right = center;
                long evenPalindromeCount = binarySearch(stringHash, s, left, right);

                left = center - 1;
                right = center;
                long oddPalindromeCount = binarySearch(stringHash, s, left, right);
                count = count + evenPalindromeCount + oddPalindromeCount;
            }
            System.out.println(count + s.length() - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int binarySearch(StringHash stringHash, String s, int left, int right) {
        int lo = 0;
        int hi = Math.min(s.length() - right, left);
        while (lo <= hi) {
            int len = (int) Math.ceil(1.0 * (lo + hi) / 2);
            if (len == 0) break;
            if (isPrefixEqualsReverseSuffix(stringHash, s, left, right, len)) {
                lo = len + 1;
            } else {
                hi = len - 1;
            }
        }
        return lo == 0 ? 0 : hi;
    }

    private static boolean isPrefixEqualsReverseSuffix(StringHash stringHash, String s, int left, int right, int len) {
        int n = s.length();
        if (left - len < 0 || n - right - len - 1 < 0) {
            return false;
        }
        long[] pow = stringHash.pow;
        long[] hash = stringHash.hash;
        long[] reverseHash = stringHash.reverseHash;
        int p = stringHash.p;
        return (hash[left] + reverseHash[n - right - len - 1] * pow[len]) % p ==
                (reverseHash[n - right - 1] + hash[left - len] * pow[len]) % p;
    }

    @Test
    public void test_01() {
        provideConsoleInput("aaa\n");
        main(new String[0]);
        String expected = "6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("aba\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("babbbabaaa\n");
        main(new String[0]);
        String expected = "21\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("abba\n");
        main(new String[0]);
        String expected = "6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("abcba\n");
        main(new String[0]);
        String expected = "7\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("abccba\n");
        main(new String[0]);
        String expected = "9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_004() {
        provideConsoleInput("aaaaa\n");
        main(new String[0]);
        String expected = "15\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f55() throws FileNotFoundException {
        String testNumber = "55";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
//        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
//        String expected = getFileContent(answerFilePath);
        String expected = "2499750017\n";
        System.setIn(testIn);
        main(new String[0]);
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
                hash[i] = (hash[i - 1] * x + (s.charAt(i) - 'a' + 1)) % p;
                reverseHash[i] = (reverseHash[i - 1] * x + (s.charAt(length - i) - 'a' + 1)) % p;
            }
        }
    }


}
