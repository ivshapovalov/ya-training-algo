package algo.c4.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;

/*
https://contest.yandex.ru/contest/53030/problems/D/
Z-функция

Ограничение времени 	5 секунд
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Привидение Петя любит играть со своими кубиками. Он любит выкладывать их в ряд и разглядывать свое творение. Недавно
друзья решили подшутить над Петей и поставили в его игровой комнате зеркало. Известно, что привидения не отражаются в
зеркале, а кубики отражаются. Теперь Петя видит перед собой N цветных кубиков, но не знает, какие из этих кубиков
настоящие, а какие — отражение в зеркале. Выясните, сколько кубиков может быть у Пети. Петя видит отражение всех кубиков
в зеркале и часть кубиков, которая находится перед ним. Часть кубиков может быть позади Пети, их он не видит.

https://contest.yandex.ru/testsys/statement-image?imageId=b4cd9568f6383bb03502433fb99a7c8db3a24088ad678633fef2c941d80deb97

Формат ввода
Первая строка входного файла содержит число N ( 1≤ N ≤1000000 ) и количество различных цветов, в которые
могут быть раскрашены кубики — M ( 1≤ M ≤1000000 ). Следующая строка содержит N целых чисел от 1 до M — цвета кубиков.

Формат вывода
Выведите в выходной файл все такие K, что у Пети может быть K кубиков

*/

public class TaskEOld extends ContestTask {

    private static final int BASE = 257;
    private static final int MOD = 1_000_000_007;

