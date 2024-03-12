package ru.algo.ya.c1.hw8;

import org.junit.jupiter.api.Test;
import ru.algo.ya.ContestTask;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/28069/problems/I/
Родословная: число потомков

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В генеалогическом древе у каждого человека, кроме родоначальника, есть ровно один родитель.

Для каждого элемента дерева определите число всех его потомков (не считая его самого). Формат ввода

Программа получает на вход число элементов в генеалогическом древе N. Далее следует N−1 строка, задающие родителя для
каждого элемента древа, кроме родоначальника. Каждая строка имеет вид имя_потомка имя_родителя. Формат вывода

Выведите список всех элементов в лексикографическом порядке, для каждого элемента выводите количество всех его потомков.

*/
public class TaskI extends ContestTask {
    static Map<String, Integer> humans;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.valueOf(reader.readLine().trim());
            String[][] historyLines = new String[n][2];
            for (int i = 0; i < n - 1; i++) {
                String[] historyLine = reader.readLine().trim().split(" ");
                historyLines[i] = historyLine;
            }
            Tree tree = Tree.createTree(historyLines);
            humans = new HashMap<>();
            dfs(tree);
            humans.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int dfs(Tree tree) {
        int count = 0;
        if (tree.children != null) {
            for (Tree child : tree.getChildren()) {
                count++;
                count += dfs(child);
            }
            humans.put(tree.name, count);
        }
        return count;
    }

    @Test
    public void test_01() {
        provideConsoleInput("9\n" +
                "Alexei Peter_I\n" +
                "Anna Peter_I\n" +
                "Elizabeth Peter_I\n" +
                "Peter_II Alexei\n" +
                "Peter_III Anna\n" +
                "Paul_I Peter_III\n" +
                "Alexander_I Paul_I\n" +
                "Nicholaus_I Paul_I\n");
        main(new String[0]);
        String expected = "Alexander_I 0\n" +
                "Alexei 1\n" +
                "Anna 4\n" +
                "Elizabeth 0\n" +
                "Nicholaus_I 0\n" +
                "Paul_I 2\n" +
                "Peter_I 8\n" +
                "Peter_II 0\n" +
                "Peter_III 3\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_f17() throws FileNotFoundException {
        String testNumber = "017";
        String testFilePath = getResourceFile(this.getClass(), testNumber + ".txt");
        String answerFilePath = getResourceFile(this.getClass(), testNumber + ".a.txt");
        testIn = new ByteArrayInputStream(getFileContent(testFilePath).getBytes());
        String expected = getFileContent(answerFilePath);
        System.setIn(testIn);
        main(new String[0]);
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    static class Tree {
        String name;
        List<Tree> children = new ArrayList<>();

        public Tree(String name) {
            this.name = name;
        }

        public Tree() {
        }

        public static Tree createTree(String[][] historyLines) {
            Map<String, Tree> history = new HashMap<>();
            for (int i = 0; i < historyLines.length - 1; i++) {
                String childName = historyLines[i][0];
                String parentName = historyLines[i][1];
                Tree parent = history.getOrDefault(parentName, new Tree(parentName));
                Tree child = history.getOrDefault(childName, new Tree(childName));
                parent.addChild(child);
                history.put(parentName, parent);
                history.put(childName, child);
            }
            List<Tree> humans = new ArrayList<>(history.values());
            history.values().stream().flatMap(el -> el.getChildren().stream()).distinct().collect(Collectors.toList());
            humans.removeAll(history.values().stream().flatMap(el -> el.getChildren().stream()).distinct().collect(Collectors.toList()));
            return humans.get(0);
//            return history.get("HVIDIAEE");
        }

        public List<Tree> getChildren() {
            return children;
        }

        public void addChild(Tree child) {
            children.add(child);
        }
    }
}
