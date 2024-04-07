package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;


/*
https://contest.yandex.ru/contest/46304/problems/A/
Подземная доставка

Ограничение времени 	2 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Для ускорения работы служб доставки под городом Длинноградом был прорыт тоннель, по которому ходит товарный поезд,
останавливающийся на промежуточных станциях возле логистических центров. На станциях к концу поезда могут быть
присоединены вагоны с определенными товарами, а также от его конца может быть отцеплено некоторое количество вагонов или
может быть проведена ревизия, во время которой подсчитывается количество вагонов с определенным товаром.

Обработайте операции в том порядке, в котором они производились, и ответьте на запросы ревизии.

Формат ввода
В первой строке вводится число N (1≤N≤100000) — количество операций, произведенных над поездом.

В каждой из следующих N строк содержится описание операций. Каждая операция может иметь один из трех типов:

add <количество вагонов> <название товара> — добавить в конец поезда <количество вагонов> с грузом <название товара>.
Количество вагонов не может превышать 109, название товара — одна строка из строчных латинских символов длиной до 20.

delete <количество вагонов> — отцепить от конца поезда <количество вагонов>. Количество отцепляемых вагонов не
превосходит длины поезда.

get <название товара> — определить количество вагонов с товаром <название товара> в поезде. Название товара — одна
строка из строчных латинских символов длиной до 20.

Формат вывода
На каждый запрос о количестве вагонов с определенным товаром выведите одно число — количество вагонов с
таким товаром. Запросы надо обрабатывать в том порядке, как они поступали.
*/
public class Task41 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            Train train = new Train();
            for (int i = 0; i < n; i++) {
                String[] input = reader.readLine().trim().split(" ");
                String command = input[0];

                switch (command) {
                    case "add":
                        long amount = Long.valueOf(input[1]);
                        String good = input[2];
                        train.add(good, amount);
                        break;
                    case "delete":
                        int carriageDelete = Integer.valueOf(input[1]);
                        train.delete(carriageDelete);
                        break;
                    case "get":
                        String goodToGet = input[1];
                        System.out.print(train.get(goodToGet) + "\n");
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("7\n" +
                "add 10 oil\n" +
                "add 20 coal\n" +
                "add 5 oil\n" +
                "get coal\n" +
                "get oil\n" +
                "add 1 coal\n" +
                "get coal\n");
        main(new String[0]);
        String expected = "20\n" +
                "15\n" +
                "21\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("6\n" +
                "add 5 oil\n" +
                "get coal\n" +
                "add 7 liverstock\n" +
                "delete 10\n" +
                "get oil\n" +
                "get liverstock\n");
        main(new String[0]);
        String expected = "0\n" +
                "2\n" +
                "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("4\n" +
                "add 5 a\n" +
                "add 7 b\n" +
                "delete 20\n" +
                "get a\n");
        main(new String[0]);
        String expected = "0\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    private static class Train {
        Deque<Carriage> train = new ArrayDeque<>();
        Map<String, Long> store = new HashMap<>();

        public void add(String good, long amount) {
            train.addLast(new Carriage(good, amount));
            store.put(good, store.getOrDefault(good, 0L) + amount);
        }

        public void delete(long amount) {
            if (!train.isEmpty()) {
                while (amount > 0 && !train.isEmpty()) {
                    Carriage last = train.pollLast();
                    if (last.getAmount() > amount) {
                        long remained = last.getAmount() - amount;
                        train.offerLast(new Carriage(last.getGood(), remained));
                        store.put(last.getGood(), store.get(last.getGood()) - amount);
                        amount = 0;
                    } else {
                        amount -= last.getAmount();
                        store.put(last.getGood(), store.get(last.getGood()) - last.getAmount());
                    }
                }
            }
        }

        public long get(String good) {
            return store.getOrDefault(good, 0L);
        }

        private class Carriage {
            long amount;
            String good;

            public Carriage(String good, long amount) {
                this.amount = amount;
                this.good = good;
            }

            public long getAmount() {
                return amount;
            }

            public void setAmount(long amount) {
                this.amount = amount;
            }

            public String getGood() {
                return good;
            }
        }
    }
}
