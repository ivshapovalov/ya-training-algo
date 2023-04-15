package ru.algo.ya.c1.hw4;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*
https://contest.yandex.ru/contest/27665/problems/C/
Самое частое слово
*/

public class TaskC extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String line = r.readLine();
            Map<String, Integer> freq = new HashMap<>();
            while (line != null) {
                String[] words = Arrays.stream(line.split(" ")).filter(el -> !el.isEmpty()).toArray(String[]::new);
                for (String word : words) {
                    freq.put(word, freq.getOrDefault(word, 0) + 1);
                }
                line = r.readLine();
            }
            Optional<String> first = freq.entrySet().stream().sorted(
                    (o1, o2) -> o1.getValue() != o2.getValue() ? o2.getValue() - o1.getValue() :
                            o1.getKey().compareTo(o2.getKey())).map(el -> el.getKey()).limit(1).findFirst();
            if (first.isPresent()) {
                System.out.print(first.get() + "\n");
            } else {
                System.out.print("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("apple orange banana banana orange\n");
        main(new String[0]);
        String expected = "banana\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("oh you touch my tralala mmm my ding ding dong\n");
        main(new String[0]);
        String expected = "ding\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("q w e r t y u i o p\n" +
                "a s d f g h j k l\n" +
                "z x c v b n m\"\n");
        main(new String[0]);
        String expected = "a\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
