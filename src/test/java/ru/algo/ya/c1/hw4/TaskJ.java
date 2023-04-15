package ru.algo.ya.c1.hw4;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
https://contest.yandex.ru/contest/27665/problems/J/
Дополнительная проверка на списывание
*/

public class TaskJ extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = r.readLine().split(" ");
            int n = Integer.valueOf(input[0]);
            boolean caseSensitivity = "yes".equals(input[1]);
            boolean startFromDigit = "yes".equals(input[2]);
            List<String> keywords = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String word = r.readLine();
                if (!caseSensitivity) {
                    keywords.add(word.toLowerCase());
                } else {
                    keywords.add(word);
                }
            }
            String line = r.readLine();
            Map<String, Integer> freq = new LinkedHashMap<>();
            Map<String, Integer> first = new LinkedHashMap<>();
            int counter = 0;
            while (line != null) {
                line = replaceIncorrectLetters(line);
                String[] words = Arrays.stream(line.split(" ")).filter(el -> !el.isEmpty()).toArray(String[]::new);
                for (String word : words) {
                    boolean hasLetters = false;
                    for (int i = 0; i < word.length(); i++) {
                        if (!Character.isDigit(word.charAt(i))) {
                            hasLetters = true;
                            break;
                        }
                    }
                    if (!caseSensitivity) {
                        word = word.toLowerCase();
                    }
                    if (!keywords.contains(word)
                            && word.length() > 0 && hasLetters) {
                        if (!startFromDigit && Character.isDigit(word.charAt(0))) {
                            break;
                        }
                        counter++;
                        if (!first.containsKey(word)) {
                            first.put(word, counter);
                        }
                        freq.put(word, freq.getOrDefault(word, 0) + 1);
                    }
                }
                line = r.readLine();
            }
            int max = freq.entrySet().stream().mapToInt(el -> el.getValue()).max().getAsInt();
            Integer pos = freq.entrySet().stream()
                    .sorted(new Comparator<Map.Entry<String, Integer>>() {
                        @Override
                        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                            return o2.getValue() - o1.getValue();
                        }
                    })
                    .filter(el -> el.getValue() == max)
                    .map(el -> first.get(el.getKey()))
                    .findFirst().get();

            System.out.println(first.entrySet().stream().filter(el -> el.getValue() == pos).findFirst().get().getKey());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String replaceIncorrectLetters(String line) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char cur = line.charAt(i);
            if (Character.isDigit(cur) || Character.isLetter(cur) || cur == '_') {
                sb.append(cur);
                continue;
            } else {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    @Test
    public void test_01() {
        provideConsoleInput("0 yes no\n" +
                "int main() {\n" +
                "  int a;\n" +
                "  int b;\n" +
                "  scanf(\"%d%d\", &a, &b);\n" +
                "  printf(\"%d\", a + b);\n" +
                "}\n");
        main(new String[0]);
        String expected = "int\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("0 yes no\n" +
                "#define INT int\n" +
                "int main() {\n" +
                "  INT a, b;\n" +
                "  scanf(\"%d%d\", &a, &b);\n" +
                "  printf(\"%d %d\", a + b, 0);\n" +
                "}\n");
        main(new String[0]);
        String expected = "d\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("6 no no\n" +
                "program\n" +
                "var\n" +
                "begin\n" +
                "end\n" +
                "while\n" +
                "for\n" +
                "program sum;\n" +
                "var\n" +
                "  A, B: integer;\n" +
                "begin\n" +
                "  read(A, b);\n" +
                "  writeln(a + b);\n" +
                "end.\n");
        main(new String[0]);
        String expected = "a\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("1 yes yes\n" +
                "_\n" +
                "a = 0h\n" +
                "b = 0h\n" +
                "c = 0h\n");
        main(new String[0]);
        String expected = "0h\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
