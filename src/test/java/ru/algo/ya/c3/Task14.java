package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/*
https://contest.yandex.ru/contest/45468/problems/14/
Сортировка вагонов lite

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

https://contest.yandex.ru/testsys/statement-image?imageId=6b47dd0f62b0e9704864ecb0be7b66943d3c0afd847bde6b2a7138ce61337b35

К тупику со стороны пути 1 (см. рисунок) подъехал поезд. Разрешается отцепить от поезда один или сразу несколько первых
вагонов и завезти их в тупик (при желании, можно даже завезти в тупик сразу весь поезд). После этого часть из этих
вагонов вывезти в сторону пути 2. После этого можно завезти в тупик еще несколько вагонов и снова часть оказавшихся
вагонов вывезти в сторону пути 2. И так далее (так, что каждый вагон может лишь один раз заехать с пути 1 в тупик, а
затем один раз выехать из тупика на путь 2). Заезжать в тупик с пути 2 или выезжать из тупика на путь 1 запрещается.
Нельзя с пути 1 попасть на путь 2, не заезжая в тупик.

Известно, в каком порядке изначально идут вагоны поезда. Требуется с помощью указанных операций сделать так, чтобы
вагоны поезда шли по порядку (сначала первый, потом второй и т.д., считая от головы поезда, едущего по пути 2 в сторону
от тупика). Напишите программу, определяющую, можно ли это сделать.

Формат ввода
Вводится число N — количество вагонов в поезде (1 ≤ N ≤ 100). Дальше идут номера вагонов в порядке от головы поезда,
едущего по пути 1 в сторону тупика. Вагоны пронумерованы натуральными числами от 1 до N, каждое из которых встречается
ровно один раз.

Формат вывода
Если сделать так, чтобы вагоны шли в порядке от 1 до N, считая от головы поезда, когда поезд поедет по пути 2 из тупика,
можно, выведите сообщение YES, если это сделать нельзя, выведите NO.
*/

public class Task14 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            int[] nums = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();

            Stack<Integer> tupik = new Stack<>();
            int way1CurrentNumber = 0;
            int way2CurrentNumber = 0;
            for (way1CurrentNumber = 0; way1CurrentNumber < nums.length; way1CurrentNumber++) {
                tupik.add(nums[way1CurrentNumber]);
                while (!tupik.isEmpty() && tupik.peek() == way2CurrentNumber + 1) {
                    tupik.pop();
                    way2CurrentNumber++;
                }
                while (!tupik.isEmpty()) {
                    Integer last = tupik.peek();
                    if (last == way2CurrentNumber + 1) {
                        tupik.pop();
                        way2CurrentNumber++;
                    } else {
                        break;
                    }
                }
            }
            System.out.println(tupik.size() == 0 ? "YES" : "NO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_01() {
        provideConsoleInput("3\n" +
                "3 2 1\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("4\n" +
                "4 1 3 2\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("3\n" +
                "2 3 1\n");
        main(new String[0]);
        String expected = "NO\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("3\n" +
                "2 1 3\n");
        main(new String[0]);
        String expected = "YES\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
