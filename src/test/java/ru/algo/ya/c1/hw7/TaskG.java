package ru.algo.ya.c1.hw7;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27883/problems/G/
Детский праздник

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Организаторы детского праздника планируют надуть для него M воздушных шариков. С этой целью они пригласили N
добровольных помощников, i-й среди которых надувает шарик за Ti минут, однако каждый раз после надувания Zi шариков
устает и отдыхает Yi минут. Теперь организаторы праздника хотят узнать, через какое время будут надуты все шарики при
наиболее оптимальной работе помощников, и сколько шариков надует каждый из них. (Если помощник надул шарик, и должен
отдохнуть, но больше шариков ему надувать не придется, то считается, что он закончил работу сразу после окончания
надувания последнего шарика, а не после отдыха).

Формат ввода
В первой строке входных данных задаются числа M и N (0 ≤ M ≤15000, 1 ≤N ≤ 1000). Следующие N строк содержат по три
целых числа - Ti, Zi и Yi соответственно (1 ≤Ti, Yi ≤100, 1 ≤Zi ≤ 1000).

Формат вывода
Выведите в первой строке число T - время, за которое будут надуты все шарики. Во второй строке выведите N чисел -
количество шариков, надутых каждым из приглашенных помощников. Разделяйте числа пробелами. Если распределений шариков
несколько, выведите любое из них.

*/
public class TaskG extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int k = Integer.valueOf(reader.readLine().trim());
            for (int i = 1; i <= k; i++) {
                Map<Integer, List<int[]>> events = new LinkedHashMap<>();

                int[] test = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                int n = test[0];
                for (int j = 1; j < test.length; j += 2) {
                    int in = test[j];
                    int out = test[j + 1];

                    List<int[]> inEvent = events.getOrDefault(in, new ArrayList<>());
                    inEvent.add(new int[]{j, 1});
                    events.put(in, inEvent);
                    List<int[]> outEvent = events.getOrDefault(out, new ArrayList<>());
                    outEvent.add(new int[]{j, -1});
                    events.put(out, outEvent);
                }
                boolean failed = false;
                Set<Integer> online = new HashSet<>();
                Set<Integer> imps = new HashSet<>();
                List<Map.Entry<Integer, List<int[]>>> sortedEvents = events.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());
                if (sortedEvents.get(0).getKey() == 0) {
                    for (Map.Entry<Integer, List<int[]>> event : sortedEvents) {
                        List<int[]> value = event.getValue();
                        for (int j = 0; j < value.size(); j++) {
                            int[] ev = value.get(j);
                            if (ev[1] == 1) {
                                online.add(ev[0]);
                            } else {
                                online.remove(ev[0]);
                            }
                        }
                        if (online.size() == 1) {
                            imps.addAll(online);
                        }
                        if (online.size() == 0 && !event.getKey().equals(10000)) {
                            failed = true;
                            break;
                        }

                    }
                } else {
                    failed = true;
                }
                if (failed || imps.size() != n) {
                    System.out.println("Wrong Answer");
                } else {
                    System.out.println("Accepted");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "3 0 3000 2500 7000 2700 10000\n" +
                "2 0 3000 2700 10000\n" +
                "2 500 3000 2700 10000\n");
        main(new String[0]);
        String expected = "Wrong Answer\n" +
                "Accepted\n" +
                "Wrong Answer\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}
