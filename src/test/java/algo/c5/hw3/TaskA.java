package algo.c5.hw3;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/59541/problems/A/
Плейлисты

Ограничение времени 	1.5 секунд
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

Костя успешно прошел собеседование и попал на стажировку в отдел разработки сервиса «Музыка».

Конкретно ему поручили такое задание — научиться подбирать плейлист для группы друзей, родственников или коллег. При
этом нужно подобрать такой плейлист, в который входят исключительно нравящиеся всем членам группы песни.

Костя очень хотел выполнить это задание быстро и качественно, но у него не получается. Помогите ему написать программу,
которая составляет плейлист для группы людей.

Формат ввода
В первой строке расположено одно натуральное число n(1≤n≤2⋅105), где n – количество человек в группе.

В следующих 2⋅n строках идет описание любимых плейлистов членов группы. По 2 строки на каждого участника.

В первой из этих 2-х строк расположено число ki — количество любимых треков i-го члена группы. В следующей строке
расположено ki строк через пробел — названия любимых треков i-го участника группы.

Каждый трек в плейлисте задан в виде строки, все строки уникальны, сумма длин строк не превосходит 2⋅106. Строки
содержат большие и маленькие латинские буквы и цифры.

Формат вывода
Выведите количество, а затем сам список песен через пробел — список треков, которые нравятся каждому
участнику группы. Ответ необходимо отсортировать в лексикографическом порядке!

*/

public class TaskA extends ContestTask {

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(r.readLine());
            Set<String> playlist = new HashSet<>();
            for (int i = 0; i < n; i++) {
                int k = Integer.parseInt(r.readLine());
                Set<String> tracks = Arrays.stream(r.readLine().split(" ")).collect(Collectors.toSet());
                if (playlist.size() == 0) {
                    playlist.addAll(tracks);
                } else {
                    playlist.retainAll(tracks);
                }
            }
            System.out.println(playlist.size());
            System.out.println(playlist.stream().sorted().collect(Collectors.joining(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_001() {
        provideConsoleInput("1\n" +
                "2\n" +
                "GoGetIt Life\n");
        main(new String[0]);
        String expected = "2\n" +
                "GoGetIt Life\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_002() {
        provideConsoleInput("2\n" +
                "2\n" +
                "Love Life\n" +
                "2\n" +
                "Life GoodDay\n");
        main(new String[0]);
        String expected = "1\n" +
                "Life\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }
}
