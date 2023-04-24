package ru.algo.ya.c1.hw7;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27883/problems/D/
Реклама

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Фирма NNN решила транслировать свой рекламный ролик в супермаркете XXX. Однако денег, запланированных на рекламную
кампанию, хватило лишь на две трансляции ролика в течение одного рабочего дня.

Фирма NNN собрала информацию о времени прихода и времени ухода каждого покупателя в некоторый день. Менеджер по рекламе
предположил, что и на следующий день покупатели будут приходить и уходить ровно в те же моменты времени.

Помогите ему определить моменты времени, когда нужно включить трансляцию рекламных роликов, чтобы как можно большее
количество покупателей прослушало ролик целиком от начала до конца хотя бы один раз. Ролик длится ровно 5 единиц времен.
Трансляции роликов не должны пересекаться, то есть начало второй трансляции должно быть хотя бы на 5 единиц времени
позже, чем начало первой.

Если трансляция ролика включается, например, в момент времени 10, то покупатели, пришедшие в супермаркет в момент
времени 10 (или раньше) и уходящие из супермаркета в момент 15 (или позднее) успеют его прослушать целиком, а, например,
покупатель, пришедший в момент времени 11, равно как и покупатель, уходящий в момент 14 - не успеют. Если покупатель
успевает услышать только конец первой трансляции ролика (не сначала) и начало второй трансляции (не до конца), то
считается, что он не услышал объявления. Если покупатель успевает услышать обе трансляции ролика, то при подсчете числа
людей, прослушавших ролик, он все равно учитывается всего один раз (фирме важно именно количество различных людей,
услышавших ролик).

Формат ввода
В первой строке входного файла вводится число N - количество покупателей (1 ≤ N ≤ 2000). В следующих N строках записано
по паре натуральных чисел - время прихода и время ухода каждого из них. Все значения времени - натуральные числа, не
превышающие 109. Время ухода человека из супермаркета всегда строго больше времени его прихода в супермаркет.

Формат вывода
Выведите через пробел три числа: количество покупателей, которые прослушают ролик целиком от начала до конца хотя бы
один раз, и моменты времени, когда должна начинаться трансляция ролика. Моменты времени должны быть выведены в
возрастающем порядке и должны быть натуральными числами, не превышающими 2·109. Если вариантов ответа несколько,
выведите любой из них.
*/
public class TaskD extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            Map<Integer, Integer> events = new LinkedHashMap<>();
            for (int i = 0; i < n; i++) {
                int[] client = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                if (client[1] - client[0] >= 5) {
                    events.put(client[0], events.getOrDefault(client[0], 0) + 1);
                    events.put(client[1], events.getOrDefault(client[1] + 1, 0) - 1);
                }
            }

            int prev = 0;
            int sum = 0;
            int online = 0;
            for (Map.Entry<Integer, Integer> event :
                    events.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList())) {
                online += event.getValue();

            }
            sum += n - prev;
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_01() {
        provideConsoleInput("4\n" +
                "1 11\n" +
                "1 3\n" +
                "6 15\n" +
                "1 6\n");
        main(new String[0]);
        String expected = "3 1 6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1\n" +
                "1 10\n");
        main(new String[0]);
        String expected = "1 3 25\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("3\n" +
                "1 10\n" +
                "11 20\n" +
                "21 30\n");
        main(new String[0]);
        String expected = "2 1 22\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
