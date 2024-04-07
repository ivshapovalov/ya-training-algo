package algo.c1.hw4;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/27665/problems/A/
Словарь синонимов
*/

public class TaskA extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(r.readLine());
            Map<String, String> dictionary = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String[] input = r.readLine().split(" ");
                dictionary.put(input[0], input[1]);
                dictionary.put(input[1], input[0]);
            }
            String search = r.readLine();
            System.out.println(dictionary.get(search));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "Hello Hi\n" +
                "Bye Goodbye\n" +
                "List Array\n" +
                "Goodbye\n");
        main(new String[0]);
        String expected = "Bye\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1\n" +
                "beep Car\n" +
                "Car\n");
        main(new String[0]);
        String expected = "beep\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("2\n" +
                "Ololo Ololo\n" +
                "Numbers 1234567890\n" +
                "Numbers\n");
        main(new String[0]);
        String expected = "1234567890\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
