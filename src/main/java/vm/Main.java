package vm;

import java.util.Scanner;
import vm.examples.Factorial;
import vm.examples.SimpleAddition;
import vm.examples.SimpleFactorial;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n===== Virtual Machine Menu =====");
            System.out.println("1. Run SimpleAddition example");
            System.out.println("4. Run SimpleFactorial example");
            System.out.println("5. Custom operation");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice;
            try {
                String input = scanner.nextLine();
                if (input.contains("SimpleAddition")) {
                    choice = 1;
                } else if (input.contains("Factorial") && !input.contains("Simple")) {
                    choice = 2;
                } else if (input.contains("SumOfNumbers")) {
                    choice = 3;
                } else if (input.contains("SimpleFactorial")) {
                    choice = 4;
                } else if (input.contains("Custom")) {
                    choice = 5;
                } else if (input.contains("Exit") || input.contains("0")) {
                    choice = 0;
                } else {
                    try {
                        choice = Integer.parseInt(input.trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number or command name.");
                        continue;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error reading input. Please try again.");
                scanner.nextLine();
                continue;
            }
            
            if (choice == 0) {
                break;
            }
            
            switch (choice) {
                case 1:
                    runSimpleAddition();
                    break;
                case 2:
                    runFactorial();
                    break;
                case 3:
                    runSumOfNumbers();
                    break;
                case 4:
                    System.out.println("Running SimpleFactorial example:");
                    SimpleFactorial.main(null);
                    System.out.println();
                    break;
                case 5:
                    runCustomOperation(scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void runSimpleAddition() {
        System.out.println("Running SimpleAddition example:");
        SimpleAddition.main(null);
        System.out.println();
    }

    
    private static void runSumOfNumbers() {
        System.out.println("Running SumOfNumbers example:");
        Program program = new Program("SumOfNumbers");
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 0, 1));    
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 1, 10));   
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 2, 0));     
        program.addInstruction(new Instruction(Instruction.ADD, 2, 2, 0));      
        program.addInstruction(new Instruction(Instruction.SUB, 3, 0, 1));       
        program.addInstruction(new Instruction(Instruction.JZ, 3, 10));         
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 4, 1));     
        program.addInstruction(new Instruction(Instruction.ADD, 0, 0, 4));       

        program.addInstruction(new Instruction(Instruction.JMP, 3));           
        
        
        program.addInstruction(new Instruction(Instruction.PRINT, 2));    
        program.addInstruction(new Instruction(Instruction.HALT));               // Halt
        
        
        VirtualMachine vm = new VirtualMachine();
        vm.loadProgram(program, 0);
        vm.run();
    }
    
    /**
     * Factorial Example run krega.
     */
    private static void runFactorial() {
        System.out.println("Running Factorial example:");
        Factorial.main(null);
        System.out.println();
    }
    
    /**
     * custo m operation run krega.
     * 
     * @param scanner The scanner for user input
     */
    private static void runCustomOperation(Scanner scanner) {
        System.out.println("\n===== Custom Operation =====");
        System.out.println("Available operations:");
        System.out.println("1. Addition (ADD)");
        System.out.println("2. Subtraction (SUB)");
        System.out.println("3. Multiplication (MUL)");
        System.out.println("4. Division (DIV)");
        System.out.println("5. Modulo (MOD)");
        System.out.println("6. Power (POW)");
        System.out.println("7. Greatest Common Divisor (GCD)");
        System.out.println("8. Debug File (DEBUG)");
        System.out.print("Enter operation number or name: ");
        
        String opInput = scanner.nextLine();
        int opcode = Instruction.ADD; 
        
        if (opInput.contains("ADD") || opInput.contains("1")) {
            opcode = Instruction.ADD;
            System.out.println("Selected: Addition");
        } else if (opInput.contains("SUB") || opInput.contains("2")) {
            opcode = Instruction.SUB;
            System.out.println("Selected: Subtraction");
        } else if (opInput.contains("MUL") || opInput.contains("3")) {
            opcode = Instruction.MUL;
            System.out.println("Selected: Multiplication");
        } else if (opInput.contains("DIV") || opInput.contains("4")) {
            opcode = Instruction.DIV;
            System.out.println("Selected: Division");
        } else if (opInput.contains("MOD") || opInput.contains("5")) {
            opcode = Instruction.MOD;
            System.out.println("Selected: Modulo");
        } else if (opInput.contains("POW") || opInput.contains("6")) {
            opcode = Instruction.POW;
            System.out.println("Selected: Power");
        } else if (opInput.contains("GCD") || opInput.contains("7")) {
            opcode = Instruction.GCD;
            System.out.println("Selected: Greatest Common Divisor");
        } else if (opInput.contains("DEBUG") || opInput.contains("8")) {
            opcode = Instruction.DEBUG;
            System.out.println("Selected: Debug File");
            
            System.out.println("Select file type to debug:");
            System.out.println("1. Java");
            System.out.println("2. C++");
            System.out.print("Enter choice (1 or 2): ");
            
            int fileType;
            try {
                String fileTypeInput = scanner.nextLine().trim();
                if (fileTypeInput.contains("Java") || fileTypeInput.equals("1")) {
                    fileType = 0;  
                } else if (fileTypeInput.contains("C++") || fileTypeInput.equals("2")) {
                    fileType = 1;  
                } else {
                    System.out.println("Invalid choice. Defaulting to Java.");
                    fileType = 0;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Defaulting to Java.");
                fileType = 0;
            }
            
            //Filename batao : 
            System.out.print("Enter the full path to the file: ");
            String filename = scanner.nextLine().trim();
            
            
            Program program = new Program("DebugOperation");
            
            //Debug file load krega
            program.addInstruction(new Instruction(Instruction.DEBUG, fileType));
            program.addInstruction(new Instruction(Instruction.HALT));
        
            //Tattva load krega
            VirtualMachine vm = new VirtualMachine();
            vm.loadProgram(program, 0);
            
            
            vm.getCPU().setDebugFilename(filename);
            
            vm.run();
            
            return;  
        }
        
        System.out.print("Enter first value: ");
        int value1;
        try {
            value1 = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Defaulting to 5.");
            value1 = 5;
        }
        
        System.out.print("Enter second value: ");
        int value2;
        try {
            value2 = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Defaulting to 3.");
            value2 = 3;
        }
     
        Program program = new Program("CustomOperation");
        
       
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 0, value1));  
        program.addInstruction(new Instruction(Instruction.LOAD_VAL, 1, value2));  
        
        
        program.addInstruction(new Instruction(opcode, 2, 0, 1)); 
        
        
        program.addInstruction(new Instruction(Instruction.PRINT, 2));
        program.addInstruction(new Instruction(Instruction.HALT));      // Halt
        
        //TATTVA RUN KARAYEGA
        VirtualMachine vm = new VirtualMachine();
        vm.loadProgram(program, 0);
        vm.run();
    }
}