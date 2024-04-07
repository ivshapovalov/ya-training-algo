package algo.c5.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/59541/problems/C/
Удаление чисел

Ограничение времени 	1 секунда
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

 Дан массив a из n чисел. Найдите минимальное количество чисел, после удаления которых попарная разность оставшихся
 чисел по модулю не будет превышать 1, то есть после удаления ни одно число не должно отличаться от какого-либо другого
 более чем на 1.

Формат ввода
Первая строка содержит одно целое число n (1≤n≤2⋅105) — количество элементов массива a.

Вторая строка содержит n целых чисел a1,a2,…,an (0≤ai≤105) — элементы массива a.

Формат вывода
Выведите одно число —
ответ на задачу.

*/

public class TaskC extends ContestTask {


    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(r.readLine());
            int[] nums = Arrays.stream(r.readLine()
                    .split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            List<Map.Entry<Integer, Integer>> collect = map.entrySet().stream()
                    .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                    .collect(Collectors.toList());
            int maxFreq = collect.get(0).getValue();
            int resultSum = collect.get(0).getValue();
            List<Integer> resultNums = new ArrayList<>(List.of(collect.get(0).getKey()));
            for (Map.Entry<Integer, Integer> entry : collect) {
                Integer num = entry.getKey();
                Integer freq = entry.getValue();
                if (freq < maxFreq / 2) break;
                int nextNum = num + 1;
                int nextFreq = map.getOrDefault(nextNum, 0);
                if (resultSum < freq + nextFreq) {
                    resultSum = freq + nextFreq;
                    resultNums = List.of(num, nextNum);
                }

            }
            int sum = resultNums.stream().mapToInt(num -> map.getOrDefault(num, 0)).sum();
            System.out.println(n - sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_001() {
        provideConsoleInput("5\n" +
                "1 2 3 4 5\n");
        main(new String[0]);
        String expected = "3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("10\n" +
                "1 1 2 3 5 5 2 2 1 5\n");
        main(new String[0]);
        String expected = "4\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f11() throws FileNotFoundException {
        String testNumber = "11";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
//        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
//        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators("198999\n", getOutput());
    }


}