    public static void main2(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String s = " " + r.readLine().trim();

            int result = countPalindromeSubstrings(s);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String s = " " + r.readLine().trim();

            StringHash stringHash = new StringHash(s);
            int n = s.length();
            int count = 0;
            // Проверка палиндромичности подстрок с использованием бинарного поиска
            for (int center = 0; center < n; center++) {
                int left = center;
                int right = center;
                int res1 = binarySearch(stringHash, s, left, right);

                left = center - 1;
                right = center;
                int res2 = binarySearch(stringHash, s, left, right);
                count += (res1 + res2);
//                while (left >= 0 && right < n && s.charAt(left) == s.charAt(right) &&
//                        binarySearch(stringHash.hash, stringHash, left, right) == binarySearch(stringHash.reverseHash, stringHash, n - right - 1, n - left - 1)) {
//                    count++;
//                    left--;
//                    right++;
//                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countPalindromeSubstrings(String s) {
        int count = 0;
        int n = s.length();

        // Вычисление хешей для подстрок и обратных хешей
        long[] forwardHashes = new long[n];
        long[] reverseHashes = new long[n];
        long[] powersOfBase = new long[n];
        powersOfBase[0] = 1;

        forwardHashes[0] = 0;
        reverseHashes[n - 1] = 0;

        for (int i = 1; i < n; i++) {
            powersOfBase[i] = (powersOfBase[i - 1] * BASE) % MOD;
            forwardHashes[i] = (forwardHashes[i - 1] * BASE + s.charAt(i) - '0') % MOD;
            reverseHashes[n - i - 1] = (reverseHashes[n - i] * BASE + s.charAt(n - i - 1) - '0') % MOD;
        }

        // Проверка палиндромичности подстрок с использованием бинарного поиска
        for (int center = 0; center < n; center++) {
            int left = center;
            int right = center;

            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right) &&
                    binarySearch(forwardHashes, powersOfBase, left, right) == binarySearch(reverseHashes, powersOfBase, n - right - 1, n - left - 1)) {
                count++;
                left--;
                right++;
            }

            left = center - 1;
            right = center;

            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right) &&
                    binarySearch(forwardHashes, powersOfBase, left, right) == binarySearch(reverseHashes, powersOfBase, n - right - 1, n - left - 1)) {
                count++;
                left--;
                right++;
            }
        }

        return count;
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
        return (stringHash.hash[right] - (left > 0 ? stringHash.hash[left - 1] : 0) * stringHash.pow[right - left + 1] + MOD) % MOD ==
                (stringHash.reverseHash[n - left - 1] - (n - right - 1 > 0 ? stringHash.reverseHash[n - right - 2] : 0) * stringHash.pow[right - left + 1] + MOD) % MOD;
    }

    private static long binarySearch(long[] hashes, long[] powers, int left, int right) {
        return (hashes[right] - (left > 0 ? hashes[left - 1] : 0) * powers[right - left + 1] + MOD) % MOD;
    }

    private static long binarySearch(long[] hashes, StringHash stringHash, int left, int right) {
        return (hashes[right] - (left > 0 ? hashes[left - 1] : 0) * stringHash.pow[right - left + 1] + stringHash.p) % stringHash.p;
    }

    public static void mainMy(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String s = " " + r.readLine().trim();

            StringHash stringHash = new StringHash(s);

            int result = 0;
            for (int delimiter = 1; delimiter <= s.length() / 2; delimiter++) {
                int binarySearchEven = binarySearchEven(stringHash, s, delimiter);
                int binarySearchOdd = binarySearchOdd(stringHash, s, delimiter);
                result += binarySearchEven + binarySearchOdd;
            }
            System.out.println(result + s.length() - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int binarySearchEven(StringHash stringHash, String s, int delimiter) {
        int lo = 0;
        int hi = Math.min(s.length() - delimiter, delimiter);
        while (lo <= hi) {
            int mid = (int) Math.ceil(1.0 * (lo + hi) / 2);
            if (mid == 0) break;
            if (isPrefixEqualsReverseSuffixEven(stringHash, delimiter, mid)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo == 0 ? 0 : hi;
    }

    private static int binarySearchOdd(StringHash stringHash, String s, int delimiter) {
        int lo = 1;
        int hi = Math.min(s.length() - delimiter - 1, delimiter - 1);
        while (lo <= hi) {
            int mid = (int) Math.ceil(1.0 * (lo + hi) / 2);
            if (mid == 0) break;
            if (isPrefixEqualsReverseSuffixOdd(stringHash, delimiter, mid)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo == 0 ? 0 : hi;

    }

    private static boolean isPrefixEqualsReverseSuffixEven(StringHash stringHash, int delimiter, int len) {
        long[] pow = stringHash.pow;
        long[] hash = stringHash.hash;
        long[] reverseHash = stringHash.reverseHash;
        int p = stringHash.p;
        int length = reverseHash.length;

        boolean result = (hash[delimiter] + reverseHash[delimiter - 1] * pow[len]) % p == (reverseHash[delimiter + len - 1] + hash[delimiter - len] * pow[len]) % p;
        return result;
    }

    private static boolean isPrefixEqualsReverseSuffixOdd(StringHash stringHash, int delimiter, int len) {
        long[] pow = stringHash.pow;
        long[] hash = stringHash.hash;
        long[] reverseHash = stringHash.reverseHash;
        int p = stringHash.p;
        int length = reverseHash.length;

        //1x5 1x4 2x3 2x2 1x 1
        //1 1x 2x2 2x3 1x4 1x5
        //len==1
        //hash[from1 + len - 1] + hash[from2 - 1] * pow[len]
        //hash[1,1]=hash[1+1-1]-hash[1-1]*pow[1]
        //reverseHash[2,1]=reverseHash[length-len]-reverseHash[length-2*len]*pow[len]
        boolean result = (hash[delimiter - 1] + reverseHash[delimiter] * pow[len]) % p == (reverseHash[delimiter + len] + hash[delimiter - len - 1] * pow[len]) % p;
        return result;
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
    public void test_001() {
        provideConsoleInput("abba\n");
        main(new String[0]);
        String expected = "6\n";
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
            int x = 10;//257
            for (int i = 1; i < s.length(); i++) {
                pow[i] = (pow[i - 1] * x) % p;
                hash[i] = (hash[i - 1] * x + (s.charAt(i) - 'a')) % p;
                reverseHash[i] = (reverseHash[i - 1] * x + (s.charAt(length - i) - 'a')) % p;
            }
        }
    }

}
