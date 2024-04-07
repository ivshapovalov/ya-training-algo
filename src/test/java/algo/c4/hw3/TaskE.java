package algo.c4.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/53031/problems/E/
На санях

Ограничение времени 	5 секунд
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В начале XVIII века еще не было самолетов, поездов и автомобилей, поэтому все междугородние зимние поездки совершались
на санях. Как известно, с дорогами в России тогда было даже больше проблем, чем сейчас, а именно на N существовавших
тогда городов имелась N-1 дорога, каждая из которых соединяла два города. Из каждого города можно было добраться в любой
другой (возможно, через промежуточные города). В каждом городе была почтовая станция («ям»), на которой можно было
пересесть в другие сани. При этом ямщики могли долго запрягать (для каждого города известно время, которое ямщики в этом
городе тратят на подготовку саней к поездке) и быстро ехать (также для каждого города известна скорость, с которой ездят
ямщики из него). Можно считать, что количество ямщиков в каждом городе не ограничено.

Если бы олимпиада проводилась 300 лет назад, то путь участников занимал бы гораздо большее время, чем сейчас. Допустим,
из каждого города в Москву выезжает участник олимпиады и хочет добраться до Москвы за наименьшее время (не обязательно
по кратчайшему пути: он может заезжать в любые города, через один и тот же город можно проезжать несколько раз). Сначала
он едет с ямщиком из своего города. Приехав в любой город, он может либо сразу ехать дальше, либо пересесть. В первом
случае он едет с той же скоростью, с какой ехал раньше. Если сменить ямщика, он сначала ждет, пока ямщик подготовит
сани, и только потом едет с ним (с той скоростью, с которой ездит этот ямщик). В пути можно делать сколько угодно
пересадок.

Определите, какое время необходимо, чтобы все участники олимпиады доехали из своего города в Москву 300 лет назад. Все
участники выезжают из своих городов одновременно.

Формат ввода

В первой строке входного файла дано натуральное число N, не превышающее 2000 — количество городов, соединенных дорогами.
Город с номером 1 является столицей.

Следующие N строк содержат по два целых числа: Ti и Vi — время подготовки саней в городе i, выраженное в часах, и
скорость, с которой ездят ямщики из города i, в километрах в час (0 ≤ Ti ≤ 100, 1 ≤ Vi ≤ 100).

Следующие N–1 строк содержат описания дорог того времени. Каждое описание состоит из трех чисел Aj, Bj и Sj, где Aj и Bj
— номера соединенных городов, а Sj — расстояние между ними в километрах (1 ≤ Aj ≤ N, 1 ≤ Bj ≤ N, Aj ≠ Bj, 1 ≤ Sj ≤
10000). Все дороги двусторонние, то есть если из A можно проехать в B, то из B можно проехать в A. Гарантируется, что из
всех городов можно добраться в столицу.

Формат вывода

Сначала выведите одно вещественное число — время в часах, в которое в Москву приедет последний участник.

Далее выведите путь участника, который приедет самым последним (если таких участников несколько, выведите путь любого из
них). Выведите город, из которого этот участник выехал первоначально, и перечислите в порядке посещения те города, в
которых он делал пересадки. Последовательность должна заканчиваться столицей.

При проверке ответ будет засчитан, если из трех величин «время путешествия по выведенному пути», «выведенное время» и
«правильный ответ» каждые две отличаются менее чем на 0.0001.

*/

