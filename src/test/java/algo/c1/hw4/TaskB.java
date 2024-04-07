package algo.c1.hw4;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
https://contest.yandex.ru/contest/27665/problems/B/
Номер появления слова
*/

public class TaskB extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String line = r.readLine();
            List<Integer> result = new ArrayList<>();
            Map<String, Integer> freq = new HashMap<>();
            while (line != null) {
                String[] words = Arrays.stream(line.split(" ")).filter(el -> !el.isEmpty()).toArray(String[]::new);
                for (String word : words) {
                    int cur = freq.getOrDefault(word, 0);
                    result.add(cur);
                    freq.put(word, cur + 1);
                }
                line = r.readLine();
            }
            result.forEach(el -> System.out.print(el + " "));
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("one two one tho three\n");
        main(new String[0]);
        String expected = "0 0 1 0 0 \n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("She sells sea shells on the sea shore;\n" +
                "The shells that she sells are sea shells I'm sure.\n" +
                "So if she sells sea shells on the sea shore,\n" +
                "I'm sure that the shells are sea shore shells.\n");
        main(new String[0]);
        String expected = "0 0 0 0 0 0 1 0 0 1 0 0 1 0 2 2 0 0 0 0 1 2 3 3 1 1 4 0 1 0 1 2 4 1 5 0 0 \n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("aba aba; aba @?\"\n");
        main(new String[0]);
        String expected = "0 0 1 0 \n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
