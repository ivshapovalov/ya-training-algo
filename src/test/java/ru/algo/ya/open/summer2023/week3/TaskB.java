package ru.algo.ya.open.summer2023.week3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/*
https://contest.yandex.ru/contest/50340/problems/B/
Бумеры и зумеры

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Площадка для выгула собак — место, где собираются и общаются люди разных возрастов. На одной из площадок Восточного Бирюлева хозяева собак очень дружны и приглашают друг-друга на день рождения.

Человек x не приглашает на день рождения человека y если выполнено хотя бы одно из условий:

- (Возраст человека y) <= 0.5 * (Возраст человека x) + 7

- (Возраст человека y) > (Возраст человека x)

- (Возраст человека y) > 100 и одновременно с этим (Возраст человека x) < 100

Во всех остальных случаях человек x приглашает человека y на день рождения.

Определите суммарное количество приглашений на день рождения.

Формат ввода
В первой строке вводится число n (1 ≤ n ≤ 100000).
Во второй строке вводится n чисел — возраст людей. Возраст находится в промежутке от 1 до 120.

Формат вывода
Выведите одно число — суммарное количество приглашений
 */

public class TaskB extends ContestTask {

    public static void main1(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] nums = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            Arrays.sort(nums);
            int l = 0, r = 0, ans = 0;
            for (int i = 0; i < n; i++) {
                while (l < n && nums[l] <= (0.5 * nums[i]) + 7)
                    l++;
                while (r < n && nums[r] <= nums[i])
                    r++;
                if (r > l + 1) {
                    ans += (r - l - 1);
                }
            }
            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] nums = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            Map<Integer, Integer> freq = new TreeMap<>(Comparator.naturalOrder());
            for (int i = 0; i < n; i++) {
                freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            }
            int ans = 0;
            for (Map.Entry<Integer, Integer> x : freq.entrySet()) {
                for (Map.Entry<Integer, Integer> y : freq.entrySet()) {
                    int ageX = x.getKey();
                    int ageY = y.getKey();
                    if (ageY > (0.5 * ageX) + 7 && ageY <= ageX) {
                        if (x.getKey().equals(y.getKey())) {
                            ans += x.getValue() * (x.getValue() - 1);
                        } else {
                            ans += x.getValue() * y.getValue();
                        }
                    }
                }
            }
            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("2\n" +
                "16 16");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("3\n" +
                "17 16 18");
        main(new String[0]);
        String expected = "2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("5\n" +
                "120 25 30 100 105 ");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_001() {
        provideConsoleInput("4\n" +
                "21 21 21 21");
        main(new String[0]);
        String expected = "12\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}