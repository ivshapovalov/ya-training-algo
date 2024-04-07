package algo.c1.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

 

/*
https://contest.yandex.ru/contest/27663/problems/D/
Количество слов в тексте
*/

public class TaskD extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String line = r.readLine();
            Set<String> set = new HashSet<>();

            while (line != null) {
                Arrays.stream(line.split(" "))
                        .filter(el -> !el.trim().isEmpty())
                        .forEach(el -> set.add(el));
                line = r.readLine();
            }
            System.out.println(set.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("She sells sea shells on the sea shore;\n" +
                "The shells that she sells are sea shells I'm sure.\n" +
                "So if she sells sea shells on the sea shore,\n" +
                "I'm sure that the shells are sea shore shells.\n");
        main(new String[0]);
        String expected = "19\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
