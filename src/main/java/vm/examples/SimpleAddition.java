package vm.examples;

import vm.CPU;
import vm.Instruction;
import vm.Memory;
import vm.Program;
import vm.VirtualMachine;

/**
 * A simple program that adds two numbers.
 */
public class SimpleAddition {
    public static void main(String[] args) {
        // Create a new program
        Program program = new Program("SimpleAddition");
        
        // Add instructions to load values, add them, and store the result
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 0, 5)); // Load value 5 into register 0
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 1, 7)); // Load value 7 into register 1
        program.addInstruction(new Instruction(Instruction.ADD, 2, 0, 1));   // Add registers 0 and 1, store in register 2
        program.addInstruction(new Instruction(Instruction.STORE, 2, 100));  // Store the result at memory address 100
        program.addInstruction(new Instruction(Instruction.HALT));           // Halt the program
        
        // Create and run the virtual machine
        Memory memory = new Memory(1024);
        CPU cpu = new CPU(memory);
        VirtualMachine vm = new VirtualMachine(cpu, memory);
        
        vm.loadProgram(program, 0);
        vm.run();
        
        // Print the result
        System.out.println("Result of addition: " + memory.read(100));
    }
}