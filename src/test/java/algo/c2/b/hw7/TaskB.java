package algo.c2.b.hw7;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/29396/problems/B/
Таможня

Ограничение времени 	3 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Идёт 2163 год. Мишу, который работает в отделении таможни при космодроме города Нью-Питер, вызвал в кабинет шеф.

Как оказалось, недавно Министерство Налогов и Сборов выделило отделению определённую сумму денег на установку новых
аппаратов для автоматического досмотра грузов. Естественно, средства были выделены с таким расчётом, чтобы грузы теперь
находились на таможне ровно столько времени, сколько требуется непосредственно на их досмотр.

В руках шефа каким-то образом оказались сведения о надвигающейся ревизии – список из N грузов, которые будут
контролироваться Министерством. Для каждого груза известны время его прибытия, отсчитываемое с некоторого момента,
хранимого в большом секрете, и время, требуемое аппарату для обработки этого груза. Шеф дал Мише задание по этим данным
определить, какое минимальное количество аппаратов необходимо заказать на заводе, чтобы все грузы Министерства начинали
досматриваться сразу после прибытия. Необходимо учесть, что конструкция тех аппаратов, которые было решено установить,
не позволяет обрабатывать два груза одновременно на одном аппарате. Напишите программу, которая поможет Мише справиться
с его задачей.

Формат ввода
На первой строке входного файла задано число N (0 ≤ N ≤ 50 000). На следующих N строках находится по 2 целых
положительных числа Ti и Li – время прибытия соответствующего груза и время, требуемое для его обработки (1 ≤ Ti ≤ 106,
1 ≤ Li ≤ 106).

Формат вывода
В выходной файл выведите одно число – наименьшее количество аппаратов, которое нужно установить, чтобы не вызвать
подозрений у Министерства.

 */

public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine().trim());
            Map<Integer, Long> cargos = new HashMap<>();
            for (int i = 0; i < N; i++) {
                int[] cargo = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                int start = cargo[0];
                int duration = cargo[1];
                cargos.put(start, cargos.getOrDefault(start, 0L) + 1L);
                cargos.put(start + duration, cargos.getOrDefault(start + duration, 0L) - 1L);
            }
            List<Map.Entry<Integer, Long>> sorted = cargos.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());
            long max = 0;
            long actual = 0;
            for (Map.Entry<Integer, Long> entry : sorted) {
                Long value = entry.getValue();
                actual += value;
                max = Math.max(max, actual);
            }
            System.out.println(max);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "3 2\n" +
                "4 2\n" +
                "5 2\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("5\n" +
                "13 4\n" +
                "15 1\n" +
                "11 5\n" +
                "12 3\n" +
                "10 3\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
