package ru.algo.ya.c3;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


/*
https://contest.yandex.ru/contest/46304/problems/B/
Эффективный менеджмент

Ограничение времени 	2 секунды
Ограничение памяти 	256Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В компании принята следующая практика: новая задача поручается последнему нанятому сотрудинку. Задача номер i
характеризуется временем начала ai и временем Ti, которое требуется для ее выполнения. Сотрудник может работать
одновременно только над одной задачей. Закончив выполнять задачу, сотрудник сразу же может приступить к выполнению
другой задачи. Если последний нанятый сотрудник не может выполнить задачу из-за пересечения с уже порученными, то
компания нанимает нового сотрудника и поручает задачу ему.

Эффективный Менеджер выделил N задач на следующий период продолжительностью W. Задачи могут поручаться в любом порядке.
Помогите Эффективному Менеджеру переупорядочить поручение задач таким образом, чтобы количество нанятых сотрудников было
минимально.

На рисунке ниже приведен пример выполнения трех задач двумя сотрудниками (рисунок соответствует примеру).
https://contest.yandex.ru/testsys/statement-image?imageId=4ef4f54c1a5ed6c526d5d4704dd3367c67508f9555413de96cd06e98bb4af80e

Формат ввода
В первой строке входного файла записаны числа N (1 ≤ N ≤ 100000) и W (1 ≤ W ≤ 109).
В следующих N строках записаны пары чисел ai и Ti (1 ≤ ai ≤ W – Ti + 1).

Формат вывода
Выведите минимальное количество сотрудников, которые будут наняты компанией.

Затем выведите переупорядоченную последовательность поручения задач, приводящую к найму наименьшего количества
сотрудников. Задачи нумеруются натуральными числами от 1 до N в том порядке, в котором они указаны во входных данных.
Если возможных вариантов несколько, выведите любой из них.

Примечания

Задачи будут поручаться в порядке 3, 1, 2. Для выполнения задачи 3 будет нанят первый сотрудник, ему также будет
поручена задача 1, т.к. она не пересекается по времени с задачей 3. Для выполнения задачи 2 будет нанят новый сотрудник,
т.к. она пересекается с задачами 1 и 3 и не может быть поручена первому сотруднику. Существуют и другие варианты
переупорядочивания поручения задач.

*/
public class Task42 extends ContestTask {

    public static void mainMy(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int w = input[1];
            Map<Integer, List<int[]>> events = new LinkedHashMap<>();
            for (int taskIndex = 1; taskIndex <= n; taskIndex++) {
                int[] task = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                List<int[]> prevStart = events.getOrDefault(task[0], new ArrayList<>());
                prevStart.add(new int[]{taskIndex, +1});
                events.put(task[0], prevStart);
                List<int[]> prevStop = events.getOrDefault(task[0] + task[1], new ArrayList<>());
                prevStop.add(new int[]{taskIndex, -1});
                events.put(task[0] + task[1], prevStop);
            }
            int maxOnline = 0;
            Map<Integer, Integer> taskWorkers = new HashMap<>();
            Queue<Integer> queue = new LinkedList<>();
            Map<Integer, List<Integer>> workHistory = new HashMap<>();
            for (Map.Entry<Integer, List<int[]>> date :
                    events.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList())) {
                List<int[]> oneTimeEvents = date.getValue();
                Collections.sort(oneTimeEvents, Comparator.comparingInt(o -> o[1]));
                for (int[] oneTimeEvent : oneTimeEvents) {
                    int taskIndex = oneTimeEvent[0];
                    int taskStatus = oneTimeEvent[1];
                    if (taskStatus == 1) {
                        int workerIndex;
                        if (queue.isEmpty()) {
                            workerIndex = workHistory.size() + 1;
                        } else {
                            workerIndex = queue.poll();
                        }
                        List<Integer> prevWork = workHistory.getOrDefault(workerIndex, new ArrayList<>());
                        prevWork.add(taskIndex);
                        workHistory.put(workerIndex, prevWork);

                        taskWorkers.put(taskIndex, workerIndex);
                    } else {
                        int workerIndex = taskWorkers.get(taskIndex);
                        taskWorkers.remove(taskIndex);
                        queue.offer(workerIndex);
                    }
                }
                maxOnline = Math.max(maxOnline, taskWorkers.size());
            }
            System.out.print(maxOnline + "\n");
            String seq = workHistory.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .flatMap(el -> el.getValue().stream())
                    .map(el -> String.valueOf(el)).collect(Collectors.joining(" "));
            System.out.print(seq + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().trim().split(" "))
                    .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
            int n = input[0];
            int w = input[1];
            Map<Integer, List<int[]>> events = new LinkedHashMap<>();
            for (int taskIndex = 1; taskIndex <= n; taskIndex++) {
                int[] task = Arrays.stream(reader.readLine().trim().split(" "))
                        .mapToInt(el -> Integer.valueOf(el).intValue()).toArray();
                List<int[]> prevStart = events.getOrDefault(task[0], new ArrayList<>());
                prevStart.add(new int[]{taskIndex, +1});
                events.put(task[0], prevStart);
                List<int[]> prevStop = events.getOrDefault(task[0] + task[1], new ArrayList<>());
                prevStop.add(new int[]{taskIndex, -1});
                events.put(task[0] + task[1], prevStop);
            }
            int maxOnline = 0;
            Map<Integer, Integer> taskWorkers = new HashMap<>();
            Queue<Integer> queue = new LinkedList<>();
            Map<Integer, List<Integer>> workHistory = new HashMap<>();
            for (Map.Entry<Integer, List<int[]>> date :
                    events.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList())) {
                List<int[]> oneTimeEvents = date.getValue();
                Collections.sort(oneTimeEvents, Comparator.comparingInt(o -> o[1]));
                for (int[] oneTimeEvent : oneTimeEvents) {
                    int taskIndex = oneTimeEvent[0];
                    int taskStatus = oneTimeEvent[1];
                    if (taskStatus == 1) {
                        int workerIndex;
                        if (queue.isEmpty()) {
                            workerIndex = workHistory.size() + 1;
                        } else {
                            workerIndex = queue.poll();
                        }
                        List<Integer> prevWork = workHistory.getOrDefault(workerIndex, new ArrayList<>());
                        prevWork.add(taskIndex);
                        workHistory.put(workerIndex, prevWork);

                        taskWorkers.put(taskIndex, workerIndex);
                    } else {
                        int workerIndex = taskWorkers.get(taskIndex);
                        taskWorkers.remove(taskIndex);
                        queue.offer(workerIndex);
                    }
                }
                maxOnline = Math.max(maxOnline, taskWorkers.size());
            }
            System.out.println(maxOnline);
            String seq = workHistory.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .flatMap(el -> el.getValue().stream())
                    .map(el -> String.valueOf(el)).collect(Collectors.joining(" "));
            System.out.println(seq);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_1() {
        provideConsoleInput("3 4\n" +
                "3 2\n" +
                "1 2\n" +
                "2 2\n"
        );
        main(new String[0]);
        String expected = "2\n" +
                "2 1 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f25() throws FileNotFoundException {
        String testNumber = "25";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
