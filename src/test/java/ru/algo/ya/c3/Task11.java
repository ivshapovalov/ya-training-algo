package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*
https://contest.yandex.ru/contest/45468/problems/11/
Стек с защитой от ошибок

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Научитесь пользоваться стандартной структурой данных stack для целых чисел. Напишите программу, содержащую описание
стека и моделирующую работу стека, реализовав все указанные здесь методы. Программа считывает последовательность команд
и в зависимости от команды выполняет ту или иную операцию. После выполнения каждой команды программа должна вывести одну
строчку. Возможные команды для программы:

push n Добавить в стек число n (значение n задается после команды). Программа должна вывести ok.

pop Удалить из стека последний элемент. Программа должна вывести его значение.

back Программа должна вывести значение последнего элемента, не удаляя его из стека.

size Программа должна вывести количество элементов в стеке.

clear Программа должна очистить стек и вывести ok.

exit Программа должна вывести bye и завершить работу.

Перед исполнением операций back и pop программа должна проверять, содержится ли в стеке хотя бы один элемент. Если во
входных данных встречается операция back или pop, и при этом стек пуст, то программа должна вместо числового значения
вывести строку error.

Формат ввода
Вводятся команды управления стеком, по одной на строке

Формат вывода
Программа должна вывести протокол работы стека, по одному сообщению на строке
*/
public class Task11 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine().trim();
            MyStack<Integer> stack = new MyStack<>();
            out:
            while (line != null) {
                String[] input = line.split(" ");
                String command = input[0].trim();
                int val = 0;
                if (input.length > 1) {
                    val = Integer.valueOf(input[1].trim());
                }
                switch (command) {
                    case "push":
                        stack.push(val);
                        break;
                    case "pop":
                        stack.pop();
                        break;
                    case "back":
                        stack.back();
                        break;
                    case "clear":
                        stack.clear();
                        break;
                    case "size":
                        stack.size();
                        break;
                    case "exit":
                        stack.exit();
                        break out;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("push 1\n" +
                "back\n" +
                "exit\n");
        main(new String[0]);
        String expected = "ok\n" +
                "1\n" +
                "bye\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("exit\n" +
                "push 1\n" +
                "pop\n");
        main(new String[0]);
        String expected = "bye\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("size\n" +
                "push 1\n" +
                "size\n" +
                "push 2\n" +
                "size\n" +
                "push 3\n" +
                "size\n" +
                "exit\n");
        main(new String[0]);
        String expected = "0\n" +
                "ok\n" +
                "1\n" +
                "ok\n" +
                "2\n" +
                "ok\n" +
                "3\n" +
                "bye\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("push 3\n" +
                "push 14\n" +
                "size\n" +
                "clear\n" +
                "push 1\n" +
                "back\n" +
                "push 2\n" +
                "back\n" +
                "pop\n" +
                "size\n" +
                "pop\n" +
                "size\n" +
                "exit\n");
        main(new String[0]);
        String expected = "ok\n" +
                "ok\n" +
                "2\n" +
                "ok\n" +
                "ok\n" +
                "1\n" +
                "ok\n" +
                "2\n" +
                "2\n" +
                "1\n" +
                "1\n" +
                "0\n" +
                "bye\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    static class MyStack<E> {
        private static final String ERROR = "error";
        LinkedList<E> list = new LinkedList<>();

        public MyStack() {

        }

        public void push(E val) {
            list.addFirst(val);
            System.out.println("ok");
        }

        public E pop() {
            if (list.size() != 0) {
                E cur = list.pop();
                System.out.println(cur);
                return cur;
            } else {
                System.out.println(ERROR);
                return null;
            }
        }

        public void back() {
            if (list.size() != 0) {
                E cur = list.peek();
                System.out.println(cur);
            } else {
                System.out.println(ERROR);
            }
        }

        public int size() {
            System.out.println(list.size());
            return 0;
        }

        public void clear() {
            list.clear();
            System.out.println("ok");
        }

        public void exit() {
            System.out.println("bye");
        }
    }
}
