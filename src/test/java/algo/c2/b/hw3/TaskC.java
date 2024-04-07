package algo.c2.b.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/28964/problems/C/
Уникальные элементы

Ограничение времени 	3 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Дан список. Выведите те его элементы, которые встречаются в списке только один раз. Элементы нужно выводить в том
порядке, в котором они встречаются в списке.

Формат ввода
Вводится список чисел. Все числа списка находятся на одной строке.

Формат вывода
Выведите ответ на задачу.
 */

public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> list = Arrays.stream(reader.readLine().trim().split(" "))
                    .map(el -> Integer.valueOf(el)).collect(Collectors.toList());
            Map<Integer, Integer> res = new LinkedHashMap<>();
            for (int num : list) {
                res.put(num, res.getOrDefault(num, 0) + 1);
            }
            System.out.println(
                    res.entrySet().stream()
                            .filter(entry -> entry.getValue() == 1)
                            .map(entry -> String.valueOf(entry.getKey()))
                            .collect(Collectors.joining(" ")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1 2 2 3 3 3\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("4 3 5 2 5 1 3 5\n");
        main(new String[0]);
        String expected = "4 2 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
