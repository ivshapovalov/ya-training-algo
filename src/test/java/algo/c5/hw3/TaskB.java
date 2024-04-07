package algo.c5.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/59541/problems/B/
Анаграмма

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Задано две строки, нужно проверить, является ли одна анаграммой другой. Анаграммой называется строка, полученная из
другой перестановкой букв.

Формат ввода
Строки состоят из строчных латинских букв, их длина не превосходит 100000. Каждая записана в отдельной строке.

Формат вывода
Выведите "YES" если одна из строк является анаграммой другой и "NO" в противном случае.

*/

public class TaskB extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String str1 = r.readLine();
            String str2 = r.readLine();
            if (str1.length() != str2.length()) {
                System.out.println("NO");
                return;
            }
            int[] alpha = new int[26];
            for (int i = 0; i < str1.length(); i++) {
                char char1 = str1.charAt(i);
                char char2 = str2.charAt(i);
                alpha[char1 - 'a']++;
                alpha[char2 - 'a']--;
            }
            for (int i = 0; i < 26; i++) {
                if (alpha[i] != 0) {
                    System.out.println("NO");
                    return;
                }
            }
            System.out.println("YES");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_001() {
        provideConsoleInput("dusty\n" +
                "study\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("rat\n" +
                "bat\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
