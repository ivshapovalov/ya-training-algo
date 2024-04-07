package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*
https://contest.yandex.ru/contest/45468/problems/16/
Очередь с защитой от ошибок

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Научитесь пользоваться стандартной структурой данных queue для целых чисел. Напишите программу, содержащую описание
очереди и моделирующую работу очереди, реализовав все указанные здесь методы.

Программа считывает последовательность команд и в зависимости от команды выполняет ту или иную операцию. После
выполнения каждой команды программа должна вывести одну строчку.

Возможные команды для программы:

push n Добавить в очередь число n (значение n задается после команды). Программа должна вывести ok.

pop Удалить из очереди первый элемент. Программа должна вывести его значение.

front Программа должна вывести значение первого элемента, не удаляя его из очереди.

size Программа должна вывести количество элементов в очереди.

clear Программа должна очистить очередь и вывести ok.

exit Программа должна вывести bye и завершить работу.

Перед исполнением операций front и pop программа должна проверять, содержится ли в очереди хотя бы один элемент. Если во
входных данных встречается операция front или pop, и при этом очередь пуста, то программа должна вместо числового
значения вывести строку error.

Формат ввода
Вводятся команды управления очередью, по одной на строке

Формат вывода
Требуется вывести протокол работы очереди, по одному сообщению на строке

*/
public class Task16 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine().trim();
            MyQueue<Integer> queue = new MyQueue<>();
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
                        queue.push(val);
                        break;
                    case "pop":
                        queue.pop();
                        break;
                    case "front":
                        queue.front();
                        break;
                    case "clear":
                        queue.clear();
                        break;
                    case "size":
                        queue.size();
                        break;
                    case "exit":
                        queue.exit();
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
                "front\n" +
                "exit\n");
        main(new String[0]);
        String expected = "ok\n" +
                "1\n" +
                "bye\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
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
    public void test_03() {
        provideConsoleInput("push 3\n" +
                "push 14\n" +
                "size\n" +
                "clear\n" +
                "push 1\n" +
                "front\n" +
                "push 2\n" +
                "front\n" +
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
                "1\n" +
                "1\n" +
                "1\n" +
                "2\n" +
                "0\n" +
                "bye\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    static class MyQueue<E> {
        private static final String ERROR = "error";
        LinkedList<E> list = new LinkedList<>();

        public MyQueue() {

        }

        public void push(E val) {
            list.addLast(val);
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

        public void front() {
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
