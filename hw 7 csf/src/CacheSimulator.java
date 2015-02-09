import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CacheSimulator {

    private CacheSimulator() {
    }
    
    private static int numSets;
    private static int numBlocks;
    private static int numBytes;
    private static int writeAlloc;
    private static int writeThrough;
    private static int eviction;
    private static String traceFile;
    
	public static void main(String[] args) throws FileNotFoundException, IndexOutOfBoundsException {
       int le = args.length;
		numSets = Integer.parseInt(args[0]);
		//check correct # arguments
		
		 
		if(Math.log(numSets)/Math.log(2) != (int)(Math.log(numSets)/(Math.log(2))))
		{
			throw new IndexOutOfBoundsException(numSets + " is not a power of 2");
		}
		
		numBlocks = Integer.parseInt(args[1]);
		if(Math.log(numBlocks)/Math.log(2) != (int)(Math.log(numBlocks)/(Math.log(2))))
		{
			throw new IndexOutOfBoundsException(numBlocks + " is not a power of 2");
		}
        
		numBytes = Integer.parseInt(args[2]);
		if(Math.log(numBytes)/Math.log(2) != (int)(Math.log(numBytes)/(Math.log(2))) || numBytes < 4)
		{
			throw new IndexOutOfBoundsException(numBytes + " is not a power of 2 or is less than 4");
		}
		
		writeAlloc = Integer.parseInt(args[3]);
		if ((writeAlloc != 0) && (writeAlloc !=1)) {
			
			throw new IndexOutOfBoundsException(writeAlloc + " is not 1 or 0");
		}
		
		writeThrough = Integer.parseInt(args[4]);
		if ((writeThrough != 0) && (writeThrough !=1)) {
			
			throw new IndexOutOfBoundsException(writeThrough + " is not 1 or 0");
		}
		
		eviction = Integer.parseInt(args[5]);
		if ((eviction != 0) && (eviction !=1)) {
			
			throw new IndexOutOfBoundsException(eviction + " is not 1 or 0");
		}
		traceFile = args[6];
		
		
		try{
        Scanner sc = new Scanner(new File(traceFile));
        String currLine;
      
        
        while(sc.hasNextLine()) {
        	currLine = sc.nextLine();
        	String[] words = currLine.split(" ");
        	System.out.println(words[0]);
        	System.out.println(words[1]);
        }
		} catch(FileNotFoundException e) {
			System.err.println(traceFile + " is not a valid file");
			System.exit(0);
		}


	}
}
