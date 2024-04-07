package algo.c5.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/59540/problems/F/
Колесо Фортуны

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Развлекательный телеканал транслирует шоу «Колесо Фортуны». В процессе игры участники шоу крутят большое колесо,
разделенное на сектора. В каждом секторе этого колеса записано число. После того как колесо останавливается, специальная
стрелка указывает на один из секторов. Число в этом секторе определяет выигрыш игрока.

Юный участник шоу заметил, что колесо в процессе вращения замедляется из-за того, что стрелка задевает за выступы на
колесе, находящиеся между секторами. Если колесо вращается с угловой скоростью v градусов в секунду, и стрелка, переходя
из сектора X к следующему сектору, задевает за очередной выступ, то текущая угловая скорость движения колеса уменьшается
на k градусов в секунду. При этом если v ≤ k, то колесо не может преодолеть препятствие и останавливается. Стрелка в
этом случае будет указывать на сектор X.

Юный участник шоу собирается вращать колесо. Зная порядок секторов на колесе, он хочет заставить колесо вращаться с
такой начальной скоростью, чтобы после остановки колеса стрелка указала на как можно большее число. Колесо можно вращать
в любом направлении и придавать ему начальную угловую скорость от a до b градусов в секунду.

Требуется написать программу, которая по заданному расположению чисел в секторах, минимальной и максимальной начальной
угловой скорости вращения колеса и величине замедления колеса при переходе через границу секторов вычисляет максимальный
выигрыш.
https://contest.yandex.ru/testsys/statement-image?imageId=0ed8d34b4b95f9faf3c6c711b435feaf710a814143f9cdad9e83074505d16ad5

Формат ввода
Первая строка входного файла содержит целое число n — количество секторов колеса (3 ≤ n ≤ 100).

Вторая строка входного файла содержит n положительных целых чисел, каждое из которых не превышает 1000 — числа,
записанные в секторах колеса. Числа приведены в порядке следования секторов по часовой стрелке. Изначально стрелка
указывает на первое число.

Третья строка содержит три целых числа: a, b и k (1 ≤ a ≤ b ≤ 109, 1 ≤ k ≤ 109).

Формат вывода
В выходном файле должно содержаться одно целое число — максимальный выигрыш.

*/

public class TaskF extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int n = input[0];
            int k = input[1];
            int[] prices = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_001() {
        provideConsoleInput("5\n" +
                "1 2 3 4 5\n" +
                "3 5 2\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("5\n" +
                "1 2 3 4 5\n" +
                "15 15 2\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("5\n" +
                "5 4 3 2 1\n" +
                "2 5 2\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
