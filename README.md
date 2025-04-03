# Java Virtual Machine Implementation

This project implements a simple virtual machine in pure Java. The VM simulates a basic computer architecture with a CPU, memory, and instruction set.

## Project Structure

- `src/main/java/vm/` - Main package containing the VM implementation
  - `CPU.java` - Simulates the CPU with registers and instruction execution
  - `Instruction.java` - Defines the instruction set for the VM
  - `Memory.java` - Simulates memory with read/write operations
  - `Program.java` - Represents a program that can be loaded and executed
  - `Register.java` - Represents a CPU register
  - `VirtualMachine.java` - Main class that ties