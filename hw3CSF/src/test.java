import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;




public class test {
	 public static void main(String[] args) throws Exception {
	FileInputStream in = null;
    Scanner input = new Scanner(System.in);
    
   
    File inputFile = new File("ok.txt");
    in = new FileInputStream(inputFile);
    FileWriter fstream = new FileWriter("output.txt");
    BufferedWriter out = new BufferedWriter(fstream);
    boolean typeOfinput = true;
    BufferedReader bin = new BufferedReader(new InputStreamReader(in) );
    String line = bin.readLine();
    line = bin.readLine();
    Scanner s = new Scanner(line);
 String f = "23 kl";
 int e = Integer.parseInt(f);
}
}