public class TaskE extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int cityAmount = Integer.parseInt(r.readLine().trim());
            Map<Integer, City> cities = new HashMap<>();

            for (int cityIndex = 1; cityIndex <= cityAmount; cityIndex++) {
                int[] input = Arrays.stream(r.readLine().trim().split(" "))
                        .filter(el -> el != null && !el.isEmpty())
                        .mapToInt(el -> Integer.parseInt(el)).toArray();
                int prepareHours = input[0];
                int speed = input[1];
                cities.putIfAbsent(cityIndex, cities.getOrDefault(cityIndex, new City(cityIndex, prepareHours, speed)));
            }
            for (int cityIndex = 1; cityIndex < cityAmount; cityIndex++) {
                int[] input = Arrays.stream(r.readLine().trim().split(" "))
                        .filter(el -> el != null && !el.isEmpty())
                        .mapToInt(el -> Integer.parseInt(el)).toArray();
                int fromIndex = input[0];
                int toIndex = input[1];
                int distance = input[2];
                City from = cities.get(fromIndex);
                City to = cities.get(toIndex);
                from.roads.add(new Road(from, to, distance));
                to.roads.add(new Road(to, from, distance));
            }
            int toIndex = 1;
            double maxTotalTime = 0;
            List<City> maxPath = new ArrayList<>();
            List<City> maxFullPath = new ArrayList<>();

            City destination = cities.get(1);
            for (int fromIndex = 1; fromIndex <= cityAmount; fromIndex++) {
                cities.entrySet().forEach(entry -> entry.getValue().update());
                if (fromIndex == toIndex) {
                    continue;
                }
                //0 - city index
                //speed
                //totalTime
                PriorityQueue<Chunk> q = new PriorityQueue<>(new Comparator<Chunk>() {
                    @Override
                    public int compare(Chunk o1, Chunk o2) {
                        return (int) (o2.speed == o1.speed ? o1.totalTime - o2.totalTime : o2.speed - o1.speed);
                    }
                });
                City from = cities.get(fromIndex);
                if (from == null) {
                    System.out.println("-1");
                    return;
                }
                q.add(new Chunk(from, from.speed, from.prepareHours, new ArrayList<>(List.of(from)), new ArrayList<>(List.of(from))));

                while (!q.isEmpty()) {
                    Chunk chunk = q.poll();
                    City startCity = chunk.from;
                    if (startCity.equals(destination)) {
                        continue;
                    }
                    int startSpeed = chunk.speed;
                    double totalTime = chunk.totalTime;
                    List<City> currentPath = chunk.path;
                    List<City> currentFullPath = chunk.fullPath;
                    List<Road> roads = startCity.roads;
                    if (roads == null) continue;
                    for (Road road : roads) {
                        City finishCity = road.to;
                        double roadTotalTime = totalTime + 1.0 * road.distance / startSpeed;
                        if (finishCity.totalTime > roadTotalTime) {
                            finishCity.totalTime = roadTotalTime;
                            finishCity.path = currentPath;
                            finishCity.fullPath = currentFullPath;
                            ArrayList<City> previousPath = new ArrayList<>(currentPath);
                            ArrayList<City> previousFullPath = new ArrayList<>(currentFullPath);
                            previousFullPath.add(finishCity);
                            ArrayList<City> updatedPath = new ArrayList<>(currentPath);
                            updatedPath.add(finishCity);
                            q.add(new Chunk(finishCity, startSpeed, roadTotalTime, previousPath, previousFullPath));
                            q.add(new Chunk(finishCity, finishCity.speed, roadTotalTime + finishCity.prepareHours, updatedPath, previousFullPath));
                        }
                    }

                }
                if (destination != null && maxTotalTime < destination.totalTime) {
                    maxTotalTime = destination.totalTime;
                    maxPath = destination.path;
                    maxFullPath = destination.fullPath;

                }
            }
            maxPath.add(destination);
            System.out.println(BigDecimal.valueOf(maxTotalTime).setScale(10, RoundingMode.UP));
            System.out.println(maxPath.stream().map(el -> String.valueOf(el.value)).collect(Collectors.joining(" ")));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("4\n" +
                "1 1\n" +
                "10 30\n" +
                "5 40\n" +
                "1 10\n" +
                "1 2 300\n" +
                "1 3 400\n" +
                "2 4 100");
        main(new String[0]);
        String expected = "31.0000000000\n" +
                "4 2 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("3\n" +
                "1 1\n" +
                "0 10\n" +
                "0 55\n" +
                "1 2 100\n" +
                "2 3 10");
        main(new String[0]);
        String expected = "3.0000000000\n" +
                "2 3 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f06() throws FileNotFoundException {
        String testNumber = "06";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
//        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
//        String expected = getFileContent(answerFilePath);
        String expected = "242.5795454545\n" +
                "0.0000000000 84.4166666667 135.2945454545 27.5428571429 89.0985915493 235.2375000000 164.3160563380 132.4100000000 160.9166666667 144.6943521595 54.6400000000 242.5795454545 103.3068181818 44.8372093023 62.1694915254 181.7437974684 56.7285714286 85.9437974684 70.8987341772 75.4375000000\n";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f08() throws FileNotFoundException {
        String testNumber = "08";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
//        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
//        String expected = getFileContent(answerFilePath);
        String expected = "213.4193548387\n" +
                "0.0000000000 31.1555555556 92.9186046512 96.5000000000 78.0641025641 114.6470588235 65.9800000000 106.7647058824 115.4218750000 82.3620689655 151.4782608696 78.7101449275 75.7758620690 213.4193548387 113.3157894737 76.9333333333 144.4741602067 94.0862068966 39.2528735632 109.7526881720\n";
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    static class Chunk {
        private final City from;
        private final int speed;
        private final double totalTime;
        private List<City> path = new ArrayList<>();
        private List<City> fullPath = new ArrayList<>();

        public Chunk(City from, int speed, double totalTime) {
            this.from = from;
            this.speed = speed;
            this.totalTime = totalTime;
        }

        public Chunk(City from, int speed, double totalTime, List<City> path, List<City> fullPath) {
            this.from = from;
            this.speed = speed;
            this.totalTime = totalTime;
            this.path = path;
            this.fullPath = fullPath;
        }
    }

    static class Road {
        private final City from;
        private final City to;
        private final int distance;

        public Road(City from, City to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }

    static class City {
        private final int value;
        private double totalTime;
        private final int prepareHours;
        private final int speed;
        private List<City> path = new ArrayList<>();
        private List<City> fullPath = new ArrayList<>();
        private final List<Road> roads = new ArrayList<>();

        public City(int value, int prepareHours, int speed) {
            this.value = value;
            this.prepareHours = prepareHours;
            this.speed = speed;
            this.totalTime = Long.MAX_VALUE;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            City city = (City) o;
            return value == city.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        public int getValue() {
            return value;
        }

        public double getTotalTime() {
            return totalTime;
        }

        public void update() {
            this.totalTime = Long.MAX_VALUE;
            this.path = new ArrayList<>();
            this.fullPath = new ArrayList<>();
        }
    }

}
