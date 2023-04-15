package ru.algo.ya.c1.hw5;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/27794/problems/B/
Сумма номеров
*/

public class TaskB extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = r.readLine().trim().split(" ");
            int N = Integer.valueOf(input[0]);
            int K = Integer.valueOf(input[1]);
            int[] nums =
                    Arrays.stream(r.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();

            int counter = 0;
            long sum = 0;
            int right;
            for (right = 0; right < nums.length && sum < K; right++) {
                sum += nums[right];
            }
            counter += getCounter(K, sum);
            for (int left = 1; left < nums.length; left++) {
                sum = sum - nums[left - 1];
                int i = 0;
                for (i = right; i < nums.length; i++) {
                    if (sum + nums[i] > K) {
                        break;
                    } else {
                        sum += nums[i];
                    }
                }
                right = i;
                counter += getCounter(K, sum);
            }
            System.out.println(counter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getCounter(int K, long sum) {
        int counter = 0;
        if (sum == K) {
            counter++;
        }
        return counter;
    }

    @Test
    public void test_1() {
        provideConsoleInput("5 17\n" +
                "17 7 10 7 10\n");
        main(new String[0]);
        String expected = "4\r\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_2() {
        provideConsoleInput("5 10\n" +
                "1 2 3 4 1\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}
