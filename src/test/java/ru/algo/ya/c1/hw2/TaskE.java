package ru.algo.ya.c1.hw2;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://contest.yandex.ru/contest/27472/problems/E/
Чемпионат по метанию коровьих лепешек
*/

public class TaskE extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(r.readLine());
            List<Integer> nums = Arrays.stream(r.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            int max = nums.stream().mapToInt(el -> el).max().getAsInt();
            List<Integer> winnerIndexes = IntStream.range(0, nums.size()).boxed().filter(ind -> nums.get(ind) == max).collect(Collectors.toList());

            int winnersBefore = 0;
            List<Integer> resultBasil = new ArrayList<>();
            for (int i = 1; i < nums.size() - 1; i++) {
                if (winnerIndexes.contains(i - 1)) {
                    winnersBefore++;
                }
                if (nums.get(i) % 10 == 5
                        && nums.get(i + 1) < nums.get(i)
                        && winnersBefore >= 1) {
                    resultBasil.add(nums.get(i));
                }
            }
            if (resultBasil.size() != 0) {
                Integer maxBasilResult = resultBasil.stream()
                        .sorted(Comparator.reverseOrder())
                        .limit(1)
                        .findFirst()
                        .get();
                Collections.sort(nums, Comparator.reverseOrder());
                int rank = nums.indexOf(maxBasilResult) + 1;
                System.out.println(rank);
            } else {
                System.out.println(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("8\n" +
                "10 30 20 15 10 30 5 1\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("7\n" +
                "10 20 15 10 30 5 1\n");
        main(new String[0]);
        String expected = "6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("3\n" +
                "15 15 10\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("3\n" +
                "10 15 20\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_05() {
        provideConsoleInput("2\n" +
                "15 20\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_06() {
        provideConsoleInput("2\n" +
                "10 15\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_07() {
        provideConsoleInput("3\n" +
                "10 15 10\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_08() {
        provideConsoleInput("3\n" +
                "20 15 10\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_09() {
        provideConsoleInput("7\n" +
                "20 15 15 15 10 20 20\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_10() {
        provideConsoleInput("4\n" +
                "15 10 15 14\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f13() throws FileNotFoundException {
        String testNumber = "013";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = "13\n";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f15() throws FileNotFoundException {
        String testNumber = "015";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = "234\n";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
