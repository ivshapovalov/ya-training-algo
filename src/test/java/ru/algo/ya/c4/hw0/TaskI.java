package ru.algo.ya.c4.hw0;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/*
https://contest.yandex.ru/contest/53027/problems/I/
Правильная скобочная последовательность

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Рассмотрим последовательность, состоящую из круглых, квадратных и фигурных скобок. Программа должна определить, является
ли данная скобочная последовательность правильной. Пустая последовательность является правильной. Если A — правильная,
то последовательности (A), [A], {A} — правильные. Если A и B — правильные последовательности, то последовательность AB —
правильная.

Формат ввода
В единственной строке записана скобочная последовательность, содержащая не более 100000 скобок.

Формат вывода
Если данная последовательность правильная, то программа должна вывести строку "yes", иначе строку "no".

*/

public class TaskI extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String line = r.readLine().trim();
            Map<Character, Character> map = Map.ofEntries(Map.entry('(', ')'), Map.entry('[', ']'), Map.entry('{', '}'));
            Deque<Character> queue = new ArrayDeque<>();
            boolean result = true;
            for (char cur : line.toCharArray()) {
                if (map.containsKey(cur)) {
                    queue.push(cur);
                } else {
                    if (!queue.isEmpty()) {
                        Character hi = queue.poll();
                        if (!map.get(hi).equals(cur)) {
                            result = false;
                            break;
                        }
                    } else {
                        result = false;
                        break;
                    }
                }
            }
            if (!queue.isEmpty()) result = false;
            System.out.println(result ? "yes" : "no");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        provideConsoleInput("([)]\n");
        main(new String[0]);
        String expected = "no\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("(\n");
        main(new String[0]);
        String expected = "no\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("\n");
        main(new String[0]);
        String expected = "yes\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput(")))\n");
        main(new String[0]);
        String expected = "no\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
