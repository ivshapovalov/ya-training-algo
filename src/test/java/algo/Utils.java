package algo;

public class Utils {

    static public int getDigits(String s) {
        return Integer.parseInt(s.replaceAll("[\\D]", ""));
    }
}
