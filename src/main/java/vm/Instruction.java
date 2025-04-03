package vm;

/**
 * Represents a single instruction in the virtual machine.
 */
public class Instruction {
    // Opcodes
    public static final int HALT = 0;
    public static final int LOAD = 1;
    public static final int STORE = 2;
    public static final int LOAD_VAL = 3;
    public static final int ADD = 4;
    public static final int SUB = 5;
    public static final int MUL = 6;
    public static final int DIV = 7;
    public static final int JMP = 8;
    public static final int JZ = 9;
    public static final int JNZ = 10;
    public static final int PRINT = 11;
    
    // New opcodes
    public static final int MOD = 12;
    public static final int POW = 13;
    public static final int GCD = 14;
    public static final int AND = 15;
    public static final int OR = 16;
    public static final int XOR = 17;
    public static final int SHIFT_LEFT = 18;
    public static final int SHIFT_RIGHT = 19;
    public static final int DEBUG = 20;  
    
    // Kuch additional opcodes
    public static final int MOV = 21;
    public static final int PUSH = 22;
    public static final int POP = 23;    
    public static final int CALL = 24;   
    public static final int RET = 25;    
    public static final int JLE = 26;    
    
    private final int opcode;
    private final int[] operands;
    
    /**
     * Naye instruction ka constructor.
     * 
     * @param opcode The opcode
     * @param operands The operands
     */
    public Instruction(int opcode, int... operands) {
        this.opcode = opcode;
        this.operands = operands;
    }
    
    /**
     *Opcode ko bulane ka method.
     * 
     * @return The opcode
     */
    public int getOpcode() {
        return opcode;
    }
    
    /**
     * Operand ko bulane ka method.
     * 
     * @return The operands
     */
    public int[] getOperands() {
        return operands;
    }
}