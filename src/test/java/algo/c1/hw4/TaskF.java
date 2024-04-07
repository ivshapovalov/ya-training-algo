package algo.c1.hw4;

import algo.ContestTask;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
https://contest.yandex.ru/contest/27665/problems/F/
Продажи
*/

public class TaskF extends ContestTask {
    public static void mainMy(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String line = r.readLine();
            Map<String, Map<String, Long>> db = new TreeMap<>();
            while (line != null) {
                String[] input = Arrays.stream(line.split(" ")).filter(el -> !el.isEmpty()).toArray(String[]::new);
                String client = input[0];
                String product = input[1];
                long amount = Long.valueOf(input[2]);

                Map<String, Long> clientPurchases;
                if (db.containsKey(client)) {
                    clientPurchases = db.get(client);
                    if (clientPurchases.containsKey(product)) {
                        long oldAmount = clientPurchases.get(product);
                        amount += oldAmount;
                    }
                } else {
                    clientPurchases = new TreeMap<>();
                }
                clientPurchases.put(product, amount);
                db.put(client, clientPurchases);
                line = r.readLine();
            }

            for (Map.Entry<String, Map<String, Long>> el : db.entrySet()) {
                System.out.println(el.getKey() + ":");
                for (Map.Entry<String, Long> good :
                        el.getValue().entrySet()) {
                    System.out.println(good.getKey() + " " + good.getValue().longValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String line = r.readLine();
            Map<String, Map<String, Long>> db = new TreeMap<>();
            List<String[]> inputs = new ArrayList<>();
            while (line != null) {
                String[] input = Arrays.stream(line.split(" ")).filter(el -> !el.isEmpty()).toArray(String[]::new);
                inputs.add(input);
                line = r.readLine();
            }
            Collections.sort(inputs, new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    return o1[0].compareTo(o2[0]);
                }
            }.thenComparing(new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    return o1[1].compareTo(o2[1]);
                }
            }));
            String prevClient = inputs.get(0)[0];
            String prevProduct = inputs.get(0)[1];
            int amount = Integer.valueOf(inputs.get(0)[2]);
            System.out.print(prevClient + ":\n");
            for (int i = 1; i < inputs.size(); i++) {
                String[] cur = inputs.get(i);
                int newAmount = Integer.valueOf(cur[2]);
                if (cur[0].equals(prevClient) && cur[1].equals(prevProduct)) {
                    newAmount = amount + newAmount;
                } else if (cur[0].equals(prevClient)) {
                    System.out.print(prevProduct + " " + amount + "\n");
                    prevProduct = cur[1];
                } else {
                    System.out.print(prevProduct + " " + amount + "\n");
                    System.out.print(cur[0] + ":\n");
                    prevClient = cur[0];
                    prevProduct = cur[1];
                }
                amount = newAmount;
            }
            System.out.print(prevProduct + " " + amount + "\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_1() {
        provideConsoleInput("Ivanov paper 10\n" +
                "Petrov pens 5\n" +
                "Ivanov marker 3\n" +
                "Ivanov paper 7\n" +
                "Petrov envelope 20\n" +
                "Ivanov envelope 5\n");
        main(new String[0]);
        String expected = "Ivanov:\n" +
                "envelope 5\n" +
                "marker 3\n" +
                "paper 17\n" +
                "Petrov:\n" +
                "envelope 20\n" +
                "pens 5\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_2() {
        provideConsoleInput("Ivanov aaa 1\n" +
                "Petrov aaa 2\n" +
                "Sidorov aaa 3\n" +
                "Ivanov aaa 6\n" +
                "Petrov aaa 7\n" +
                "Sidorov aaa 8\n" +
                "Ivanov bbb 3\n" +
                "Petrov bbb 7\n" +
                "Sidorov aaa 345\n" +
                "Ivanov ccc 45\n" +
                "Petrov ddd 34\n" +
                "Ziborov eee 234\n" +
                "Ivanov aaa 45\n");
        main(new String[0]);
        String expected = "Ivanov:\n" +
                "aaa 52\n" +
                "bbb 3\n" +
                "ccc 45\n" +
                "Petrov:\n" +
                "aaa 9\n" +
                "bbb 7\n" +
                "ddd 34\n" +
                "Sidorov:\n" +
                "aaa 356\n" +
                "Ziborov:\n" +
                "eee 234\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

    @Test
    public void test_3() {
        provideConsoleInput("TKSNUU FKXYPUGQ 855146\n" +
                "TKSNUU FKXYPUGQ 930060\n" +
                "TKSNUU FKXYPUGQ 886973\n" +
                "TKSNUU FKXYPUGQ 59344\n" +
                "TKSNUU FKXYPUGQ 296343\n" +
                "TKSNUU FKXYPUGQ 193166\n" +
                "TKSNUU FKXYPUGQ 211696\n" +
                "TKSNUU FKXYPUGQ 821064\n" +
                "TKSNUU FKXYPUGQ 672846\n" +
                "TKSNUU FKXYPUGQ 820341\n" +
                "TKSNUU FKXYPUGQ 350693\n" +
                "TKSNUU FKXYPUGQ 469538\n" +
                "TKSNUU FKXYPUGQ 849069\n" +
                "TKSNUU FKXYPUGQ 502007\n" +
                "TKSNUU FKXYPUGQ 961595\n" +
                "TKSNUU FKXYPUGQ 747271\n" +
                "TKSNUU FKXYPUGQ 863648\n" +
                "TKSNUU FKXYPUGQ 952069\n" +
                "TKSNUU FKXYPUGQ 286019\n" +
                "TKSNUU FKXYPUGQ 364841\n" +
                "TKSNUU FKXYPUGQ 455930\n" +
                "TKSNUU FKXYPUGQ 100486\n" +
                "TKSNUU FKXYPUGQ 335026\n" +
                "TKSNUU FKXYPUGQ 197672\n" +
                "TKSNUU FKXYPUGQ 217640\n" +
                "TKSNUU FKXYPUGQ 612549\n" +
                "TKSNUU FKXYPUGQ 622501\n" +
                "TKSNUU FKXYPUGQ 96554\n" +
                "TKSNUU FKXYPUGQ 327166\n" +
                "TKSNUU FKXYPUGQ 425399\n" +
                "TKSNUU FKXYPUGQ 362309\n" +
                "TKSNUU FKXYPUGQ 78477\n" +
                "TKSNUU FKXYPUGQ 258916\n" +
                "TKSNUU FKXYPUGQ 297923\n" +
                "TKSNUU FKXYPUGQ 8891\n" +
                "TKSNUU FKXYPUGQ 13639\n" +
                "TKSNUU FKXYPUGQ 77308\n" +
                "TKSNUU FKXYPUGQ 707620\n" +
                "TKSNUU FKXYPUGQ 68205\n" +
                "TKSNUU FKXYPUGQ 256702\n" +
                "TKSNUU FKXYPUGQ 668334\n" +
                "TKSNUU FKXYPUGQ 968673\n" +
                "TKSNUU FKXYPUGQ 138125\n" +
                "TKSNUU FKXYPUGQ 222904\n" +
                "TKSNUU FKXYPUGQ 214091\n" +
                "TKSNUU FKXYPUGQ 500231\n" +
                "TKSNUU FKXYPUGQ 19611\n" +
                "TKSNUU FKXYPUGQ 491343\n" +
                "TKSNUU FKXYPUGQ 404307\n" +
                "TKSNUU FKXYPUGQ 68367\n" +
                "TKSNUU FKXYPUGQ 287107\n" +
                "TKSNUU FKXYPUGQ 794935\n" +
                "TKSNUU FKXYPUGQ 254217\n" +
                "TKSNUU FKXYPUGQ 206370\n" +
                "TKSNUU FKXYPUGQ 202761\n" +
                "TKSNUU FKXYPUGQ 929017\n" +
                "TKSNUU FKXYPUGQ 843359\n" +
                "TKSNUU FKXYPUGQ 955269\n" +
                "TKSNUU FKXYPUGQ 134139\n" +
                "TKSNUU FKXYPUGQ 946168\n" +
                "TKSNUU FKXYPUGQ 967781\n" +
                "TKSNUU FKXYPUGQ 856474\n" +
                "TKSNUU FKXYPUGQ 465070\n" +
                "TKSNUU FKXYPUGQ 580526\n" +
                "TKSNUU FKXYPUGQ 172109\n" +
                "TKSNUU FKXYPUGQ 191703\n" +
                "TKSNUU FKXYPUGQ 207916\n" +
                "TKSNUU FKXYPUGQ 512264\n" +
                "TKSNUU FKXYPUGQ 533081\n" +
                "TKSNUU FKXYPUGQ 577208\n" +
                "TKSNUU FKXYPUGQ 831389\n" +
                "TKSNUU FKXYPUGQ 439158\n" +
                "TKSNUU FKXYPUGQ 565633\n" +
                "TKSNUU FKXYPUGQ 452643\n" +
                "TKSNUU FKXYPUGQ 164426\n" +
                "TKSNUU FKXYPUGQ 540743\n" +
                "TKSNUU FKXYPUGQ 880704\n" +
                "TKSNUU FKXYPUGQ 868529\n" +
                "TKSNUU FKXYPUGQ 240742\n" +
                "TKSNUU FKXYPUGQ 868865\n" +
                "TKSNUU FKXYPUGQ 910442\n" +
                "TKSNUU FKXYPUGQ 146737\n" +
                "TKSNUU FKXYPUGQ 820984\n" +
                "TKSNUU FKXYPUGQ 660948\n" +
                "TKSNUU FKXYPUGQ 957975\n" +
                "TKSNUU FKXYPUGQ 135847\n" +
                "TKSNUU FKXYPUGQ 401865\n" +
                "TKSNUU FKXYPUGQ 982859\n" +
                "TKSNUU FKXYPUGQ 748454\n" +
                "TKSNUU FKXYPUGQ 354734\n" +
                "TKSNUU FKXYPUGQ 525638\n" +
                "TKSNUU FKXYPUGQ 119140\n" +
                "TKSNUU FKXYPUGQ 484816\n" +
                "TKSNUU FKXYPUGQ 616539\n" +
                "TKSNUU FKXYPUGQ 682553\n" +
                "TKSNUU FKXYPUGQ 841541\n" +
                "TKSNUU FKXYPUGQ 713063\n" +
                "TKSNUU FKXYPUGQ 433453\n" +
                "TKSNUU FKXYPUGQ 465340\n" +
                "TKSNUU FKXYPUGQ 985635\n");
        main(new String[0]);
        String expected = "TKSNUU:\n" +
                "FKXYPUGQ 49769497\n";
        assertStringEqualsIgnoreLineSeparators(expected, getOutput());
    }

}
