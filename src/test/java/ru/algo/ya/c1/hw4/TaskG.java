package ru.algo.ya.c1.hw4;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/27665/problems/G/
Банковские счета
*/
public class TaskG extends ContestTask {

    public static void main(String[] args) {
        Map<String, Long> accounts = new HashMap<>();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String line = r.readLine();
            while (line != null) {
                String[] input = Arrays.stream(line.split(" ")).filter(el -> !el.isEmpty()).toArray(String[]::new);
                switch (input[0]) {
                    case "DEPOSIT":
                        accounts.put(input[1], accounts.getOrDefault(input[1], 0L) + Long.valueOf(input[2]));
                        break;
                    case "WITHDRAW":
                        accounts.put(input[1], accounts.getOrDefault(input[1], 0L) - Long.valueOf(input[2]));
                        break;
                    case "BALANCE":
                        if (accounts.containsKey(input[1])) {
                            System.out.println(accounts.get(input[1]));
                        } else {
                            System.out.println("ERROR");
                        }
                        break;
                    case "TRANSFER":
                        accounts.put(input[1], accounts.getOrDefault(input[1], 0L) - Long.valueOf(input[3]));
                        accounts.put(input[2], accounts.getOrDefault(input[2], 0L) + Long.valueOf(input[3]));
                        break;
                    case "INCOME":
                        for (Map.Entry<String, Long> account : accounts.entrySet()) {
                            long val = account.getValue();
                            if (val > 0) {
                                account.setValue(val + val * Long.valueOf(input[1]) / 100);
                            }
                        }
                        break;
                }
                line = r.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("DEPOSIT Ivanov 100\n" +
                "INCOME 5\n" +
                "BALANCE Ivanov\n" +
                "TRANSFER Ivanov Petrov 50\n" +
                "WITHDRAW Petrov 100\n" +
                "BALANCE Petrov\n" +
                "BALANCE Sidorov\n");
        main(new String[0]);
        String expected = "105\n" +
                "-50\n" +
                "ERROR\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("BALANCE Ivanov\n" +
                "BALANCE Petrov\n" +
                "DEPOSIT Ivanov 100\n" +
                "BALANCE Ivanov\n" +
                "BALANCE Petrov\n" +
                "DEPOSIT Petrov 150\n" +
                "BALANCE Petrov\n" +
                "DEPOSIT Ivanov 10\n" +
                "DEPOSIT Petrov 15\n" +
                "BALANCE Ivanov\n" +
                "BALANCE Petrov\n" +
                "DEPOSIT Ivanov 46\n" +
                "BALANCE Ivanov\n" +
                "BALANCE Petrov\n" +
                "DEPOSIT Petrov 14\n" +
                "BALANCE Ivanov\n" +
                "BALANCE Petrov\n");
        main(new String[0]);
        String expected = "ERROR\n" +
                "ERROR\n" +
                "100\n" +
                "ERROR\n" +
                "150\n" +
                "110\n" +
                "165\n" +
                "156\n" +
                "165\n" +
                "156\n" +
                "179\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("BALANCE a\n" +
                "BALANCE b\n" +
                "DEPOSIT a 100\n" +
                "BALANCE a\n" +
                "BALANCE b\n" +
                "WITHDRAW a 20\n" +
                "BALANCE a\n" +
                "BALANCE b\n" +
                "WITHDRAW b 78\n" +
                "BALANCE a\n" +
                "BALANCE b\n" +
                "WITHDRAW a 784\n" +
                "BALANCE a\n" +
                "BALANCE b\n" +
                "DEPOSIT b 849\n" +
                "BALANCE a\n" +
                "BALANCE b\n");
        main(new String[0]);
        String expected = "ERROR\n" +
                "ERROR\n" +
                "100\n" +
                "ERROR\n" +
                "80\n" +
                "ERROR\n" +
                "80\n" +
                "-78\n" +
                "-704\n" +
                "-78\n" +
                "-704\n" +
                "771\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
