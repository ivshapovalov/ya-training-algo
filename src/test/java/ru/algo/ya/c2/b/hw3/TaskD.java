package ru.algo.ya.c2.b.hw3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://contest.yandex.ru/contest/28964/problems/D/
Угадай число

Ограничение времени 	3 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Август и Беатриса играют в игру. Август загадал натуральное число от 1 до n. Беатриса пытается угадать это число, для
этого она называет некоторые множества натуральных чисел. Август отвечает Беатрисе YES, если среди названных ей чисел
есть задуманное или NO в противном случае. После нескольких заданных вопросов Беатриса запуталась в том, какие вопросы
она задавала и какие ответы получила и просит вас помочь ей определить, какие числа мог задумать Август.

Формат ввода
Первая строка входных данных содержит число n — наибольшее число, которое мог загадать Август. Далее идут строки,
содержащие вопросы Беатрисы. Каждая строка представляет собой набор чисел, разделенных пробелами. После каждой строки с
вопросом идет ответ Августа: YES или NO. Наконец, последняя строка входных данных содержит одно слово HELP.

Формат вывода
Вы должны вывести (через пробел, в порядке возрастания) все числа, которые мог задумать Август.
 */

public class TaskD extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine().trim());
            String line = reader.readLine().trim();
            Set<Integer> resultYes = new HashSet<>();
            Set<Integer> resultNo = new HashSet<>();
            while (!line.equals("HELP")) {
                Set<Integer> question = Arrays.stream(line.split(" "))
                        .map(el -> Integer.valueOf(el)).collect(Collectors.toSet());
                String answer = reader.readLine().trim();
                if (answer.equals("YES")) {
                    if (resultYes.size() == 0) {
                        resultYes = new HashSet<>(question);
                    } else {
                        resultYes.retainAll(question);
                    }
                } else {
                    if (resultYes.size() > 0) {
                        resultYes.removeAll(question);
                    }
                    resultNo.addAll(question);
                }
                line = reader.readLine().trim();
            }
            if (resultYes.size() > 0) {
                System.out.println(resultYes.stream().sorted()
                        .map(String::valueOf).collect(Collectors.joining(" ")));
            } else {
                int result = IntStream.rangeClosed(1, N).filter(el -> !resultNo.contains(el)).findFirst().getAsInt();
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("10\n" +
                "1 2 3 4 5\n" +
                "YES\n" +
                "2 4 6 8 10\n" +
                "NO\n" +
                "HELP\n");
        main(new String[0]);
        String expected = "1 3 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("10\n" +
                "1 2 3 4 5 6 7 8 9 10\n" +
                "YES\n" +
                "1\n" +
                "NO\n" +
                "2\n" +
                "NO\n" +
                "3\n" +
                "NO\n" +
                "4\n" +
                "NO\n" +
                "6\n" +
                "NO\n" +
                "7\n" +
                "NO\n" +
                "8\n" +
                "NO\n" +
                "9\n" +
                "NO\n" +
                "10\n" +
                "NO\n" +
                "HELP\n" +
                "\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f007() throws FileNotFoundException {
        String testNumber = "007";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
//        String expected = getFileContent(answerFilePath);
        String expected = "22799 38012 96874\n";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f009() throws FileNotFoundException {
        String testNumber = "009";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
//        String expected = getFileContent(answerFilePath);
        String expected = "82832\n";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}