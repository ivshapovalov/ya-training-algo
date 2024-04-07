package algo.c2.b.hw6;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/29188/problems/D/
Вырубка леса

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Фермер Николай нанял двух лесорубов: Дмитрия и Федора, чтобы вырубить лес, на месте которого должно быть кукурузное
поле. В лесу растут X деревьев.

Дмитрий срубает по A деревьев в день, но каждый K-й день он отдыхает и не срубает ни одного дерева. Таким образом,
Дмитрий отдыхает в K-й, 2K-й, 3K-й день, и т.д.

Федор срубает по B деревьев в день, но каждый M-й день он отдыхает и не срубает ни одного дерева. Таким образом, Федор
отдыхает в M-й, 2M-й, 3M-й день, и т.д.

Лесорубы работают параллельно и, таким образом, в дни, когда никто из них не отдыхает, они срубают A + B деревьев, в
дни, когда отдыхает только Федор — A деревьев, а в дни, когда отдыхает только Дмитрий — B деревьев. В дни, когда оба
лесоруба отдыхают, ни одно дерево не срубается.

Фермер Николай хочет понять, за сколько дней лесорубы срубят все деревья, и он сможет засеять кукурузное поле.

Требуется написать программу, которая по заданным целым числам A, K, B, M и X определяет, за сколько дней все деревья в
лесу будут вырублены. Формат ввода

Входной файл содержит пять целых чисел, разделенных пробелами: A, K, B, M и X (1 ≤ A, B ≤ 109 , 2 ≤ K, M ≤ 1018, 1 ≤ X ≤
1018). Формат вывода

Выходной файл должен содержать одно целое число — искомое количество дней.

 */

public class TaskD extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            long[] nums = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToLong(el -> Long.valueOf(el)).toArray();
            int A = (int) nums[0];
            long K = nums[1];
            int B = (int) nums[2];
            long M = nums[3];
            long X = nums[4];

            long result = binSearch(A, K, B, M, X);
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long binSearch(int A, long K, int B, long M, long X) {
        long l = 0;
        long r = Long.MAX_VALUE / ((A + B) / 2);
        while (l <= r) {
            long m = l + (r - l) / 2;
            BigInteger amount = getAmount(A, K, B, M, m);
            if (amount.compareTo(BigInteger.valueOf(X)) < 0) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return l;
    }

    private static BigInteger getAmount(int A, long K, int B, long M, long m) {
        long restDays1 = m / K;
        long restDays2 = m / M;
        long workDays1 = m - restDays1;
        long workDays2 = m - restDays2;
        BigInteger result1 = BigInteger.valueOf(A).multiply(BigInteger.valueOf(workDays1));
        BigInteger result2 = BigInteger.valueOf(B).multiply(BigInteger.valueOf(workDays2));
        return result1.add(result2);
    }

    @Test
    public void test_01() {
        provideConsoleInput("1 2 1 3 10\n");
        main(new String[0]);
        String expected = "8\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1 2 1 3 11\n");

        main(new String[0]);
        String expected = "9\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("19 3 14 6 113\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_04() {
        provideConsoleInput("19 3 14 6 114\n");
        main(new String[0]);
        String expected = "5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_15() {
        provideConsoleInput("1 1000000000000000000 2 1000000000000000000 999999999999999999\n\n");
        main(new String[0]);
        String expected = "333333333333333333\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
