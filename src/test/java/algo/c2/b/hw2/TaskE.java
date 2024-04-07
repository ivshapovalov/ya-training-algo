package algo.c2.b.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/28738/problems/E/
Дипломы в папках

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В этом году Иван заканчивает школу и поступает в вуз. За время своей учебы он часто участвовал в олимпиадах по
информатике и у него накопилось много дипломов. Иван раскладывал дипломы по папкам совершенно бессистемно, то есть любой
диплом мог оказаться в любой из папок. К счастью, Иван помнит, сколько дипломов лежит в каждой из папок.

Иван хочет принести в приемную комиссию выбранного вуза папку, в которой находится диплом Московской олимпиады по
программированию (такой диплом у Ивана ровно один). Для того чтобы понять, что в данной папке нужного диплома нет, Ивану
нужно просмотреть все дипломы из этой папки. Просмотр одного диплома занимает у него ровно одну секунду и он может
мгновенно переходить к просмотру следующей папки после окончания просмотра предыдущей. Порядок просмотра папок Иван
может выбирать.

По заданному количеству дипломов в каждой из папок требуется определить, за какое наименьшее время в худшем случае Иван
поймет, в какой папке содержится нужный ему диплом.

Формат ввода
В первой строке входного файла записано целое число N (1 ≤ N ≤ 100) - количество папок. Во второй строке записаны N
целых чисел a1, a2, ..., aN (1 ≤ ai ≤ 100) - количество дипломов в каждой из папок.

Формат вывода
Выведите одно число - минимальное количество секунд, необходимое Ивану в худшем случае для определения того, в какой
папке содержится диплом.
 */

public class TaskE extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine().trim());
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            Arrays.sort(input);
            int counter = 0;
            for (int i = 0; i < input.length - 1; i++) {
                counter += input[i];
            }
            System.out.println(counter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("2\n" +
                "2 1\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
