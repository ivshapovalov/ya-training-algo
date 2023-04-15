package ru.algo.ya.c1.hw5;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/*
https://contest.yandex.ru/contest/27794/problems/F/
Кондиционеры
*/
public class TaskF extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine());
            Integer[] demand =
                    Arrays.stream(reader.readLine().trim().split(" ")).map(el -> Integer.valueOf(el)).toArray(Integer[]::new);
            Arrays.sort(demand);
            int m = Integer.valueOf(reader.readLine());
            List<Cond> supply = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int[] cond =
                        Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                supply.add(new Cond(cond[0], cond[1]));
            }

            Collections.sort(supply, (o1, o2) -> o1.getPrice() == o2.getPrice() ? o2.getPower() - o1.getPower() :
                    o1.getPrice() - o2.getPrice());
            int result = 0;
            int end = 0;
            for (int d = 0; d < n; d++) {
                for (int s = end; s < m; s++) {
                    Cond cond = supply.get(s);
                    if (cond.getPower() >= demand[d]) {
                        result += cond.getPrice();
                        end = s;
                        break;
                    }
                }
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1\n" +
                "800\n" +
                "1\n" +
                "800 1000\n");
        main(new String[0]);
        String expected = "1000\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("3\n" +
                "1 2 3\n" +
                "4\n" +
                "1 10\n" +
                "1 5\n" +
                "10 7\n" +
                "2 3\n");
        main(new String[0]);
        String expected = "13\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    static class Cond {
        int power;
        int price;

        public Cond(int power, int price) {
            this.power = power;
            this.price = price;
        }

        public int getPower() {
            return power;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cond cond = (Cond) o;
            return power == cond.power && price == cond.price;
        }

        @Override
        public int hashCode() {
            return Objects.hash(power, price);
        }
    }
}
