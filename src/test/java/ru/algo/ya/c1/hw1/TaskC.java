package ru.algo.ya.c1.hw1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/27393/problems/C/
Телефонные номера
*/

public class TaskC extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] phones = new String[3];
            String search = format(reader.readLine());

            phones[0] = format(reader.readLine());
            phones[1] = format(reader.readLine());
            phones[2] = format(reader.readLine());

            for (String phone : phones) {
                if (phone.equals(search)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String format(String input) {
        String temp = input.replace("-", "").replace("(", "").replace(")", "");
        if (temp.charAt(0) == '+') {
            temp = "8" + temp.substring(2);
        } else if (temp.length() == 7) {
            temp = "8495" + temp;
        }
        return temp;
    }

    @Test
    public void test_01() {
        provideConsoleInput("8(495)430-23-97\n" +
                "+7-4-9-5-43-023-97\n" +
                "4-3-0-2-3-9-7\n" +
                "8-495-430\n");
        main(new String[0]);
        String expected = "YES\n" +
                "YES\n" +
                "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("86406361642\n" +
                "83341994118\n" +
                "86406361642\n" +
                "83341994118\n");
        main(new String[0]);
        String expected = "NO\n" +
                "YES\n" +
                "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("+78047952807\n" +
                "+78047952807\n" +
                "+76147514928\n" +
                "88047952807\n");
        main(new String[0]);
        String expected = "YES\n" +
                "NO\n" +
                "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
