package vm.examples;

import vm.CPU;
import vm.Instruction;
import vm.Memory;
import vm.Program;
import vm.VirtualMachine;

/**
 * A simplified program that calculates the factorial of a number.
 */
public class SimpleFactorial {
    public static void main(String[] args) {
        // Create a new program
        Program program = new Program("SimpleFactorial");
        
        int n = 5; // Calculate factorial of 5
        
        // Initialize registers
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 0, n));    // R0 = n = 5
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 1, 1));    // R1 = result = 1
        
        // Multiply: result = result * 5 (5*1=5)
        program.addInstruction(new Instruction(Instruction.MUL, 1, 1, 0));
        
        // Decrement n: n = n - 1 = 4
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 2, 1));
        program.addInstruction(new Instruction(Instruction.SUB, 0, 0, 2));
        
        // Multiply: result = result * 4 (5*4=20)
        program.addInstruction(new Instruction(Instruction.MUL, 1, 1, 0));
        
        // Decrement n: n = n - 1 = 3
        program.addInstruction(new Instruction(Instruction.SUB, 0, 0, 2));
        
        // Multiply: result = result * 3 (20*3=60)
        program.addInstruction(new Instruction(Instruction.MUL, 1, 1, 0));
        
        // Decrement n: n = n - 1 = 2
        program.addInstruction(new Instruction(Instruction.SUB, 0, 0, 2));
        
        // Multiply: result = result * 2 (60*2=120)
        program.addInstruction(new Instruction(Instruction.MUL, 1, 1, 0));
        
        // Print and store result
      program.addInstruction(new Instruction(Instruction.PRINT, 1));
        program.addInstruction(new Instruction(Instruction.STORE, 1, 100));
        program.addInstruction(new Instruction(Instruction.HALT));
        
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