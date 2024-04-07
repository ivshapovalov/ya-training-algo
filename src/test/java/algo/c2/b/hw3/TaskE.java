package algo.c2.b.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/28964/problems/E/
Автомобильные номера

Ограничение времени 	2 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Неизвестный водитель совершил ДТП и скрылся с места происшествия. Полиция опрашивает свидетелей. Каждый из них говорит,
что запомнил какие-то буквы и цифры номера. Но при этом свидетели не помнят порядок этих цифр и букв. Полиция хочет
проверить несколько подозреваемых автомобилей. Будем говорить, что номер согласуется с показанием свидетеля, если все
символы, которые назвал свидетель, присутствуют в этом номере (не важно, сколько раз). Формат ввода

Сначала задано число - количество свидетелей. Далее идет M строк, каждая из которых описывает показания очередного
свидетеля. Эти строки непустые и состоят из не более чем 20 символов. Каждый символ в строке - либо цифра, либо
заглавная латинская буква, причём символы могут повторяться.

Затем идёт число - количество номеров. Следующие строки представляют из себя номера подозреваемых машин и имеют такой же
формат, как и показания свидетелей. Формат вывода

Выпишите номера автомобилей, согласующиеся с максимальным количеством свидетелей. Если таких номеров несколько, то
выведите их в том же порядке, в котором они были заданы на входе.
 */

public class TaskE extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int M = Integer.parseInt(reader.readLine().trim());

            Set<Set<String>> witnesses = new HashSet<>();
            for (int i = 0; i < M; i++) {
                Set<String> witness = Arrays.stream(reader.readLine().trim().split("")).collect(Collectors.toSet());
                witnesses.add(witness);
            }
            int N = Integer.parseInt(reader.readLine().trim());
            List<String> numbers = new ArrayList<>();
            Map<Integer, Integer> freq = new HashMap<>();
            for (int index = 0; index < N; index++) {
                String line = reader.readLine().trim();
                List<String> number = Arrays.stream(line.split("")).collect(Collectors.toList());
                numbers.add(line);
                freq.put(index, 0);
                for (Set<String> witness : witnesses) {
                    if (number.containsAll(witness)) {
                        freq.put(index, freq.getOrDefault(index, 0) + 1);
                    }
                }
            }
            int maxFrequency = freq.values().stream().max(Comparator.naturalOrder()).get();
            freq.entrySet().stream()
                    .filter(entry -> entry.getValue() == maxFrequency)
                    .sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(entry -> System.out.println(numbers.get(entry.getKey())));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "ABC\n" +
                "A37\n" +
                "BCDA\n" +
                "2\n" +
                "A317BD\n" +
                "B137AC\n");
        main(new String[0]);
        String expected = "B137AC\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("2\n" +
                "1ABC\n" +
                "3A4B\n" +
                "3\n" +
                "A143BC\n" +
                "C143AB\n" +
                "AAABC1\n");
        main(new String[0]);
        String expected = "A143BC\n" +
                "C143AB\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("3\n" +
                "ABDEX\n" +
                "XBACD\n" +
                "BAXED\n" +
                "2\n" +
                "AAA\n" +
                "ABCD\n");
        main(new String[0]);
        String expected = "AAA\n" +
                "ABCD\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}
