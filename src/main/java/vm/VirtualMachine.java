package vm;

/**
 * Main class for the virtual machine implementation.
 * Integrates CPU and memory components and provides methods to load and execute programs.
 */
public class VirtualMachine {
    private final CPU cpu;
    private final Memory memory;
    
    /**
     * Tattva ka naya virtual machine banayega.
     * Default memory size = 1024 words.
     */
    public VirtualMachine() {
        this(1024);
    }
    
    /** 
     * @param memorySize 
     */
    public VirtualMachine(int memorySize) {
        this.memory = new Memory(memorySize);
        this.cpu = new CPU(memory);
    }
    
    /**
     * @param cpu 
     * @param memory 
     */
    public VirtualMachine(CPU cpu, Memory memory) {
        this.cpu = cpu;
        this.memory = memory;
    }
    
    /**
     *Program ko load karega memory mein ek specific location pr.
     * 
     * @param program 
     * @param startAddress 
     */
    public void loadProgram(Program program, int startAddress) {
        int address = startAddress;
        for (Instruction instruction : program.getInstructions()) {
            memory.write(address++, instruction.getOpcode());
            
            for (int operand : instruction.getOperands()) {
                memory.write(address++, operand);
            }
        }

        cpu.setProgramCounter(startAddress);
    }

    //execute method ko run karega jab tak halt nahi hota.
    public void execute() {
        boolean running = true;
        
        while (running) {
            int pc = cpu.getProgramCounter();
            int opcode = memory.read(pc);
            
            try {
                
                int[] operands = new int[getOperandCount(opcode)];
                for (int i = 0; i < operands.length; i++) {
                    operands[i] = memory.read(pc + 1 + i);
                }
                
                Instruction instruction = new Instruction(opcode, operands);

                if (debugMode) {
                    debugInstruction(instruction, pc);
                }
                
                running = cpu.executeInstruction(instruction);
                

                if (running) {
                    cpu.setProgramCounter(pc + 1 + operands.length);
                }
            } catch (Exception e) {
                System.err.println("ERROR at PC=" + pc + ", opcode=" + opcode + ": " + e.getMessage());
                if (debugMode) {
                    e.printStackTrace();
                    System.err.println("Memory dump around PC:");
                    dumpMemory(pc - 5, pc + 5);
                    System.err.println("Register state:");
                    System.err.println(cpu.getRegistersState());
                }
                break;
            }
        }
    }
    
    /**
     * Debugging ke liye memory dump karega.
     * 
     * @param start 
     * @param end 
     */
    private void dumpMemory(int start, int end) {
        start = Math.max(0, start);
        end = Math.min(memory.getSize() - 1, end);
        
        for (int i = start; i <= end; i++) {
            int value = memory.read(i);
            String marker = (i == cpu.getProgramCounter()) ? " <-- PC" : "";
            System.err.println("Memory[" + i + "] = " + value + 
                    (value >= 0 && value < 30 ? " (" + getOpcodeName(value) + ")" : "") + marker);
        }
    }
    
    private boolean debugMode = false;
    
    /**
     * @param enabled 
     */
    public void setDebugMode(boolean enabled) {
        this.debugMode = enabled;
    }
    
    /**
     * @param instruction 
     * @param pc 
     */
    private void debugInstruction(Instruction instruction, int pc) {
        System.out.println("DEBUG [PC=" + pc + "]: " + getInstructionString(instruction));
        System.out.println("Registers: " + cpu.getRegistersState());
    }
    
    /**
     * Instruction ka string representation dega. 
     * @param instruction 
     * @return
     */
    private String getInstructionString(Instruction instruction) {
        int opcode = instruction.getOpcode();
        int[] operands = instruction.getOperands();
        
        String opcodeName = getOpcodeName(opcode);
        StringBuilder sb = new StringBuilder(opcodeName);
        
        for (int operand : operands) {
            sb.append(" ").append(operand);
        }
        
        return sb.toString();
    }
    
    
    /**
     * @param opcode
     * @return 
     */
    private String getOpcodeName(int opcode) {
        switch (opcode) {
            case Instruction.HALT: return "HALT";
            case Instruction.LOAD: return "LOAD";
            case Instruction.STORE: return "STORE";
            case Instruction.ADD: return "ADD";
            case Instruction.SUB: return "SUB";
            case Instruction.MUL: return "MUL";
            case Instruction.DIV: return "DIV";
            case Instruction.JMP: return "JMP";
            case Instruction.JZ: return "JZ";
            case Instruction.JNZ: return "JNZ";
            case Instruction.LOAD_VAL: return "LOAD_VAL";
            case Instruction.MOV: return "MOV";
            case Instruction.PRINT: return "PRINT";
            case Instruction.PUSH: return "PUSH";
            case Instruction.POP: return "POP";
            case Instruction.CALL: return "CALL";
            case Instruction.RET: return "RET";
            case Instruction.JLE: return "JLE";
            case Instruction.MOD: return "MOD";
            case Instruction.POW: return "POW";
            case Instruction.GCD: return "GCD";
            case Instruction.AND: return "AND";
            case Instruction.OR: return "OR";
            case Instruction.XOR: return "XOR";
            case Instruction.SHIFT_LEFT: return "SHIFT_LEFT";
            case Instruction.SHIFT_RIGHT: return "SHIFT_RIGHT";
            case Instruction.DEBUG: return "DEBUG";
            default: return "UNKNOWN(" + opcode + ")";
        }
    }
    
    /**
     * @param opcode 
     * @return
     */
    private int getOperandCount(int opcode) {
        switch (opcode) {
            case Instruction.HALT:
                return 0;
            case Instruction.JMP:
            case Instruction.PRINT:
            case Instruction.DEBUG:
                return 1;
            case Instruction.LOAD:
            case Instruction.STORE:
            case Instruction.LOAD_VAL:
            case Instruction.JZ:
            case Instruction.JNZ:
            case Instruction.MOV:
            case Instruction.PUSH:
            case Instruction.POP:  
            case Instruction.CALL:
            case Instruction.RET:
            case Instruction.JLE:
                return 2;
            case Instruction.ADD:
            case Instruction.SUB:
            case Instruction.MUL:
            case Instruction.DIV:
            case Instruction.MOD:
            case Instruction.POW:
            case Instruction.GCD:
            case Instruction.AND:
            case Instruction.OR:
            case Instruction.XOR:
            case Instruction.SHIFT_LEFT:
            case Instruction.SHIFT_RIGHT:
                return 3;
            default:
                throw new IllegalArgumentException("Unknown opcode: " + opcode);
        }
    }
    
    /**
     * execute method ko run karega.
     */
    public void run() {
        execute();
    }
    
    /**
     * Tattva ke CPU ko bulane ka method.
     * @return 
     */
    public CPU getCPU() {
        return cpu;
    }
    
    /**
     * Tattva ke memory ko bulane ka method.
     * @return 
     */
    public Memory getMemory() {
        return memory;
    }
}