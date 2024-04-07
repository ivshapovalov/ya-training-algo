package algo.c1.hw8;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
https://contest.yandex.ru/contest/28069/problems/J/
Родословная: подсчет уровней

Ограничение времени 	1 секунда
Ограничение памяти 	64Mb
Ввод 	стандартный ввод или input.txt
Вывод 	стандартный вывод или output.txt

В генеалогическом древе у каждого человека, кроме родоначальника, есть ровно один родитель. Каждом элементу дерева
сопоставляется целое неотрицательное число, называемое высотой. У родоначальника высота равна 0, у любого другого
элемента высота на 1 больше, чем у его родителя. Вам дано генеалогическое древо, определите высоту всех его элементов.
Формат ввода

Программа получает на вход число элементов в генеалогическом древе N. Далее следует N-1 строка, задающие родителя для
каждого элемента древа, кроме родоначальника. Каждая строка имеет вид имя_потомка имя_родителя. Формат вывода

Программа должна вывести список всех элементов древа в лексикографическом порядке. После вывода имени каждого элемента
необходимо вывести его высоту.

*/
public class TaskJ extends ContestTask {
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
            dfs(tree, 0);
            humans.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dfs(Tree tree, int level) {
        humans.put(tree.name, level);
        if (tree.children != null) {
            for (Tree child : tree.getChildren()) {
                dfs(child, level + 1);
            }
        }
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
        String expected = "Alexander_I 4\n" +
                "Alexei 1\n" +
                "Anna 1\n" +
                "Elizabeth 1\n" +
                "Nicholaus_I 4\n" +
                "Paul_I 3\n" +
                "Peter_I 0\n" +
                "Peter_II 2\n" +
                "Peter_III 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_02() {
        provideConsoleInput("10\n" +
                "AQHFYP MKFXCLZBT\n" +
                "AYKOTYQ QIUKGHWCDC\n" +
                "IWCGKHMFM WPLHJL\n" +
                "MJVAURUDN QIUKGHWCDC\n" +
                "MKFXCLZBT IWCGKHMFM\n" +
                "PUTRIPYHNQ UQNGAXNP\n" +
                "QIUKGHWCDC WPLHJL\n" +
                "UQNGAXNP WPLHJL\n" +
                "YURTPJNR QIUKGHWCDC\n");
        main(new String[0]);
        String expected = "AQHFYP 3\n" +
                "AYKOTYQ 2\n" +
                "IWCGKHMFM 1\n" +
                "MJVAURUDN 2\n" +
                "MKFXCLZBT 2\n" +
                "PUTRIPYHNQ 2\n" +
                "QIUKGHWCDC 1\n" +
                "UQNGAXNP 1\n" +
                "WPLHJL 0\n" +
                "YURTPJNR 2\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_03() {
        provideConsoleInput("10\n" +
                "BFNRMLH CSZMPFXBZ\n" +
                "CSZMPFXBZ IHWBQDJ\n" +
                "FMVQTU FUXATQUGIG\n" +
                "FUXATQUGIG IRVAVMQKN\n" +
                "GNVIZ IQGIGUJZ\n" +
                "IHWBQDJ LACXYFQHSQ\n" +
                "IQGIGUJZ JMUPNYRQD\n" +
                "IRVAVMQKN GNVIZ\n" +
                "JMUPNYRQD BFNRMLH\n");
        main(new String[0]);
        String expected = "BFNRMLH 3\n" +
                "CSZMPFXBZ 2\n" +
                "FMVQTU 9\n" +
                "FUXATQUGIG 8\n" +
                "GNVIZ 6\n" +
                "IHWBQDJ 1\n" +
                "IQGIGUJZ 5\n" +
                "IRVAVMQKN 7\n" +
                "JMUPNYRQD 4\n" +
                "LACXYFQHSQ 0\n";
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
        }

        public List<Tree> getChildren() {
            return children;
        }

        public void addChild(Tree child) {
            children.add(child);
        }
    }
}
