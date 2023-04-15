package ru.algo.ya;

public class Utils {

    static public int getDigits(String s) {
        return Integer.parseInt(s.replaceAll("[\\D]", ""));
    }
}
