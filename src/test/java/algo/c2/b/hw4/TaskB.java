package algo.c2.b.hw4;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
https://contest.yandex.ru/contest/28970/problems/B/
Выборы в США

Ограничение времени 	2 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Как известно, в США президент выбирается не прямым голосованием, а путем двухуровневого голосования. Сначала проводятся
выборы в каждом штате и определяется победитель выборов в данном штате. Затем проводятся государственные выборы: на этих
выборах каждый штат имеет определенное число голосов — число выборщиков от этого штата. На практике, все выборщики от
штата голосуют в соответствии с результами голосования внутри штата, то есть на заключительной стадии выборов в
голосовании участвуют штаты, имеющие различное число голосов. Вам известно за кого проголосовал каждый штат и сколько
голосов было отдано данным штатом. Подведите итоги выборов: для каждого из участника голосования определите число
отданных за него голосов.

Формат ввода
Каждая строка входного файла содержит фамилию кандидата, за которого отдают голоса выборщики этого штата, затем через
пробел идет количество выборщиков, отдавших голоса за этого кандидата.

Формат вывода
Выведите фамилии всех кандидатов в лексикографическом порядке, затем, через пробел, количество отданных за них голосов.

 */

public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Map<String, Integer> elections = new HashMap<>();
            String line = reader.readLine();
            while (line != null) {
                String[] input = line.trim().split(" ");
                elections.put(input[0], elections.getOrDefault(input[0], 0) + Integer.parseInt(input[1]));
                line = reader.readLine();
            }
            elections.entrySet().stream().sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("McCain 10\n" +
                "McCain 5\n" +
                "Obama 9\n" +
                "Obama 8\n" +
                "McCain 1\n");
        main(new String[0]);
        String expected = "McCain 16\n" +
                "Obama 17\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("ivanov 100\n" +
                "ivanov 500\n" +
                "ivanov 300\n" +
                "petr 70\n" +
                "tourist 1\n" +
                "tourist 2\n");
        main(new String[0]);
        String expected = "ivanov 900\n" +
                "petr 70\n" +
                "tourist 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("bur 1\n");
        main(new String[0]);
        String expected = "bur 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
