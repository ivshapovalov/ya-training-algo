package ru.algo.ya.c1.hw5;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27794/problems/G/
Счет в гипершашках
*/
public class TaskG extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input =
                    Arrays.stream(reader.readLine().trim().split(" "))
                            .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int k = input[1];

            long[] nums =
                    Arrays.stream(reader.readLine().trim().split(" "))
                            .mapToLong(el -> Integer.valueOf(el).longValue()).toArray();

            HashMap<Long, Long> freq = Arrays.stream(nums).boxed().collect(
                    Collectors.groupingBy(
                            Function.identity(),
                            HashMap::new,
                            Collectors.counting()
                    ));

            System.out.println(findCount(freq, k));
        } catch (IOException ignored) {

        }
    }

    public static long findCount(Map<Long, Long> map, int k) {
        List<Long> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);

        final int N = keys.size();

        long res = 0;
        int j = 0;

        long duplicates = 0;
        for (int i = 0; i < N; i++) {
            while (j < N && keys.get(i) * k >= keys.get(j)) {
                if (map.get(keys.get(j)) >= 2) {
                    duplicates++;
                }
                j++;
            }

            long countI = map.get(keys.get(i));
            long range = j - i;

            if (countI >= 2) {
                // 3 кол-во перестановок
                // range - 1 - любое число между l и r
                res += (range - 1) * 3;
                duplicates--;
            }
            if (countI >= 3) {
                res++;
            }
            // арифм прогрессия
            res += (range - 1) * (range - 2) * 3;
            res += duplicates * 3;
        }

        return res;
    }

    @Test
    public void test_01() {
        provideConsoleInput("5 2\n" +
                "1 1 2 2 3\n");
        main(new String[0]);
        String expected = "9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
