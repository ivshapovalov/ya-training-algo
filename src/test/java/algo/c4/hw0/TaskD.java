package algo.c4.hw0;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/53027/problems/D/
Анаграмма?

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

public class TaskD extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String text1 = r.readLine().trim();
            String text2 = r.readLine().trim();
            if (text2.length() != text1.length()) {
                System.out.println("NO");
            } else {
                int[] chars = new int[26];
                for (int i = 0; i < text1.length(); i++) {
                    char char1 = text1.charAt(i);
                    char char2 = text2.charAt(i);
                    chars[char1 - 'a']++;
                    chars[char2 - 'a']--;
                }
                boolean errorFound = Arrays.stream(chars).anyMatch(el -> el != 0);
                System.out.println(errorFound ? "NO" : "YES");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("dusty\n" +
                "study\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("rat\n" +
                "bat\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}
