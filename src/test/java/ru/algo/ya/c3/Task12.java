package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
https://contest.yandex.ru/contest/45468/problems/12/
Правильная скобочная последовательность

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt

Рассмотрим последовательность, состоящую из круглых, квадратных и фигурных скобок. Программа дожна определить, является
ли данная скобочная последовательность правильной. Пустая последовательность явлется правильной. Если A – правильная, то
последовательности (A), [A], {A} – правильные. Если A и B – правильные последовательности, то последовательность AB –
правильная.

Формат ввода
В единственной строке записана скобочная последовательность, содержащая не более 100000 скобок.

Формат вывода
Если данная последовательность правильная, то программа должна вывести строку yes, иначе строку no.
*/
public class Task12 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine().trim();
            System.out.println(checkLine(line) ? "yes" : "no");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkLine(String line) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> braces = new HashMap<>();
        braces.put(']', '[');
        braces.put('}', '{');
        braces.put(')', '(');
        for (int i = 0; i < line.length(); i++) {
            char cur = line.charAt(i);
            if (braces.containsValue(cur)) {
                stack.push(cur);
            } else if (braces.containsKey(cur)) {
                if (!stack.isEmpty()) {
                    Character last = stack.pop();
                    if (!last.equals(braces.get(cur))) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test_01() {
        provideConsoleInput("()[]\n");
        main(new String[0]);
        String expected = "yes\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("([)]\n\n");
        main(new String[0]);
        String expected = "no\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("(\n\n");
        main(new String[0]);
        String expected = "no\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("()}\n\n");
        main(new String[0]);
        String expected = "no\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("(){]}\n\n");
        main(new String[0]);
        String expected = "no\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        provideConsoleInput("({[])}\n\n");
        main(new String[0]);
        String expected = "no\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
