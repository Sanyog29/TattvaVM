package vm;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a program that can be loaded and executed by the virtual machine.
 */
public class Program {
    private final String name;
    private final List<Instruction> instructions;
    
    /**
     * Naye program ka constructor.
     *
     * @param name The name of the program
     */
    public Program(String name) {
        this.name = name;
        this.instructions = new ArrayList<>();
    }
    
    /**
     * Program ka naam bulane ka method.  
     *
     * @return The name
     */
    public String getName() {
        return name;
    }
    
    /**
     * instruction ko program mein add karne ka method.
     *
     * @param instruction The instruction to add
     */
    public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }
    
    /**
     * program ke instructions ko bulane ka method.
     *
     * @return The instructions
     */
    public List<Instruction> getInstructions() {
        return instructions;
    }
    
    /**
     * instructions ki size ko bulane ka method.
     *
     * @return The size
     */
    public int size() {
        return instructions.size();
    }
}