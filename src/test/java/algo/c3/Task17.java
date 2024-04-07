package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
https://contest.yandex.ru/contest/45468/problems/17/
Игра в пьяницу

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В игре в пьяницу карточная колода раздается поровну двум игрокам. Далее они вскрывают по одной верхней карте, и тот, чья
карта старше, забирает себе обе вскрытые карты, которые кладутся под низ его колоды. Тот, кто остается без карт –
проигрывает. Для простоты будем считать, что все карты различны по номиналу, а также, что самая младшая карта побеждает
самую старшую карту ("шестерка берет туза"). Игрок, который забирает себе карты, сначала кладет под низ своей колоды
карту первого игрока, затем карту второго игрока (то есть карта второго игрока оказывается внизу колоды). Напишите
программу, которая моделирует игру в пьяницу и определяет, кто выигрывает. В игре участвует 10 карт, имеющих значения от
0 до 9, большая карта побеждает меньшую, карта со значением 0 побеждает карту 9.

Формат ввода
Программа получает на вход две строки: первая строка содержит 5 чисел, разделенных пробелами — номера карт первого
игрока, вторая – аналогично 5 карт второго игрока. Карты перечислены сверху вниз, то есть каждая строка начинается с той
карты, которая будет открыта первой.

Формат вывода
Программа должна определить, кто выигрывает при данной раздаче, и вывести слово first или second, после чего вывести
количество ходов, сделанных до выигрыша. Если на протяжении 106 ходов игра не заканчивается, программа должна вывести
слово botva.
*/
public class Task17 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Deque<Integer> first = new ArrayDeque<>();
            int[] firstArr = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            for (int i = firstArr.length - 1; i >= 0; i--) {
                first.push(firstArr[i]);
            }

            Deque<Integer> second = new ArrayDeque<>();
            int[] secondArr = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            for (int i = secondArr.length - 1; i >= 0; i--) {
                second.push(secondArr[i]);
            }
            int move = 0;
            while (!second.isEmpty() && !first.isEmpty() && move <= 1E+6) {
                int num1 = first.pop();
                int num2 = second.pop();
                if ((num1 == 0 && num2 == 9) || (num1 > num2 && !(num1 == 9 && num2 == 0))) {
                    first.addLast(num1);
                    first.addLast(num2);
                } else {
                    second.addLast(num1);
                    second.addLast(num2);
                }
                move++;
            }
            if (move > 1E+6) {
                System.out.println("botva");
            } else if (!first.isEmpty() && second.isEmpty()) {
                System.out.println("first " + move);
            } else {
                System.out.println("second " + move);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1 3 5 7 9\n" +
                "2 4 6 8 0\n" +
                "\n");
        main(new String[0]);
        String expected = "second 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("2 4 6 8 0\n" +
                "1 3 5 7 9\n" +
                "\n");
        main(new String[0]);
        String expected = "first 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("1 7 3 9 4\n" +
                "5 8 0 2 6\n");
        main(new String[0]);
        String expected = "second 23\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
