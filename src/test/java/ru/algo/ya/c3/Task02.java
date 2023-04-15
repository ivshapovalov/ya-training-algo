package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/45468/problems/2/
Красивая строка

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Красотой строки назовем максимальное число идущих подряд одинаковых букв. (красота строки abcaabdddettq равна 3)

Сделайте данную вам строку как можно более красивой, если вы можете сделать не более k операций замены символа.

Формат ввода
В первой строке записано одно целое число k (0 ≤ k ≤ 109)
Во второй строке дана непустая строчка S (|S| ≤ 2 ⋅ 105). Строчка S состоит только из маленьких латинских букв.

Формат вывода
Выведите одно число — максимально возможную красоту строчки, которую можно получить.
*/

public class Task02 extends ContestTask {
    private static final String TEST_PATH = "/src/test/java/algo/c3/task02/";

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int k = Integer.valueOf(r.readLine().trim());
            String s = r.readLine().trim();
            int ans = 0;
            if (s.length() == 1) {
                ans = 1;
            } else {
                int[] letters = new int[26];
                int start = 0;
                int maxCount = 0;
                for (int end = 0; end < s.length(); end++) {
                    letters[s.charAt(end) - 'a']++;
                    maxCount = Math.max(letters[s.charAt(end) - 'a'], maxCount);
                    if ((end - start + 1) - maxCount > k) {
                        letters[s.charAt(start) - 'a']--;
                        start++;
                    }
                    ans = Math.max(ans, end - start + 1);
                }
            }
            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("2\n" +
                "abcaz");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("2\n" +
                "helto\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("2\n" +
                "aabbaab\n");
        main(new String[0]);
        String expected = "6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("2\n" +
                "aabbababb\n");
        main(new String[0]);
        String expected = "7\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("2\n" +
                "aabbcb\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        provideConsoleInput("50\n" +
                "pnwkexqnexjiljxkyhvgdxzpktcttnjwstwtowmupzullrzknjlgqyhutzftelcnzdogghzbhccrmvheoecjvpafekvllwijezhh\n");
        main(new String[0]);
        String expected = "57\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


    @Test
    public void test_f14() throws FileNotFoundException {
        String testNumber = "14";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
