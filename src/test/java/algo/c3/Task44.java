package algo.c3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
https://contest.yandex.ru/contest/46304/problems/D/
Круглая кровать

Ограничение времени 	2 секунды
Ограничение памяти 	    256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Вася изучил алгоритмы, устроился на интересную и высокооплачиваемую работу, построил виллу с зимним садом, в котором
растут пальмы и решил исполнить свою давнюю мечту — купить огромную круглую кровать.

Чтобы внести кровать в спальню необходимо пронести ее через зимний сад. Стены зимнего сада параллельны оси Y. Вход в
зимний сад находится снизу, а спальня — сверху. Все пальмы в саду при виде сверху представляют собой круг и имеют
одинаковых радиус R.

Определите максимальный диаметр круглой кровати, которую можно пронести через зимний сад в горизонтальном положении.

https://contest.yandex.ru/testsys/statement-image?imageId=c586116cfedf17c07b761a268fb2eb5dc030df935b7715e0edea126ae1e77953


Формат ввода
В первой строке заданы два целых числа XL и XR (−106≤XL≤XR≤106) — x-координаты левой и правой стен зимнего
сада.

Во второй строке задано целое число R (1≤R≤106) — радиус пальм.

В третей задано целое число N (1≤N≤200) — количество пальм в саду.

В следующих N строках задано по два целых числа x и y (−106≤x,y≤106) — координаты центра пальмы.

Формат вывода
 Выведите максимальный диаметр кровати. Ответ должен отличаться от правильного не более чем на 10−3 (т.е. на 0.001).

Если нельзя пронести никакую кровать, выведите 0.


*/
public class Task44 extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int k = Integer.valueOf(reader.readLine().trim());
            int[] point = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_1() {
        provideConsoleInput("push 1\n" +
                "front\n" +
                "exit\n");
        main(new String[0]);
        String expected = "ok \n" +
                "1 \n" +
                "bye \n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

//        @Test
//    public void test_f1() throws FileNotFoundException {
//        testIn = new ByteArrayInputStream(
//                getFileContent(class, "/src/main/java/algo.c3/t11/11.txt").getBytes());
//        String expected = getFileContent(class, "/src/main/java/algo.c3/t10/09.a.txt");
//        System.setIn(testIn);
//        main(new String[0]);
//         assertStringEqualsIgnoreLineSeparators(expected, getOutput());
//    }

}
