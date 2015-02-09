package hw31;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Assembler for hw3.
 * @author davidmvp23
 *
 */
public class SAS {


     /*
     * Keep track of all the operations.
     */
    ArrayList<OperationCode> allOperation;
    ArrayList<ArrayList<String>> arr;
      /**
       * Constructor, add all the codes.
       */
    public SAS() {
        this.allOperation = new ArrayList<OperationCode>();
        OperationCode op = new OperationCode();
        op.setOperation("LDA");
        op.setCode("0001");
        this.allOperation.add(op);
        OperationCode op1 = new OperationCode();
        op1.setOperation("LDI");
        op1.setCode("0010");
        this.allOperation.add(op1);
        OperationCode op2 = new OperationCode();
        op2.setOperation("STA");
        op2.setCode("0011");
        this.allOperation.add(op2);
        OperationCode op3 = new OperationCode();
        op3.setOperation("STI");
        op3.setCode("0100");
        this.allOperation.add(op3);
        OperationCode op4 = new OperationCode();
        op4.setOperation("ADD");
        op4.setCode("0101");
        this.allOperation.add(op4);
        OperationCode op5 = new OperationCode();
        op5.setOperation("SUB");
        op5.setCode("0110");
        this.allOperation.add(op5);
        OperationCode op6 = new OperationCode();
        op6.setOperation("JMP");
        op6.setCode("0111");
        this.allOperation.add(op6);
        OperationCode op7 = new OperationCode();
        op7.setOperation("JMZ");
        op7.setCode("1000");
        this.allOperation.add(op7);
        this.arr = new ArrayList<ArrayList<String>>();
    }
    /**
     * Return all the operation codes.
     * @return ArrayList of all operations.
     */
    public ArrayList<OperationCode> returnOp() {
        return this.allOperation;
    }
    /**
     * This function is to read file from input file.
     * @return ArrayList with all the information in the input file.
     * @throws IOException if the file exists.
     */
    public ArrayList<ArrayList<String>> readFile() throws IOException {
        FileInputStream in = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type in the name of the input file");
        String inputName = sc.nextLine();
        File inputFile = new File(inputName);
        in = new FileInputStream(inputFile);
        BufferedReader bin = new BufferedReader(new InputStreamReader(in));
        String line = "";
        int currLine = 0;
        while (true) {

             //Here I read the entire file, and skip # comments.
            line = bin.readLine();
            currLine++;
            if (line == null) {
                break;
            }
            if (line.length() == 0) {
                continue;
            }
            Scanner s = new Scanner(line);
            String str = s.next();
            if (str.contains("#") || str.length() == 0) {
                continue;
            }
            ArrayList<String> ar = new ArrayList<String>();
            ar.add(str);
            while (s.hasNext()) {
                String ne = s.next();
                if (ne.contains("#")) {
                    break;
                }
                ar.add(ne);
            }
            ar.add("" + currLine);
            this.arr.add(ar);
        }
        return this.arr;

    }
    /**
     * Process the information and return the output strings.
     * @param i Row i for ArrayList.
     * @return An integer to tell programs to stop or not.
     * @throws IOException if there is an IOE.
     */
    public int processOutput(int i) throws IOException {
        String instruction = "0";
        final int v = 15;
        final int v1 = -10;
        final int v2 = 16;
        for (int j = 0; j < this.allOperation.size(); j++) {
            if (this.arr.get(i).get(0).
            contains(this.allOperation.get(j).getOperation())) {
                instruction = this.allOperation.get(j).getCode();
            }
        }
        int address = Integer.parseInt(this.arr.get(i).get(1));
        if (address > v) {
            System.out.println("Number is too big! Error at line "
                + this.arr.get(i).get(this.arr.get(i).size() - 1));
            return v1;
        }
        int ad1 = Integer.parseInt(instruction, 2) * v2;
        int ad3 = ad1 + address;
        return ad3;
    }
    /**
     * Process the information and return the output strings.
     * @param i Row i for ArrayList.
     * @return An integer to tell programs to stop or not.
     * @throws IOException if there is an IOE.
     */
    public int processOutput2(int i,boolean bool) throws IOException {
        final int v = -10;
        final int v1 = 16;
        final int v2 = 255;
        final int v3 = 4;
        int bo = 0;
        String instruction = "";
        if (bool == false) {
            bo = 1;
        }
        for (int j = 0; j < this.allOperation.size(); j++) {
            String st = this.arr.get(i).get(1 - bo);
            if (st.equals((this.allOperation.get(j).getOperation()))) {
             //Get instruction
                instruction = this.allOperation.get(j).getCode();
            }
        }
        if (instruction.length() == 0) {
            instruction = "0000";
        }

        int address = -2;
        for (int j = 0; j < this.arr.size(); j++) {
            String sj = this.arr.get(j).get(0);
            sj = sj.substring(0, sj.length() - 1);

            if (sj.equals((this.arr.get(i).get(2 - bo)))) {
                address = j;
            }
        }

        if  (address == -2) {

            if (this.arr.get(i).get(2 - bo).matches(".*[0-9].*")) {
                if (this.arr.get(i).get(2 - bo).length() > v3) {
                    System.out.println("The number is too big at line "
                             + this.arr.get(i)
                            .get(this.arr.get(i).size() - 1));
                    return v;
                }
                address = Integer.parseInt(this.arr.get(i).get(2 - bo));
            } else {
                System.out.println("This symbol has never been define at line "
                    + this.arr.get(i).get(this.arr.get(i).size() - 1));
                return v;
            }

        }
        if (address > v2) {
            System.out.println("Number is too big! Error at line "
                + this.arr.get(i).get(this.arr.get(i).size() - 1));
            return v;
        }
        int ad1 = Integer.parseInt(instruction, 2) * v1;
        int ad3 = ad1 + address;
        return ad3;

    }

