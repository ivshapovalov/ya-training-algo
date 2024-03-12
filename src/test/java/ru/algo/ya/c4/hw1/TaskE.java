package ru.algo.ya.c4.hw1;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://contest.yandex.ru/contest/53029/problems/E/
Поразрядная сортировка

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Поразрядная сортировка является одним из видов сортировки, которые работают практически за линейное от размера
сортируемого массива время. Такая скорость достигается за счет того, что эта сортировка использует внутреннюю структуру
сортируемых объектов. Изначально этот алгоритм использовался для сортировки перфокарт. Первая его компьютерная
реализация была создана в университете MIT Гарольдом Сьюардом (Harold Н. Seward). Опишем алгоритм подробнее. Пусть задан
массив строк s1 , ..., si причём все строки имеют одинаковую длину m. Работа алгоритма состоит из m фаз. На i -ой фазе
строки сортируются па i -ой с конца букве. Происходит это следующим образом. Будем, для простоты, в этой задаче
рассматривать строки из цифр от 0 до 9. Для каждой цифры создается «корзина» («bucket»), после чего строки si
распределяются по «корзинам» в соответствии с i-ой цифрой с конца. Строки, у которых i-ая с конца цифра равна j попадают
в j-ую корзину (например, строка 123 на первой фазе попадет в третью корзину, на второй — во вторую, на третьей — в
первую). После этого элементы извлекаются из корзин в порядке увеличения номера корзины. Таким образом, после первой
фазы строки отсортированы по последней цифре, после двух фаз — по двум последним, ..., после m фаз — по всем. При важно,
чтобы элементы в корзинах сохраняли тот же порядок, что и в исходном массиве (до начала этой фазы). Например, если
массив до первой фазы имеет вид: 111, 112, 211, 311, то элементы по корзинам распределятся следующим образом: в первой
корзине будет. 111, 211, 311, а второй: 112. Напишите программу, детально показывающую работу этого алгоритма на
заданном массиве.

Формат ввода
Первая строка входного файла содержит целое число n (1≤ n ≤1000) . Последующие n строк содержат каждая по одной строке
si . Длины всех si , одинаковы и не превосходят 20. Все si состоят только из цифр от 0 до 9.

Формат вывода
В выходной файл выведите исходный массив строк в, состояние «корзин» после распределения элементов по ним для каждой
фазы и отсортированный массив. Следуйте формату, приведенному в примере.

*/

public class TaskE extends ContestTask {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(r.readLine().trim());
            String[] nums = new String[N];
            for (int i = 0; i < N; i++) {
                String num = r.readLine().trim();
                nums[i] = num;
            }
            int len = nums[0].length();
            System.out.println("Initial array:");
            System.out.println(Arrays.stream(nums).collect(Collectors.joining(", ")));
            System.out.println("**********");

            List<String>[] buckets = new List[10];
            buckets[0] = Arrays.stream(nums).collect(Collectors.toList());
            for (int col = 0; col < len; col++) {
                List<String>[] newBuckets = new List[10];
                for (int i = 0; i < buckets.length; i++) {
                    if (buckets[i] != null) {
                        for (String num : buckets[i]) {
                            int digit = num.charAt(len - 1 - col) - '0';
                            if (newBuckets[digit] == null) {
                                List<String> list = new ArrayList<>();
                                list.add(num);
                                newBuckets[digit] = list;
                            } else {
                                newBuckets[digit].add(num);
                            }
                        }
                    }
                }
                buckets = newBuckets;
                System.out.println("Phase " + (col + 1));
                IntStream.rangeClosed(0, 9).forEach(ind -> {
                    System.out.println(String.format("Bucket %s: %s", ind, newBuckets[ind] == null ? "empty" : newBuckets[ind].stream().map(el -> String.valueOf(el)).collect(Collectors.joining(", "))));
                });
                System.out.println("**********");


            }
            System.out.println("Sorted array:");
            System.out.println(Arrays.stream(buckets).filter(el -> el != null).flatMap(bucket -> bucket.stream()).collect(Collectors.joining(", ")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("9\n" +
                "12\n" +
                "32\n" +
                "45\n" +
                "67\n" +
                "98\n" +
                "29\n" +
                "61\n" +
                "35\n" +
                "09\n");
        main(new String[0]);
        String expected = "Initial array:\n" +
                "12, 32, 45, 67, 98, 29, 61, 35, 09\n" +
                "**********\n" +
                "Phase 1\n" +
                "Bucket 0: empty\n" +
                "Bucket 1: 61\n" +
                "Bucket 2: 12, 32\n" +
                "Bucket 3: empty\n" +
                "Bucket 4: empty\n" +
                "Bucket 5: 45, 35\n" +
                "Bucket 6: empty\n" +
                "Bucket 7: 67\n" +
                "Bucket 8: 98\n" +
                "Bucket 9: 29, 09\n" +
                "**********\n" +
                "Phase 2\n" +
                "Bucket 0: 09\n" +
                "Bucket 1: 12\n" +
                "Bucket 2: 29\n" +
                "Bucket 3: 32, 35\n" +
                "Bucket 4: 45\n" +
                "Bucket 5: empty\n" +
                "Bucket 6: 61, 67\n" +
                "Bucket 7: empty\n" +
                "Bucket 8: empty\n" +
                "Bucket 9: 98\n" +
                "**********\n" +
                "Sorted array:\n" +
                "09, 12, 29, 32, 35, 45, 61, 67, 98\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }


}
