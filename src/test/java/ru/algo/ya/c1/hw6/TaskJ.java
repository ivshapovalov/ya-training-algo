package ru.algo.ya.c1.hw6;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/27844/problems/J/
Медиана объединения
*/

public class TaskJ extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input =
                    Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int l = input[1];
            int[][] sequences = new int[n][l];
            for (int i = 0; i < n; i++) {
                int[] seq =
                        Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                sequences[i] = seq;
            }
            int[] temp = new int[2 * l];
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < sequences.length; i++) {
                for (int j = i + 1; j < sequences.length; j++) {
                    System.arraycopy(sequences[i], 0, temp, 0, l);
                    System.arraycopy(sequences[j], 0, temp, l, l);
                    Arrays.sort(temp);
                    res.append(temp[l - 1]).append("\n");
                }
            }

            System.out.print(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_1() {
        provideConsoleInput("3 6\n" +
                "1 4 7 10 13 16 \n" +
                "0 2 5 9 14 20 \n" +
                "1 7 16 16 21 22 \n");
        main(new String[0]);
        String expected = "7\n" +
                "10\n" +
                "9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
