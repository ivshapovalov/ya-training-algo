package ru.algo.ya.c1.hw1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/27393/problems/A/
Кондиционер
*/
public class TaskA extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int tempRoom = input[0];
            int tempCond = input[1];

            String mode = reader.readLine();
            int tempRes = 0;
            switch (mode) {
                case "heat":
                    if (tempCond >= tempRoom) {
                        tempRes = tempCond;
                    } else {
                        tempRes = tempRoom;
                    }
                    break;
                case "freeze":
                    if (tempCond <= tempRoom) {
                        tempRes = tempCond;
                    } else {
                        tempRes = tempRoom;
                    }
                    break;
                case "fan":
                    tempRes = tempRoom;
                    break;
                case "auto":
                    tempRes = tempCond;
                    break;
            }
            System.out.println(tempRes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("10 20\n" +
                "heat\n");
        main(new String[0]);
        String expected = "20\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("10 20\n" +
                "freeze\n");
        main(new String[0]);
        String expected = "10\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
