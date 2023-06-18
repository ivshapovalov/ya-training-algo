package ru.algo.ya.open.summer2023.week1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/50158/problems/B/
Канонический путь

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

По заданной строке, являющейся абсолютным адресом в Unix-системе, вам необходимо получить канонический адрес.

В Unix-системе "." соответсвутет текущей директории, ".." — родительской директории, при этом будем считать, что любое количество точек подряд, большее двух, соответствует директории с таким названием (состоящем из точек). "/" является разделителем вложенных директорий, причем несколько "/" подряд должны интерпретироваться как один "/".

Канонический путь должен обладать следующими свойствами:

1) всегда начинаться с одного "/"

2) любые две вложенные директории разделяются ровно одним знаком "/"

3) путь не заканчивается "/" (за исключением корневой директории, состоящего только из символа "/")

4) в каноническом пути есть только директории, т.е. нет ни одного вхождения "." или ".." как соответствия текущей или родительской директории
Формат ввода

Вводится строка с абсолютным адресом, её длина не превосходит 100.
Формат вывода

Выведите канонический путь.

 */

public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String path = reader.readLine().trim();
            String newPath = path.replace("//", "/").replace("//", "/");
            if (newPath.endsWith("/") && !newPath.equals("/")) {
                newPath = newPath.substring(0, newPath.length() - 1);
            }
            if (newPath.startsWith("/")) newPath = newPath.substring(1);
            String[] parts = newPath.split("/");
            Deque<String> res = new ArrayDeque<>();
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].trim().equals(".")) continue;
                if (parts[i].trim().equals("..")) {
                    if (!res.isEmpty()) {
                        res.pollLast();
                    }
                } else {
                    res.offer(parts[i]);
                }
            }
            String canon = "/" + res.stream().collect(Collectors.joining("/"));
            System.out.println(canon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("/home/\n");
        main(new String[0]);
        String expected = "/home\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("/../\n");
        main(new String[0]);
        String expected = "/\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("/home//foo/\n");
        main(new String[0]);
        String expected = "/home/foo\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("/../../\n");
        main(new String[0]);
        String expected = "/\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("//ivan//hello/world/../../main/\n");
        main(new String[0]);
        String expected = "/ivan/main\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}