    /**
     * OperationCode class.
     * @author davidmvp23
     *
     */
    public class OperationCode {
        String operation = "";
        String code = "";
        /**
         * Set operation to o.
         * @param o set String to o.
         */
        public void setOperation(String o) {
            this.operation = o;
        }
        /**
         * Set Code to c.
         * @param c c is the string.
         */
        public void setCode(String c) {
            this.code = c;
        }
        /**
         * Get operation.
         * @return operation.
         */
        public String getOperation() {
            return this.operation;
        }
        /**
         * Get Code.
         * @return code.
         */
        public String getCode() {
            return this.code;
        }
        @Override
        public String toString() {
            return this.operation + " " + this.code;
        }

    }


    /**
     * Static Main program.
     * @param args args.
     * @throws Exception Check exceptions if any.
     */
    public static void main(String[] args) throws Exception {
        SAS as = new SAS();
        FileWriter fstream = new FileWriter("loop.scram");
        BufferedWriter out = new BufferedWriter(fstream);
        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
        arr = as.readFile();
        final int v = -10;
        final int v1 = 4;
        // Check what type of file is it
        boolean type = true;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).get(0).contains(":")) {
                type = false;
            }
        }
        // If the file type does not have labels.
        if (type == true) {
            for (int i = 0; i < arr.size(); i++) {
                int results = as.processOutput(i);
                if (results == v) {
                    return;
                }
                long result = results;
                out.write((byte) result);
            }
        } else {
            //The file contains labels.
            for (int i = 0; i < arr.size(); i++) {
                for (int j = 0; j < arr.size(); j++) {
                    if (arr.get(i).get(0).contains(":")
                            && arr.get(j).get(0).contains(":")
                            && arr.get(i).get(0).equals(arr.get(j).get(0))
                            && j != i) {
                        System.out.println("A label is define twice on line "
                            + arr.get(j).get(arr.get(j).size() - 1));
                        return;
                    }
                }
            }
            for (int i = 0; i < arr.size(); i++) {

                if (arr.get(i).size() == v1) {
                    int results = as.processOutput2(i, true);
                    if (results == v) {
                        return;
                    }
                    long result = results;
                    out.write((byte) result);
                } else {
                    //If the line does not contain a label.

                    int results = as.processOutput2(i, false);
                    if (results == v) {
                        return;
                    }
                    long result = results;
                    out.write((byte) result);
                }
            }
        }
        out.close();
    }

}


