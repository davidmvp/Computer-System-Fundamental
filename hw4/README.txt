Da Lu
dlu6@jhu.edu
10/2/2013


Problem 1 FIX the SCRAM:
1. See diagram 1.

2.Micro Programs:

Fetch cycle:
t0: MAR <- PC
	x10 = 00, x4 = 1, x5 = 1
t1: MBR <- MEM, PC <- PC + 1
	x7 = 0, x2 = 1; x13 = 1
t2: IR <- MBR
 
LDA:
q1t3: MAR <- IR(O)
	x10 = 01, x4 = 1, x5 = 1
q1t4: MBR <- MEM
	x7 = 0, x2 = 1
q1t5: AC <- MBR
	x11 = 1, x12 = 1

LDI:
q2t3: MAR <- IR(O)
	x10 = 01, x4 = 1, x5 = 1
q2t4: MBR <- MEM
	x7 = 0, x2 = 1
q2t5: MAR <- MBR
	x10 = 10, x4 = 1, x5 = 1
q2t6: MBR <- MEM
	x7 = 0, x2 = 1
q2t7: AC <- MBR
	x11 = 1, x12 = 1

STA:
q3t6: MAR <- IR(O)
	x10 = 01, x4 = 1, x5 = 1
q3t8: MBR <- AC
	x7 = 1, x2 = 1
q3t9: MEM <- MBR
	x10 = 10, x4 = 1, x5 = 0

STI:
q4t3: MAR <- IR(O)
	x10 = 01, x4 = 1, x5 = 1
q4t4: MBR <- MEM
	x7 = 0, x2 = 1
q4t5: MAR <- MBR
	x10 = 10, x4 = 1, x5 = 1
q4t6: MBR <- AC
	x7 = 1, x2 = 1
q4t7: MEM <- MBR
	x10 = 10, x4 = 1, x5 = 0

ADD:
q5t3: MAR <- IR(O)
	x10 = 01, x4 = 1
q5t4: MBR <- MEM
	x7 = 0, x2 = 1, x5 = 1
q5t5: AD <- MBR
	x8 = 0, x9 = 1
q5t6: RS <- AC + AD
	x8 = 1, x14 = 1
q5t7: AC <- RS
	x11 = 1, x12 = 1

SUB:
q6t3: MAR <- IR(O)
	x10 = 01, x4 = 1
q6t4: MBR <- MEM
	x7 = 0, x2 = 1, x5 = 1
q6t5: AD <- MBR
	x8 = 0, x9 = 1
q6t6: RS <- AC + AD
	x8 = 1, x14 = 0
q6t7: AC <- RS
	x11 = 1, x12 = 1

JMP:
q7t3: PC <- IR
	x3 = 1, x15 = 1

JMZ:
q8t3: PC <- IR
	x3 = 1, x15 = 0

3.
Explanations: I have found 8 problems with this SCRAM.

1. ADD PC <- IR.    We need a circuit that connect PC and IR because it allows the desired addresses to be transferred to the PC for the next cycle.  In other words, we need it to execute JMA and JMP micro programs.  

2. Delete PC <-AC  We do not need a circuit that goes from AC to PC.  AC stores addresses, PC does not.  

3. Delete AC <-PC  Like 2, PC and AC  store different types of information, so they do not need to be connected. 

4. ADD MUX <- MBR:  Indirect micro programs like LdI and STI, addresses needed to be removed from MEM to MRB to MAR.  There has to be some signal that goes from MBR to MUX.

5.  ADD SELECT signal to ALU:  The ALU needs a select signal that tells it to either add or subtract.    It already has signals from the AC and the MUX.  So we need one more input, a select signal that determines if ALU needs to add or subtract.

6.  Add PC <- MUX: It is needed to correctly execute JMZ micro program. An if block was added after AC and a MUX was added before PC.  If block takes an input from AC and its output goes to MUX, which is connected to PC.


7.   Add RS <- ALU: After correction 5,  I realize since the ALU does not have a select signal, we do not know what is inside of ALU.  We need the RS to store the desired arithmetic sum.

8.  Delete AC <- IR:  IR and AC do not need to be connected.  IR contains instructions and addresses while AC contains data.




Problem 2:
In my submitted files, I will have a SAS.class.  To run it, we need to type in java SAS, and it will ask us for an input file.  Type in the name of the input file under same directory.  I have loop.s and loop.txt as input files.  If there are errors in the code, my program will stop and tell you where the error is.  If there is no error, we do python dis.py <loop.scram to check the results.
For the programming part, I first read the input file and store all useful information in an array list line by line.  Then I check if the file contains labels or not.  My program handles two cases a little bit differently.  Then my program will  write output to loop.scram. We can then use the dissembler,python dis.py <loop.scram  to check if we get the correct output.

