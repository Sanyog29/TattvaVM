package vm.examples;

import vm.CPU;
import vm.Instruction;
import vm.Memory;
import vm.Program;
import vm.VirtualMachine;

/**
 * A program that calculates the factorial of a number.
 */
public class Factorial {
    public static void main(String[] args) {
        // Create a new program
        Program program = new Program("Factorial");
        
        int n = 5; // Calculate factorial of 5
        
        // Initialize registers
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 0, n));    // n in register 0
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 1, 1));    // result in register 1
        
        // Loop start at address 2
        // Check if n <= 1
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 2, 1));    // Load 1 for comparison
        program.addInstruction(new Instruction(Instruction.SUB, 3, 0, 2));      // R3 = n - 1
        program.addInstruction(new Instruction(Instruction.JZ, 3, 10));         // If n == 1, jump to end
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 4, 0));    // Load 0 for comparison
        program.addInstruction(new Instruction(Instruction.SUB, 5, 3, 4));      // R5 = R3 - 0 (check if R3 < 0)
        program.addInstruction(new Instruction(Instruction.JZ, 5, 10));         // If R5 == 0 (n < 1), jump to end
        
        // Multiply result by n
        program.addInstruction(new Instruction(Instruction.MUL, 1, 1, 0));      // result = result * n
        
        // Decrement n
        program.addInstruction(new Instruction(Instruction.SUB, 0, 0, 2));      // n = n - 1
        
        // Jump back to loop start
        program.addInstruction(new Instruction(Instruction.JMP, 2));            // Jump to loop start
        
        // End: print result and store
        program.addInstruction(new Instruction(Instruction.PRINT, 1));          // Print result directly
        program.addInstruction(new Instruction(Instruction.STORE, 1, 100));     // Store result at address 100
        program.addInstruction(new Instruction(Instruction.HALT));              // Halt the program
        
        // Create and run the virtual machine
        Memory memory = new Memory(1024);
        CPU cpu = new CPU(memory);
        VirtualMachine vm = new VirtualMachine(cpu, memory);
        
        vm.loadProgram(program, 0);
        vm.run();
        
        // Print the result
        System.out.println("Factorial of " + n + " is: " + memory.read(100));
    }
}