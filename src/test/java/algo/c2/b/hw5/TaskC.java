package algo.c2.b.hw5;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://contest.yandex.ru/contest/29075/problems/C/
Каждому по компьютеру

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В новом учебном году на занятия в компьютерные классы Дворца Творчества Юных пришли учащиеся, которые были разбиты на N
групп. В i-й группе оказалось Xi человек. Тут же перед директором встала серьезная проблема: как распределить группы по
аудиториям. Во дворце имеется M ≥ N аудиторий, в j-й аудитории имеется Yj компьютеров. Для занятий необходимо, чтобы у
каждого учащегося был компьютер и еще один компьютер был у преподавателя. Переносить компьютеры из одной аудитории в
другую запрещается. Помогите директору!

Напишите программу, которая найдет, какое максимальное количество групп удастся одновременно распределить по аудиториям,
чтобы всем учащимся в каждой группе хватило компьютеров, и при этом остался бы еще хотя бы один для учителя.

Формат ввода
На первой строке входного файла расположены числа N и M (1 ≤ N ≤ M ≤ 1000). На второй строке расположено N чисел — X1,
…, XN (1 ≤ Xi ≤ 1000 для всех 1 ≤ i ≤ N). На третьей строке расположено M чисел Y1, ..., YM (1 ≤ Yi ≤ 1000 для всех 1 ≤
i ≤ M).

Формат вывода
Выведите на первой строке число P - количество групп, которые удастся распределить по аудиториям. На второй строке
выведите распределение групп по аудиториям – N чисел, i-е число должно соответствовать номеру аудитории, в которой
должна заниматься i-я группа. (Нумерация как групп, так и аудиторий, начинается с 1). Если i-я группа осталась без
аудитории, i-е число должно быть равно 0. Если допустимых распределений несколько, выведите любое из них.
 */

public class TaskC extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();

            int n = input[0];
            int m = input[1];
            String[] groupArr = reader.readLine().trim().split(" ");
            String[] classArr = reader.readLine().trim().split(" ");
            List<int[]> groups = IntStream.range(0, groupArr.length)
                    .mapToObj(ind -> new int[]{ind, Integer.parseInt(groupArr[ind])})
                    .sorted((o1, o2) -> o2[1] - o1[1])
                    .collect(Collectors.toList());
            List<int[]> classes = IntStream.range(0, classArr.length)
                    .mapToObj(ind -> new int[]{ind, Integer.parseInt(classArr[ind])})
                    .sorted((o1, o2) -> o2[1] - o1[1])
                    .collect(Collectors.toList());

            Map<Integer, Integer> distribution = new HashMap<>();
            int classIndex = 0;
            int groupIndex;
            for (groupIndex = 0; groupIndex < groups.size() && classIndex < classes.size(); groupIndex++) {
                int[] curGroup = groups.get(groupIndex);
                int[] curClass = classes.get(classIndex);
                if (curGroup[1] + 1 > curClass[1]) {
                    distribution.put(curGroup[0] + 1, 0);
                } else {
                    distribution.put(curGroup[0] + 1, curClass[0] + 1);
                    classIndex++;
                }
            }
            for (int i = groupIndex; i < groups.size(); i++) {
                distribution.put(groups.get(i)[0], 0);
            }
            long distributed = distribution.values().stream().filter(el -> el != 0).count();
            System.out.println(distributed);
            String result = distribution.entrySet().stream().sorted(Map.Entry.comparingByKey())
                    .map(entry -> String.valueOf(entry.getValue()))
                    .collect(Collectors.joining(" ")) + " ";
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_01() {
        provideConsoleInput("1 1\n" +
                "1\n" +
                "2\n");
        main(new String[0]);
        String expected = "1\n" +
                "1 \n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("1 1\n" +
                "1\n" +
                "1\n");
        main(new String[0]);
        String expected = "0\n" +
                "0 \n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("2 2\n" +
                "1 2\n" +
                "2 3\n");
        main(new String[0]);
        String expected = "2\n" +
                "1 2 \n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
