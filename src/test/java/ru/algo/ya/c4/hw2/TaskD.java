package ru.algo.ya.c4.hw2;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/53030/problems/D/
Кубики в зеркале

Ограничение времени 	5 секунд
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Привидение Петя любит играть со своими кубиками. Он любит выкладывать их в ряд и разглядывать свое творение. Недавно
друзья решили подшутить над Петей и поставили в его игровой комнате зеркало. Известно, что привидения не отражаются в
зеркале, а кубики отражаются. Теперь Петя видит перед собой N цветных кубиков, но не знает, какие из этих кубиков
настоящие, а какие — отражение в зеркале. Выясните, сколько кубиков может быть у Пети. Петя видит отражение всех кубиков
в зеркале и часть кубиков, которая находится перед ним. Часть кубиков может быть позади Пети, их он не видит.

https://contest.yandex.ru/testsys/statement-image?imageId=b4cd9568f6383bb03502433fb99a7c8db3a24088ad678633fef2c941d80deb97

Формат ввода
Первая строка входного файла содержит число N ( 1≤ N ≤1000000 ) и количество различных цветов, в которые
могут быть раскрашены кубики — M ( 1≤ M ≤1000000 ). Следующая строка содержит N целых чисел от 1 до M — цвета кубиков.

Формат вывода
Выведите в выходной файл все такие K, что у Пети может быть K кубиков

*/

public class TaskD extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine().trim().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int N = input[0];
            int M = input[1];
            int[] numbersOrigin = Arrays.stream(r.readLine().trim().split(" "))
                    .filter(el -> el != null && !el.isEmpty())
                    .mapToInt(num -> Integer.valueOf(num))
                    .toArray();
            int[] numbers = new int[numbersOrigin.length + 1];
            System.arraycopy(numbersOrigin, 0, numbers, 1, numbersOrigin.length);

            NumberHash numberHash = new NumberHash(numbers);

            List<Integer> result = new ArrayList<>();
            for (int end = 2; end < numbers.length; end = end + 2) {
                boolean isEquals = isPrefixPalindrome(numberHash, end);
                if (isEquals) {
                    result.add(numbersOrigin.length - end / 2);
                }

            }
            result.add(numbers.length - 1);

            System.out.println(
                    result.stream()
                            .sorted()
                            .map(number -> String.valueOf(number))
                            .collect(Collectors.joining(" ")));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isPrefixPalindrome(NumberHash numberHAsh, int end) {
        long[] pow = numberHAsh.pow;
        long[] hash = numberHAsh.hash;
        long[] reverseHash = numberHAsh.reverseHash;
        int p = numberHAsh.p;

        //1x5 1x4 2x3 2x2 1x 1
        //1 1x 2x2 2x3 1x4 1x5
        //len==1
        //hash[from1 + len - 1] + hash[from2 - 1] * pow[len]
        //hash[1,1]=hash[1+1-1]-hash[1-1]*pow[1]
        //reverseHash[2,1]=reverseHash[length-len]-reverseHash[length-2*len]*pow[len]
        boolean result = (hash[end] + reverseHash[reverseHash.length - end - 1] * pow[end]) % p == (reverseHash[reverseHash.length - 1] + hash[0] * pow[end]) % p;
        return result;
    }

    @Test
    public void test_01() {
        provideConsoleInput("6 2\n" +
                "1 1 2 2 1 1\n");
        main(new String[0]);
        String expected = "3 5 6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1 239\n" +
                "57");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("2 239\n" +
                "23 97");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("2 317\n" +
                "57 57");
        main(new String[0]);
        String expected = "1 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    private static class NumberHash {
        final long[] pow;
        final long[] hash;
        final long[] reverseHash;
        final int p = 1_000_000_000 + 7;

        public NumberHash(int[] numbers) {
            int len = numbers.length;
            pow = new long[len];
            hash = new long[len];
            reverseHash = new long[len];

            pow[0] = 1;

            hash[0] = 0;
            reverseHash[0] = 0;
            int x = 257;
            for (int i = 1; i < len; i++) {
                pow[i] = (pow[i - 1] * x) % p;
                hash[i] = (hash[i - 1] * x + numbers[i]) % p;
                reverseHash[i] = (reverseHash[i - 1] * x + numbers[len - i]) % p;
            }
        }
    }

}
