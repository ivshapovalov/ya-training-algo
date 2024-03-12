package ru.algo.ya.c2.b.hw4;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/28970/problems/D/
Выборы Государственной думы

Ограничение времени 	2 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Статья 83 закона “О выборах депутатов Государственной Думы Федерального Собрания Российской Федерации” определяет
следующий алгоритм пропорционального распределения мест в парламенте.

Необходимо распределить 450 мест между партиями, участвовавших в выборах. Сначала подсчитывается сумма голосов
избирателей, поданных за каждую партию и подсчитывается сумма голосов, поданных за все партии. Эта сумма делится на 450,
получается величина, называемая “первое избирательное частное” (смысл первого избирательного частного - это количество
голосов избирателей, которое необходимо набрать для получения одного места в парламенте).

Далее каждая партия получает столько мест в парламенте, чему равна целая часть от деления числа голосов за данную партию
на первое избирательное частное.

Если после первого раунда распределения мест сумма количества мест, отданных партиям, меньше 450, то оставшиеся места
передаются по одному партиям, в порядке убывания дробной части частного от деления числа голосов за данную партию на
первое избирательное частное. Если же для двух партий эти дробные части равны, то преимущество отдается той партии,
которая получила большее число голосов.

Формат ввода
На вход программе подается список партий, участвовавших в выборах. Каждая строка входного файла содержит название партии
(строка, возможно, содержащая пробелы), затем, через пробел, количество голосов, полученных данной партией – число, не
превосходящее 108.

Формат вывода
Программа должна вывести названия всех партий и количество голосов в парламенте, полученных данной партией. Названия
необходимо выводить в том же порядке, в котором они шли во входных данных.

 */

public class TaskD extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            Map<String, Integer> parties = new LinkedHashMap<>();
            long votesAmount = 0;
            while (line != null) {
                String[] input = line.trim().split(" ");
                int votes = Integer.parseInt(input[input.length - 1]);
                votesAmount += votes;
                input[input.length - 1] = "";
                String partyName = Arrays.stream(input).collect(Collectors.joining(" ")).trim();
                parties.put(partyName, votes);
                line = reader.readLine();
            }
            double firstElectionNumber = (double) votesAmount / 450;
            int placesAll = 450;
            Map<String, double[]> places = new LinkedHashMap<>();
            for (Map.Entry<String, Integer> party : parties.entrySet()) {
                int place = (int) (party.getValue() / firstElectionNumber);
                double remainder =
                        (party.getValue() / firstElectionNumber) - (int) (party.getValue() / firstElectionNumber);
                places.put(party.getKey(), new double[]{place, remainder});

                placesAll -= place;
            }
            List<Map.Entry<String, double[]>> sorted = places.entrySet().stream().sorted(new Comparator<Map.Entry<String,
                    double[]>>() {
                @Override
                public int compare(Map.Entry<String, double[]> o1, Map.Entry<String, double[]> o2) {
                    return o2.getValue()[1] == o1.getValue()[1] ? o2.getValue()[0] > o1.getValue()[0] ? 1 : -1 :
                            o2.getValue()[1] > o1.getValue()[1] ? 1 : -1;
                }
            }).collect(Collectors.toList());
            for (int i = 0; i < sorted.size() && placesAll > 0; i++) {
                Map.Entry<String, double[]> current = sorted.get(i);
                double[] values = places.get(current.getKey());
                values[0] = values[0] + 1;
                placesAll--;
                places.put(current.getKey(), values);
            }
            places.entrySet().stream().forEachOrdered(entry -> System.out.println(entry.getKey() + " " + (int) entry.getValue()[0]));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("Party One 100000\n" +
                "Party Two 200000\n" +
                "Party Three 400000\n");
        main(new String[0]);
        String expected = "Party One 64\n" +
                "Party Two 129\n" +
                "Party Three 257\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("Party number one 100\n" +
                "Partytwo 100\n");
        main(new String[0]);
        String expected = "Party number one 225\n" +
                "Partytwo 225\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("Party number one 449\n" +
                "Partytwo 1\n");
        main(new String[0]);
        String expected = "Party number one 449\n" +
                "Partytwo 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("Party number one 1\n" +
                "Partytwo 500\n");
        main(new String[0]);
        String expected = "Party number one 1\n" +
                "Partytwo 449\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}