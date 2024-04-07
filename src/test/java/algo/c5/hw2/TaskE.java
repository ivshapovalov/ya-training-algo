package algo.c5.hw2;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://contest.yandex.ru/contest/59540/problems/E/
Амбициозная улитка

Ограничение времени 	5 секунд
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Домашний питомец мальчика Васи — улитка Петя. Петя обитает на бесконечном в обе стороны вертикальном столбе, который
 для удобства можно представить как числовую прямую. Изначально Петя находится в точке 0.

Вася кормит Петю ягодами. У него есть n ягод, каждая в единственном экземпляре. Вася знает, что если утром он даст Пете
ягоду с номером i, то поев и набравшись сил, за остаток дня Петя поднимется на ai единиц вверх по столбу, но при этом за
ночь, потяжелев, съедет на bi единиц вниз. Параметры различных ягод могут совпадать.

Пете стало интересно, а как оно там, наверху, и Вася взялся ему в этом помочь. Ближайшие n дней он будет кормить Петю
ягодами из своего запаса таким образом, чтобы максимальная высота, на которой побывал Петя за эти n дней была
максимальной. К сожалению, Вася не умеет программировать, поэтому он попросил вас о помощи. Найдите, максимальную
высоту, на которой Петя сможет побывать за эти n дней и в каком порядке Вася должен давать Пете ягоды, чтобы Петя смог
её достичь!

Формат ввода
В первой строке входных данных дано число n (1≤n≤5⋅105) — количество ягод у Васи. В последующих n строках
описываются параметры каждой ягоды. В i+1 строке дано два числа ai и bi (0≤ai,bi≤109) — то, насколько поднимется улитка
за день после того, как съест i ягоду и насколько опуститься за ночь.

Формат вывода
В первой строке выходных данных
выведите единственное число — максимальную высоту, которую сможет достичь Петя, если Вася будет его кормить оптимальным
образом. В следующей строке выведите n различных целых чисел от 1 до n — порядок, в котором Вася должен кормить Петю (i
число в строке соответствует номеру ягоды, которую Вася должен дать Пете в i день чтобы Петя смог достичь максимальной
высоты).

*/

public class TaskE extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(r.readLine());
            List<Param> positiveDiff = new ArrayList<>();
            List<Param> negativeDiff = new ArrayList<>();
            Param maxPosMinus = null;
            Param maxNegPlus = null;
            for (int i = 1; i <= n; i++) {
                int[] params = Arrays.stream(r.readLine().split(" ")).filter(el -> el != null && !el.isEmpty()).mapToInt(el -> Integer.parseInt(el)).toArray();
                int diff = params[0] - params[1];
                Param param = new Param(i, params[0], params[1], params[0] - params[1]);
                if (diff >= 0) {
                    positiveDiff.add(param);
                    if (maxPosMinus == null || maxPosMinus.minus < param.minus) {
                        maxPosMinus = param;
                    }
                } else {
                    negativeDiff.add(param);
                    if (maxNegPlus == null || maxNegPlus.plus < param.plus) {
                        maxNegPlus = param;
                    }
                }
            }

            StringBuilder result = new StringBuilder();

            long len = 0;
            long maxLen = 0;
            for (int i = 0; i < positiveDiff.size(); i++) {
                Param param = positiveDiff.get(i);
                if (param.equals(maxPosMinus)) {
                    continue;
                }
                len = len + param.plus;
                maxLen = Math.max(maxLen, len);
                len = len - param.minus;
                result.append(" ").append(param.index);
            }
            if (maxPosMinus != null) {
                maxLen = Math.max(maxLen, len + maxPosMinus.plus);
                len = len + maxPosMinus.plus - maxPosMinus.minus;
                result.append(" ").append(maxPosMinus.index);
            }
            if (maxNegPlus != null) {
                maxLen = Math.max(maxLen, len + maxNegPlus.plus);
                result.append(" ").append(maxNegPlus.index);
            }
            for (int i = 0; i < negativeDiff.size(); i++) {
                Param param = negativeDiff.get(i);
                if (param.equals(maxNegPlus)) {
                    continue;
                }
                result.append(" ").append(param.index);
            }
            System.out.println(maxLen);
            System.out.println(result.toString().strip());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_001() {
        provideConsoleInput("3\n" +
                "1 5\n" +
                "8 2\n" +
                "4 4\n");
        main(new String[0]);
        String expected = "10\n" +
                "2 3 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("2\n" +
                "7 6\n" +
                "7 4\n");
        main(new String[0]);
        String expected = "10\n" +
                "2 1\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_004() {
        provideConsoleInput("7\n" +
                "160714711 449656269\n" +
                "822889311 446755913\n" +
                "135599877 389312924\n" +
                "480845266 448565595\n" +
                "561330066 605997004\n" +
                "61020590 573085537\n" +
                "715477619 181424399");
        main(new String[0]);
        String expected = "1503796355\n" +
                "2 4 7 5 1 3 6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_007() {
        provideConsoleInput("6\n" +
                "822889311 446755913\n" +
                "715477619 181424399\n" +
                "61020590 573085537\n" +
                "480845266 448565595\n" +
                "135599877 389312924\n" +
                "160714711 449656269");
        main(new String[0]);
        String expected = "1391031884\n" +
                "1 2 4 3 5 6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_008() {
        provideConsoleInput("7\n" +
                "160714711 449656269\n" +
                "822889311 446755913\n" +
                "135599877 389312924\n" +
                "448565595 480845266\n" +
                "561330066 605997004\n" +
                "61020590 573085537\n" +
                "715477619 181424399");
        main(new String[0]);
        String expected = "1471516684\n" +
                "2 7 5 1 3 4 6\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f15() throws FileNotFoundException {
        String testNumber = "15";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    record Param(int index, int plus, int minus, int diff) {
    }

}
