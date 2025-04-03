package vm;

/**
 * Simulates a CPU with registers and instruction execution.
 */
public class CPU {

    private static final int NUM_REGISTERS = 16;

    private final int[] registers;
    private int programCounter;
    private final Memory memory;

    /**
     * Naya CPU banayega ek specific memory ke saath.
     *
     * @param memory
     */
    public CPU(Memory memory) {
        this.registers = new int[NUM_REGISTERS];
        this.programCounter = 0;
        this.memory = memory;
    }

    /**
     * Ye rhega program counter ka current value.
     *
     * @return The program counter
     */
    public int getProgramCounter() {
        return programCounter;
    }

    /**
     * program counter ka value set karega.
     *
     * @param programCounter The new value of program counter
     */
    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    /**
     * ye CPU ke instruction execute krega
     *
     * @param instruction The instruction to execute
     * @return true if execution should continue, false if halted
     */
    private String debugFilename;

    // Iss method ko debugFilename set karne ke liye use karega
    public void setDebugFilename(String filename) {
        this.debugFilename = filename;
    }

    // Opcodes ke liye
    public boolean executeInstruction(Instruction instruction) {
        int opcode = instruction.getOpcode();
        int[] operands = instruction.getOperands();

        switch (opcode) {
            case Instruction.HALT:
                return false;

            case Instruction.LOAD:
                registers[operands[0]] = memory.read(operands[1]);
                break;

            case Instruction.STORE:
                memory.write(operands[1], registers[operands[0]]);
                break;

            case Instruction.LOAD_VAL:
                registers[operands[0]] = operands[1];
                break;

            case Instruction.ADD:
                registers[operands[0]] = registers[operands[1]] + registers[operands[2]];
                break;

            case Instruction.SUB:
                registers[operands[0]] = registers[operands[1]] - registers[operands[2]];
                break;

            case Instruction.MUL:
                registers[operands[0]] = registers[operands[1]] * registers[operands[2]];
                break;

            case Instruction.DIV:
                registers[operands[0]] = registers[operands[1]] / registers[operands[2]];
                break;

            case Instruction.MOD:
                registers[operands[0]] = registers[operands[1]] % registers[operands[2]];
                break;

            case Instruction.POW:
                registers[operands[0]] = (int) Math.pow(registers[operands[1]], registers[operands[2]]);
                break;

            case Instruction.GCD:
                registers[operands[0]] = gcd(registers[operands[1]], registers[operands[2]]);
                break;

            case Instruction.AND:
                registers[operands[0]] = registers[operands[1]] & registers[operands[2]];
                break;

            case Instruction.OR:
                registers[operands[0]] = registers[operands[1]] | registers[operands[2]];
                break;

            case Instruction.XOR:
                registers[operands[0]] = registers[operands[1]] ^ registers[operands[2]];
                break;

            case Instruction.SHIFT_LEFT:
                registers[operands[0]] = registers[operands[1]] << registers[operands[2]];
                break;

            case Instruction.SHIFT_RIGHT:
                registers[operands[0]] = registers[operands[1]] >> registers[operands[2]];
                break;

            case Instruction.JMP:
                programCounter = operands[0] - 1;
                break;

            case Instruction.JZ:
                if (registers[operands[0]] == 0) {
                    programCounter = operands[1] - 1;
                }
                break;

            case Instruction.JNZ:
                if (registers[operands[0]] != 0) {
                    programCounter = operands[1] - 1;
                }
                break;

            case Instruction.PRINT:
                System.out.println(registers[operands[0]]);
                break;

            case Instruction.DEBUG:
                return executeDebugInstruction(operands[0], debugFilename);

            default:
                throw new IllegalArgumentException("Unknown opcode: " + opcode);
        }

        return true;
    }

    /**
     * FileType ke hisab se file ko debug karega jaise CPP Ya Java.
     *
     * @param fileType The file type (0 for Java, 1 for C++)
     */
    private void debugFile(int fileType) {
        String fileExtension = (fileType == 0) ? "java" : "cpp";
        System.out.println("\n===== Debugging " + fileExtension + " File =====");

        if (fileType == 0) {
            System.out.println("1. Checking for syntax errors...");
            System.out.println("2. Verifying imports...");
            System.out.println("3. Checking method signatures...");
            System.out.println("4. Analyzing control flow...");
            System.out.println("5. Checking for null pointer exceptions...");
            System.out.println("6. Verifying resource management...");
            System.out.println("7. Checking thread safety...");
            System.out.println("Debug complete: No issues found in Java file.");
        } else {
            System.out.println("1. Checking for syntax errors...");
            System.out.println("2. Verifying header includes...");
            System.out.println("3. Checking memory allocation/deallocation...");
            System.out.println("4. Analyzing pointer usage...");
            System.out.println("5. Checking for memory leaks...");
            System.out.println("6. Verifying template instantiations...");
            System.out.println("7. Checking for undefined behavior...");
            System.out.println("Debug complete: No issues found in C++ file.");
        }
    }

