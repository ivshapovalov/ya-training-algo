package algo.c5.hw1;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/59539/problems/B/
Футбольный комментатор

Ограничение времени 	2 секунды
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Раунд плей-офф между двумя командами состоит из двух матчей. Каждая команда проводит по одному матчу «дома» и «в
гостях». Выигрывает команда, забившая большее число мячей. Если же число забитых мячей совпадает, выигрывает команда,
забившая больше мячей «в гостях». Если и это число мячей совпадает, матч переходит в дополнительный тайм или серию
пенальти.

Вам дан счёт первого матча, а также счёт текущей игры (которая ещё не завершилась). Помогите комментатору сообщить,
сколько голов необходимо забить первой команде, чтобы победить, не переводя игру в дополнительное время.

Формат ввода
В первой строке записан счёт первого мачта в формате G1:G2, где G1 — число мячей, забитых первой командой, а G2 — число
мячей, забитых второй командой.
Во второй строке записан счёт второго (текущего) матча в аналогичном формате. Все числа в записи счёта не превышают 5.
В третьей строке записано число 1, если первую игру первая команда провела «дома», или 2, если «в гостях».

Формат вывода
Выведите единственное целое число "— необходимое количество мячей.

*/

public class TaskB extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int[] first = Arrays.stream(r.readLine().split(":")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int[] current = Arrays.stream(r.readLine().split(":")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            int isHome = Integer.parseInt(r.readLine());

            int goalsDiff = first[0] + current[0] - first[1] - current[1];
            int goalsGuestDiff = isHome == 1 ? current[0] - first[1] : first[0] - current[1];
            if (goalsDiff > 0) {
                System.out.println(0);
            } else if (goalsDiff == 0) {
                if (goalsGuestDiff == 0) {
                    System.out.println(1);
                } else if (goalsGuestDiff > 0) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                }
            } else {
                if (isHome == 1) {
                    if (goalsGuestDiff == 0) {
                        System.out.println(Math.abs(goalsDiff));
                    } else if (goalsGuestDiff > 0) {
                        System.out.println(Math.abs(goalsDiff));
                    } else {
                        if (Math.abs(goalsDiff) == Math.abs(goalsGuestDiff)) {
                            System.out.println(Math.abs(goalsDiff) + 1);
                        } else if (Math.abs(goalsDiff) > Math.abs(goalsGuestDiff)) {
                            System.out.println(Math.abs(goalsDiff));
                        } else {
                            System.out.println(Math.abs(goalsDiff) + 1);
                        }
                    }
                } else {
                    if (goalsGuestDiff == 0) {
                        System.out.println(Math.abs(goalsDiff) + 1);
                    } else if (goalsGuestDiff > 0) {
                        System.out.println(Math.abs(goalsDiff));
                    } else {
                        System.out.println(Math.abs(goalsDiff) + 1);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_001() {
        provideConsoleInput("0:0\n" +
                "0:0\n" +
                "1\n");
        main(new String[0]);
        String expected = "1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("0:2\n" +
                "0:3\n" +
                "1\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_003() {
        provideConsoleInput("0:2\n" +
                "0:3\n" +
                "2\n");
        main(new String[0]);
        String expected = "6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_045() {
        provideConsoleInput("0:1\n" +
                "4:5\n" +
                "1\n");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
