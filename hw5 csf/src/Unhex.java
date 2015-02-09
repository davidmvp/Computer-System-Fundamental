
import java.util.Scanner;
/**
 * Unhex program.
 * @author davidmvp23
 *
 */
public final class Unhex {

    private Unhex() {

    }

    /**
     * static integer function.
     * @param bits bits.
     * @param k k.
     * @return the integer.
     */
    public static int ror(int bits, int k) {
        return (bits >>> k) | (bits << (32 - k));
    }
    /**
     * main method.
     * @param args args.
     */
    public static void main(String[] args) {
        //input
        final int f = 32;
        Scanner in = new Scanner(System.in);
        int a0 = 0;
        a0 = in.nextInt();
        String bin = Integer.toBinaryString(a0);
        //.data
        String digits = "0123456789abcdef";
        String output = "";

        while (bin.length() != f) {  // fill 0s i the front
            bin = "0" + bin;

        }

        //LOOP
        final int l = 8;
        final int k = 4;
        for (int i = 0; i < l; i++) {
            String sub = bin.substring(0, k);
            bin = bin.substring(k, bin.length()) + sub;
            int s1 = Integer.parseInt(sub, 2);
            int t3 = s1 & 0xf;
            char t4 = digits.charAt(t3);
            output = output + t4;

        }

        System.out.println("Outpt is " + output);
    }
}
