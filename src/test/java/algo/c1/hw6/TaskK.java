package algo.c1.hw6;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/27844/problems/J/
Медиана объединения 2
*/

public class TaskK extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input =
                    Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int l = input[1];
            int[][] sequences = new int[n][l];
            for (int i = 0; i < n; i++) {
                int[] vars =
                        Arrays.stream(reader.readLine().trim().split(" ")).mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                //x1, d1, a, c, m
                int x1 = vars[0];
                int d1 = vars[1];
                int a = vars[2];
                int c = vars[3];
                int m = vars[4];
                int[] seq = new int[l];
                seq[0] = x1;
                int[] d = new int[l];
                d[0] = d1;
                for (int j = 1; j < l; j++) {
                    // di = ((a*di–1+c) mod m),
                    d[j] = (a * d[j - 1] + c) % m;
                    seq[j] = seq[j - 1] + d[j - 1];
                }
                sequences[i] = seq;
            }
            int[] temp = new int[2 * l];
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < sequences.length; i++) {
                for (int j = i + 1; j < sequences.length; j++) {
                    System.arraycopy(sequences[i], 0, temp, 0, l);
                    System.arraycopy(sequences[j], 0, temp, l, l);
                    Arrays.sort(temp);
                    res.append(temp[l - 1]).append("\n");
                }
            }

            System.out.print(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_1() {
        provideConsoleInput("3 6\n" +
                "1 3 1 0 5\n" +
                "0 2 1 1 100\n" +
                "1 6 8 5 11\n");
        main(new String[0]);
        String expected = "7\n" +
                "10\n" +
                "9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
