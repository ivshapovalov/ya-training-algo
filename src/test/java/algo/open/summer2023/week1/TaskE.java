package algo.open.summer2023.week1;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/50158/problems/E/
Сломай палиндром

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Палиндромом называется строка, которая читается одинаково слева-направо и справа-налево.

В заданной строке-палиндроме необходимо заменить один символ, чтобы она перестала быть палиндромом. При этом полученная строка должна быть лексикографически минимальной.

Строка A лексикографически меньше строки B (той же длины), если на первой различающейся позиции в строке A стоит меньший символ, чем в B. Например, строка adbc меньше строки adca, т.к. первые два символа в строках совпадают, а на третьем месте в строке adbc стоит символ b, который меньше символа c, стоящего на третьей позиции в строке adca.

Формат ввода
Вводится строка-палиндром, состоящая из маленьких букв латинского алфавита. Длина строки не превосходит 1000.

Формат вывода
Выведите лексикографически минимальную строку, не являющуюяся палиндромом, полученную из исходной строки заменой одного символа.
Если получить такую строку невозможно - выведите пустую строку.

 */

public class TaskE extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine().trim();
            String output = "";
            if (input.length() != 1) {
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) != 'a') {
                        if (input.length() % 2 == 1 && i == input.length() / 2) {
                            output = input.substring(0, input.length() - 1) + 'b';
                        } else {
                            output = input.substring(0, i) + 'a' + input.substring(i + 1);
                        }
                        break;
                    }
                }
                if (output.equals("")) output = input.substring(0, input.length() - 1) + 'b';
            }
            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("abba");
        main(new String[0]);
        String expected = "aaba\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("a");
        main(new String[0]);
        String expected = "\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("aacaa");
        main(new String[0]);
        String expected = "aacab\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("b");
        main(new String[0]);
        String expected = "\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("aba");
        main(new String[0]);
        String expected = "abb\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_004() {
        provideConsoleInput("aaaa");
        main(new String[0]);
        String expected = "aaab\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}
