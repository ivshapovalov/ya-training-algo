package algo.c2.b.hw5;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/*
https://contest.yandex.ru/contest/29075/problems/D/
Правильная, круглая, скобочная

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Если из правильного арифметического выражения вычеркнуть всё, кроме круглых скобок, то получится правильная скобочная
последовательность. Проверьте, является ли введённая строка правильной скобочной последовательностью.

Формат ввода
Вводится непустая строка, состоящая из открывающих и закрывающих круглых скобок. Длина строки не превосходит 100000

Формат вывода
Выведите YES если введённая строка является правильной скобочной последовательностью и NO иначе
 */

public class TaskD extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine().trim();

            Deque<Character> q = new ArrayDeque<>();
            boolean correct = true;
            for (int i = 0; i < input.length(); i++) {
                char cur = input.charAt(i);
                if (cur == ')') {
                    if (q.isEmpty()) {
                        correct = false;
                        break;
                    } else {
                        q.pop();
                    }
                } else {
                    q.push('(');
                }
            }
            if (!q.isEmpty()) correct = false;
            System.out.println(correct ? "YES" : "NO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("(())()");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("(()))()");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_07() {
        provideConsoleInput("((())()");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
