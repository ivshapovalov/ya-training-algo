package algo.c1.hw7;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/27883/problems/F/
Современники

Ограничение времени 	3 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Группа людей называется современниками, если был такой момент, когда они могли собраться все вместе и обсуждать
какой-нибудь важный вопрос. Для этого в тот момент, когда они собрались, каждому из них должно было уже исполниться 18
лет, но еще не исполниться 80 лет.

Вам дан список великих людей с датами их жизни. Выведите всевозможные максимальные множества современников. Множество
современников будем называть максимальным, если нет другого множества современников, которое включает в себя всех людей
из первого множества.

Будем считать, что в день своего 18-летия человек уже может принимать участие в такого рода собраниях, а в день
80-летия, равно как и в день своей смерти, — нет.

Формат ввода
Сначала на вход программы поступает число N — количество людей (1 ≤ N ≤ 10000). Далее в N строках вводится по шесть
чисел — первые три задают дату (день, месяц, год) рождения, следующие три — дату смерти (она всегда не ранее даты
рождения). День (в зависимости от месяца, а в феврале — еще и года) от 1 до 28, 29, 30 или 31, месяц — от 1 до 12, год —
от 1 до 2005.

Формат вывода
Программа должна вывести все максимальные множества современников. Каждое множество должно быть записано на отдельной
строке и содержать номера людей (люди во входных данных нумеруются в порядке их задания, начиная с 1). Номера людей
должны разделяться пробелами.

Никакое множество не должно быть указано дважды.

Если нет ни одного непустого максимального множества, выведите одно число 0.

Гарантируется, что входные данные будут таковы, что размер выходных данных для правильного ответа не превысит 2 Мб.

*/
public class TaskF extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            Map<LocalDate, List<int[]>> events = new LinkedHashMap<>();
            for (int i = 1; i <= n; i++) {
                int[] human = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();

                String birth =
                        appendZeros(human[2], 4) + "-" + appendZeros(human[1], 2) + "-" + appendZeros(human[0], 2);
                String death =
                        appendZeros(human[5], 4) + "-" + appendZeros(human[4], 2) + "-" + appendZeros(human[3], 2);
                LocalDate dateAdult = LocalDate.parse(birth).plusYears(18);
                LocalDate dateDeath = LocalDate.parse(death);

                if (dateAdult.isBefore(dateDeath)) {

                    List<int[]> birthEvent = events.getOrDefault(dateAdult, new ArrayList<>());
                    birthEvent.add(new int[]{i, 1});
                    events.put(dateAdult, birthEvent);

                    List<int[]> deathEvent = events.getOrDefault(dateDeath, new ArrayList<>());
                    deathEvent.add(new int[]{i, -1});
                    events.put(dateDeath, deathEvent);
                }
            }
            int onlineCounter = 0;
            List<Integer> online = new ArrayList<>();
            Map<Integer, Set<List<Integer>>> result = new HashMap<>();
            for (Map.Entry<LocalDate, List<int[]>> event :
                    events.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList())) {
                List<int[]> value = event.getValue();
                for (int i = 0; i < value.size(); i++) {
                    int[] input = value.get(i);
                    if (input[1] == 1) {
                        Set<List<Integer>> prev = result.getOrDefault(onlineCounter, new HashSet<>());
                        ArrayList<Integer> newOnline = new ArrayList<>(online);
                        Collections.sort(newOnline);
                        prev.remove(newOnline);
                        online.add(input[0]);
                        onlineCounter++;
                    } else {
                        Set<List<Integer>> prev = result.getOrDefault(onlineCounter, new HashSet<>());
                        ArrayList<Integer> newOnline = new ArrayList<>(online);
                        Collections.sort(newOnline);
                        prev.add(newOnline);
                        result.put(onlineCounter, prev);
                        onlineCounter--;
                        online.remove((Integer) input[0]);
                    }
                }

            }
            if (result.size() > 0) {
                result.entrySet().stream().forEach(entry ->
                        {
                            entry.getValue().stream().forEach(list -> System.out.println(list.stream().map(el -> String.valueOf(el)).collect(Collectors.joining(" "))));

                        }
                );
            } else {
                System.out.println(0);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String appendZeros(int human, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len - String.valueOf(human).length(); i++) {
            sb.append("0");
        }
        sb.append(human);
        return sb.toString();
    }


    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "2 5 1988 13 11 2005\n" +
                "1 1 1 1 1 30\n" +
                "1 1 1910 1 1 1990\n");
        main(new String[0]);
        String expected = "2\n" +
                "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("3\n" +
                "2 5 1968 13 11 2005\n" +
                "1 1 1 1 1 30\n" +
                "1 1 1910 1 1 1990\n");
        main(new String[0]);
        String expected = "2\n" +
                "1 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("2\n" +
                "14 00 18 00\n" +
                "10 00 14 01\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