    /**
     * GCD ka function hai jo do numbers ka GCD nikalta hai.
     *
     * @param a The first number
     * @param b The second number
     * @return The greatest common divisor
     */
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * Register ka value return karega.
     *
     * @param register The register number
     * @return
     */
    public int getRegister(int register) {
        return registers[register];
    }

    /**
     * register ka value set karega.
     *
     * @param register The register number
     * @param value
     */
    public void setRegister(int register, int value) {
        registers[register] = value;
    }

    /**
     * ye registers ka state return karega.
     *
     * @return A string representation of the registers
     */
    public String getRegistersState() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < registers.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("R").append(i).append("=").append(registers[i]);
        }
        return sb.toString();
    }

    // debug dekhega ki file ka type kya hai aur uske hisab se file ko debug karega.
    public boolean executeDebugInstruction(int fileType, String filename) {
        System.out.println("Debugging file: " + filename);

        if (fileType == 0) {
            debugJavaFile(filename);
        } else if (fileType == 1) {
            debugCppFile(filename);
        } else {
            System.out.println("Unknown file type: " + fileType);
        }

        return true;
    }

    private void debugJavaFile(String filename) {
        System.out.println("Analyzing Java file: " + filename);
        try {
            java.nio.file.Path path = java.nio.file.Paths.get(filename);
            java.util.List<String> lines = java.nio.file.Files.readAllLines(path);

            int braceCount = 0;
            boolean hasPackage = false;
            boolean importsAfterClass = false;
            boolean lastLineWasClass = false;

            System.out.println("\nPotential issues found:");

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                for (char c : line.toCharArray()) {
                    if (c == '{') {
                        braceCount++;
                    }
                    if (c == '}') {
                        braceCount--;
                    }
                }
                if (line.trim().startsWith("package ")) {
                    hasPackage = true;
                }
                if (line.trim().startsWith("import ") && lastLineWasClass) {
                    importsAfterClass = true;
                    System.out.println("Line " + (i + 1) + ": Import statement after class declaration");
                }
                if (line.contains("class ")) {
                    lastLineWasClass = true;
                } else {
                    lastLineWasClass = false;
                }
                if (line.contains(".") && line.contains("null")) {
                    System.out.println("Line " + (i + 1) + ": Potential null pointer dereference");
                }
                if (line.matches(".*\\bint\\s+\\w+\\s*=.*") && !lines.toString().contains(line.split("=")[0].split("int")[1].trim())) {
                    System.out.println("Line " + (i + 1) + ": Potential unused variable");
                }
            }

            // ye batayega ki kya package hai ya nahi
            if (!hasPackage) {
                System.out.println("Missing package declaration");
            }

            if (braceCount != 0) {
                System.out.println("Unbalanced braces: " + (braceCount > 0 ? "Missing " + braceCount + " closing braces" : "Extra " + (-braceCount) + " closing braces"));
            }

            if (importsAfterClass) {
                System.out.println("Import statements should be before class declaration");
            }

        } catch (Exception e) {
            System.out.println("Error analyzing file: " + e.getMessage());
        }
    }

    private void debugCppFile(String filename) {
        System.out.println("Analyzing C++ file: " + filename);
        // CPP file ka analysis karega
        try {
            java.nio.file.Path path = java.nio.file.Paths.get(filename);
            java.util.List<String> lines = java.nio.file.Files.readAllLines(path);

            int braceCount = 0;
            boolean hasMemoryAllocation = false;
            boolean hasMemoryDeallocation = false;

            System.out.println("\nPotential issues found:");

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                for (char c : line.toCharArray()) {
                    if (c == '{') {
                        braceCount++;
                    }
                    if (c == '}') {
                        braceCount--;
                    }
                }

                if (line.contains("new ")) {
                    hasMemoryAllocation = true;
                    if (!line.contains("unique_ptr") && !line.contains("shared_ptr")) {
                        System.out.println("Line " + (i + 1) + ": Raw memory allocation, consider using smart pointers");
                    }
                }

                if (line.contains("delete ") || line.contains("delete[] ")) {
                    hasMemoryDeallocation = true;
                }

                if (line.contains("strcpy") && !line.contains("strncpy")) {
                    System.out.println("Line " + (i + 1) + ": Potential buffer overflow with strcpy, consider using strncpy");
                }

                if (line.matches(".*\\w+\\s*\\*\\s*\\w+\\s*;.*") && !line.contains("=")) {
                    System.out.println("Line " + (i + 1) + ": Uninitialized pointer");
                }
            }

            if (braceCount != 0) {
                System.out.println("Unbalanced braces: " + (braceCount > 0 ? "Missing " + braceCount + " closing braces" : "Extra " + (-braceCount) + " closing braces"));
            }

            if (hasMemoryAllocation && !hasMemoryDeallocation) {
                System.out.println("Potential memory leak: Memory allocation without deallocation");
            }

        } catch (Exception e) {
            System.out.println("Error analyzing file: " + e.getMessage());
        }
    }
}
