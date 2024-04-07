package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*
https://contest.yandex.ru/contest/45468/problems/18/
Дек с защитой от ошибок

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Научитесь пользоваться стандартной структурой данных deque для целых чисел.  Напишите программу, содержащую описание
дека и моделирующую работу дека, реализовав все указанные здесь методы. Программа считывает последовательность команд и
в зависимости от команды выполняет ту или иную операцию. После выполнения каждой команды программа должна вывести одну
строчку.

Возможные команды для программы:

push_front n Добавить (положить) в начало дека новый элемент. Программа должна вывести ok.

push_back n Добавить (положить) в конец дека новый элемент. Программа должна вывести ok.

pop_front Извлечь из дека первый элемент. Программа должна вывести его значение.

pop_back Извлечь из дека последний элемент. Программа должна вывести его значение.

front Узнать значение первого элемента (не удаляя его). Программа должна вывести его значение.

back Узнать значение последнего элемента (не удаляя его). Программа должна вывести его значение.

size Вывести количество элементов в деке.

clear Очистить дек (удалить из него все элементы) и вывести ok.

exit Программа должна вывести bye и завершить работу.

Гарантируется, что количество элементов в деке в любой момент не превосходит 100. Перед исполнением операций pop_front,
pop_back, front, back программа должна проверять, содержится ли в деке хотя бы один элемент. Если во входных данных
встречается операция pop_front, pop_back, front, back, и при этом дек пуст, то программа должна вместо числового
значения вывести строку error.

Формат ввода
Вводятся команды управления деком, по одной на строке.

Формат вывода
Требуется вывести протокол работы дека, по одному сообщению на строке
*/
public class Task18 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine().trim();
            MyDeque<Integer> queue = new MyDeque<>();
            out:
            while (line != null) {
                String[] input = line.split(" ");
                String command = input[0].trim();
                int val = 0;
                if (input.length > 1) {
                    val = Integer.valueOf(input[1].trim());
                }
                switch (command) {
                    case "push_front":
                        queue.push_front(val);
                        break;
                    case "push_back":
                        queue.push_back(val);
                        break;
                    case "pop_front":
                        queue.pop_front();
                        break;
                    case "pop_back":
                        queue.pop_back();
                        break;
                    case "front":
                        queue.front();
                        break;
                    case "back":
                        queue.back();
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
    public void test_1() {
        provideConsoleInput("push_back 1\n" +
                "back\n" +
                "exit\n");
        main(new String[0]);
        String expected = "ok\n" +
                "1\n" +
                "bye\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_2() {
        provideConsoleInput("size\n" +
                "push_back 1\n" +
                "size\n" +
                "push_back 2\n" +
                "size\n" +
                "push_front 3\n" +
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
    public void test_3() {
        provideConsoleInput("push_back 3\n" +
                "push_front 14\n" +
                "size\n" +
                "clear\n" +
                "push_front 1\n" +
                "back\n" +
                "push_back 2\n" +
                "front\n" +
                "pop_back\n" +
                "size\n" +
                "pop_front\n" +
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
                "2\n" +
                "1\n" +
                "1\n" +
                "0\n" +
                "bye\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    static class MyDeque<E> {
        private static final String ERROR = "error";
        LinkedList<E> list = new LinkedList<>();

        public MyDeque() {

        }

        public void push_front(E val) {
            list.addFirst(val);
            System.out.println("ok");
        }

        public void push_back(E val) {
            list.addLast(val);
            System.out.println("ok");
        }

        public E pop_front() {
            if (list.size() != 0) {
                E cur = list.pollFirst();
                System.out.println(cur);
                return cur;
            } else {
                System.out.println(ERROR);
                return null;
            }
        }

        public E pop_back() {
            if (list.size() != 0) {
                E cur = list.pollLast();
                System.out.println(cur);
                return cur;
            } else {
                System.out.println(ERROR);
                return null;
            }
        }

        public void front() {
            if (list.size() != 0) {
                E cur = list.peekFirst();
                System.out.println(cur);
            } else {
                System.out.println(ERROR);
            }
        }

        public void back() {
            if (list.size() != 0) {
                E cur = list.peekLast();
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
