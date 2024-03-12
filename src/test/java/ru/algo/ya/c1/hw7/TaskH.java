package ru.algo.ya.c1.hw7;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27883/problems/H/
Охрана

Ограничение времени 	4 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt

На секретной военной базе работает N охранников. Сутки поделены на 10000 равных промежутков времени, и известно когда
каждый из охранников приходит на дежурство и уходит с него. Например, если охранник приходит в 5, а уходит в 8, то
значит, что он был в 6, 7 и 8-ой промежуток (а в 5-й нет!!!).

Укажите, верно ли что для данного набора охранников, объект охраняется в любой момент времени хотя бы одним охранником и
удаление любого из них приводит к появлению промежутка времени, когда объект не охраняется.

Формат ввода
В первой строке входного файла записано натуральное число K (1≤K≤100) — количество тестов в файле. Каждый тест
начинается с числа N (1≤N≤10000), за которым следует N пар неотрицательных целых чисел A и B — время прихода на
дежурство и ухода (0≤A≤B≤10000) соответствующего охранника.

Формат вывода
Выведите K строк, где в M-ой строке находится слово Accepted, если M-ый набор охранников удовлетворяет описанным выше
условиям. В противном случае выведите Wrong Answer.

*/
public class TaskH extends ContestTask {

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
