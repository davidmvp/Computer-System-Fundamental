import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Assembler {
	   
    ArrayList<OperationCode> allOperation;
    
            
      public Assembler() {
      	allOperation = new ArrayList<OperationCode>();
      	OperationCode op = new OperationCode();
      	op.setOperation("LDA");
      	op.setCode("0001");
      	allOperation.add(op);
    	OperationCode op1 = new OperationCode();
      	op1.setOperation("LDI");
      	op1.setCode("0010");
      	allOperation.add(op1);
    	OperationCode op2 = new OperationCode();
      	op2.setOperation("STA");
      	op2.setCode("0011");
      	allOperation.add(op2);
    	OperationCode op3 = new OperationCode();
      	op3.setOperation("STI");
      	op3.setCode("0100");
      	allOperation.add(op3);
    	OperationCode op4 = new OperationCode();
      	op4.setOperation("ADD");
      	op4.setCode("0101");
      	allOperation.add(op4);
    	OperationCode op5 = new OperationCode();
      	op5.setOperation("SUB");
      	op5.setCode("0110");
      	allOperation.add(op5);
    	OperationCode op6 = new OperationCode();
      	op6.setOperation("JMP");
      	op6.setCode("0111");
      	allOperation.add(op6);
    	OperationCode op7 = new OperationCode();
      	op7.setOperation("JMZ");
      	op7.setCode("1000");
      	allOperation.add(op7);
      	System.out.println(allOperation);
      }
      
        
    public ArrayList<OperationCode> returnOp() {
    	return allOperation;
    }
    
    
    
    
    
    public class OperationCode{
    	String operation = "";
    	String code = "";
    
    public void setOperation(String o) {
    	operation = o;
    }
    public void setCode(String c) {
    	code = c;
    }
    public String getOperation() {
    	return operation;
    }
    public String getCode() {
    	return code;
    }
    public String toString() {
    	return operation + " " + code;
    }
    	
    }
    public static void main(String[] args) throws Exception {
    	Assembler as = new Assembler();
        FileInputStream in = null;
        Scanner input = new Scanner(System.in);
        ArrayList<OperationCode> allOp = new ArrayList<OperationCode>();
        allOp = as.returnOp();
        File inputFile = new File("hello.txt");
        in = new FileInputStream(inputFile);
        FileWriter fstream = new FileWriter("output.txt");
        BufferedWriter out = new BufferedWriter(fstream);
        boolean typeOfinput = true;
      
      
        BufferedReader bin = new BufferedReader(new InputStreamReader(in) );
        String line = "";
        String code = "";
        int currLine = 0;
        //int lineNumber = 0;
        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>(); 
        while (true) {
        	
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
        	str = s.next();
        	ar.add(str);
        	if (s.hasNext()) {
        		ar.add(s.next());
        	}
        	ar.add("" + currLine);
        	arr.add(ar);
        	
        }
       
        	System.out.println(arr);
        
        boolean type = true;
        if (arr.get(0).get(0).contains(":")) {
        	type = false;
        }
        
        if (type == true) {
        	for (int i = 0; i < arr.size() ; i++) {
        		 code = "";
        		
        		for (int j = 0; j < allOp.size() ; j++) {
        			
                	if (arr.get(i).get(0).contains(allOp.get(j).getOperation())) {
                		code = allOp.get(j).getCode();
                		
                	}
                }
                if (code.length() == 0) {
                	code = "0000";
                }
                int address = Integer.parseInt(arr.get(i).get(1));
                if (address > 15) {
                	System.out.println("Number is too big! Error at line " + arr.get(i).get(2));
                	return;
                }
                
               
               
                String addre = Integer.toBinaryString(address) + "";
                 while (addre.length() < 4) {
                	addre = "0" + addre;
                }
               
                 String str = code + "" + addre;
                 int inte = Integer.parseInt(str);
                 long lon = (long) inte;
                 //out.write((byte)lon & 0xff);
                 out.write(str);

        		
        	}
        }
        else {
        	
        	for (int i = 0; i < arr.size() ; i++) {
        		for (int j = 0; j < arr.size() ; j++) {
        			if (arr.get(i).size() == 4 && arr.get(j).size() == 4 && arr.get(i).get(0).equals(arr.get(j).get(0)) && j!= i) {
        				
        					
        					System.out.println("A label is define twice on line " + arr.get(i).get(3) + " and " + arr.get(j).get(3));
        			}
        		}
        	}
        	for (int i = 0; i< arr.size() ; i++) {
        		code = "";
        		if (arr.get(i).size() == 4) {
        			
        			for (int j = 0; j < allOp.size() ; j++) {
        				System.out.println(arr.get(i).get(1));
                		System.out.println(allOp.get(j).getOperation());
                    	if (arr.get(i).get(1).contains(allOp.get(j).getOperation())) {
                    		System.out.println("ww");
                    		code = allOp.get(j).getCode();
                    		
                    	}
                    
                    }
        			System.out.println(arr.get(i).get(1));
        			System.out.println("code is" + code);
        			if (code.length() == 0) {
        				code = "0000";
                    	
                    }
        			System.out.println("code is" + code);
                    int address = -2;
                   
                    
                    for (int j = 0; j < arr.size() ; j++) {
                    	if (arr.get(j).get(0).contains(arr.get(i).get(2))) {
                    		address = j;
                    	}
                    }
                    if ( address == -2) {
                    	if (arr.get(i).get(2).matches(".*[0-9].*"))
                    	address = Integer.parseInt(arr.get(i).get(2));
                    	else {
                    		System.out.println("This symbol has never been define at line " + arr.get(i).get(3));
                    		return;
                    	}
                    	
                    }
                    if (address > 255) {
                    	System.out.println("Number is too big! Error at line " + arr.get(i).get(3));
                    	return;
                    }
                    String addre = Integer.toBinaryString(address) + "";
                     while (addre.length() < 4) {
                    	addre = "0" + addre;
                    }
                   
                    String str = code + "" + addre;
                    int inte = Integer.parseInt(str);
                    long lon = (long) inte;
                   // out.write((byte)lon & 0xff);
                    out.write(str);
        			
        		}
        		else {
        			
        			for (int j = 0; j < allOp.size() ; j++) {
            			
                    	if (arr.get(i).get(0).contains(allOp.get(j).getOperation())) {
                    		code = allOp.get(j).getCode();
                    		
                    	}
                    }
        			
        			if (code.length() == 0) {
                    	code = arr.get(i).get(1);
                    }
                    int address = -2;
                   
                    
                    for (int j = 0; j < arr.size() ; j++) {
                    	if (arr.get(j).get(0).contains(arr.get(i).get(1))) {
                    		address = j;
                    	}
                    }
                    System.out.println(arr.get(i).get(0));
                    System.out.println("code is " +code);
                    if ( address == -2) {
                    	if (arr.get(i).get(1).matches(".*[0-9].*"))
                        	address = Integer.parseInt(arr.get(i).get(1));
                        	else {
                        		System.out.println("This symbol has never been define at line " + arr.get(i).get(2));
                        		return;
                        	}
                    }
                    if (address > 255) {
                    	System.out.println("Number is too big! Error at line " + arr.get(i).get(2));
                    	return;
                    }
                    String addre = Integer.toBinaryString(address) + "";
                     while (addre.length() < 4) {
                    	addre = "0" + addre;
                    }
                   
                    String str = code + "" + addre;
                    int inte = Integer.parseInt(str);
                    long lon = (long) inte;
                    //out.write((byte)lon & 0xff);
        			out.write(str);
        		}
        		
        		
        		
        	}
        	
        	
        	
        }
        
        out.close();
        }
    
      
    
}
