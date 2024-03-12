package ru.algo.ya.c1.hw3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
https://contest.yandex.ru/contest/27663/problems/I/
Полиглоты
*/
public class TaskI extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            Integer num = Integer.valueOf(r.readLine());
            List<String> langsCommon = new ArrayList<>();
            Set<String> langsAll = new LinkedHashSet<>();
            for (int i = 0; i < num; i++) {
                Integer langsAmount = Integer.valueOf(r.readLine());
                List<String> langs = new ArrayList<>();
                for (int j = 0; j < langsAmount; j++) {
                    String lang = r.readLine();
                    langs.add(lang);
                }
                langsAll.addAll(langs);
                if (i == 0) {
                    langsCommon.addAll(langs);
                } else {
                    langsCommon.retainAll(langs);
                }
            }
            System.out.println(langsCommon.size());
            langsCommon.stream().forEach(el -> System.out.println(el));

            System.out.println(langsAll.size());
            langsAll.stream().sorted(Comparator.reverseOrder()).forEach(el -> System.out.println(el));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "3\n" +
                "Russian\n" +
                "English\n" +
                "Japanese\n" +
                "2\n" +
                "Russian\n" +
                "English\n" +
                "1\n" +
                "English\n");
        main(new String[0]);
        String expected = "1\n" +
                "English\n" +
                "3\n" +
                "Russian\n" +
                "Japanese\n" +
                "English\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
