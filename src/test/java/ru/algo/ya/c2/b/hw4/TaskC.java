package ru.algo.ya.c2.b.hw4;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/28970/problems/C/
Частотный анализ

Ограничение времени 	2 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дан текст. Выведите все слова, встречающиеся в тексте, по одному на каждую строку. Слова должны быть отсортированы по
убыванию их количества появления в тексте, а при одинаковой частоте появления — в лексикографическом порядке. Указание.
После того, как вы создадите словарь всех слов, вам захочется отсортировать его по частоте встречаемости слова.
Желаемого можно добиться, если создать список, элементами которого будут кортежи из двух элементов: частота
встречаемости слова и само слово. Например, [(2, 'hi'), (1, 'what'), (3, 'is')]. Тогда стандартная сортировка будет
сортировать список кортежей, при этом кортежи сравниваются по первому элементу, а если они равны — то по второму. Это
почти то, что требуется в задаче.

Формат ввода
Вводится текст.

Формат вывода
Выведите ответ на задачу.

 */

public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Map<String, Integer> words = new HashMap<>();
            String line = reader.readLine();
            while (line != null) {
                String[] input = line.trim().split(" ");
                Arrays.stream(input).forEachOrdered(
                        word
                                -> words.put(word, words.getOrDefault(word, 0) + 1)
                );
                line = reader.readLine();
            }
            words.entrySet().stream().sorted(new Comparator<Map.Entry<String, Integer>>() {
                        @Override
                        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                            return
                                    o2.getValue().equals(o1.getValue())
                                            ? o1.getKey().compareTo(o2.getKey())
                                            : o2.getValue() - o1.getValue();
                        }
                    })
                    .forEachOrdered(entry -> System.out.println(entry.getKey()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("hi\n" +
                "hi\n" +
                "what is your name\n" +
                "my name is bond\n" +
                "james bond\n" +
                "my name is damme\n" +
                "van damme\n" +
                "claude van damme\n" +
                "jean claude van damme\n");
        main(new String[0]);
        String expected = "damme\n" +
                "is\n" +
                "name\n" +
                "van\n" +
                "bond\n" +
                "claude\n" +
                "hi\n" +
                "my\n" +
                "james\n" +
                "jean\n" +
                "what\n" +
                "your\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("oh you touch my tralala\n" +
                "mmm my ding ding dong\n");
        main(new String[0]);
        String expected = "ding\n" +
                "my\n" +
                "dong\n" +
                "mmm\n" +
                "oh\n" +
                "touch\n" +
                "tralala\n" +
                "you\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("ai ai ai ai ai ai ai ai ai ai\n");
        main(new String[0]);
        String expected = "ai\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}