package vm;

/**
 * Simulates memory with read/write operations.
 */
public class Memory {
    private final int[] memory;
    
    /**
     * memory banayega
     *
     * @param size The size of memory in words
     */
    public Memory(int size) {
        this.memory = new int[size];
    }
    
    /**
     * words ko memory se read karega.
     *
     * @param address The address to read from
     * @return The value at the address
     * @throws IndexOutOfBoundsException 
     */
    public int read(int address) {
        if (address < 0 || address >= memory.length) {
            throw new IndexOutOfBoundsException("Memory address out of bounds: " + address);
        }
        return memory[address];
    }
    
    /**
     * word ko memory me write karega.
     *
     * @param address The address to write to
     * @param value The value to write
     * @throws IndexOutOfBoundsException if the address is out of bounds
     */
    public void write(int address, int value) {
        if (address < 0 || address >= memory.length) {
            throw new IndexOutOfBoundsException("Memory address out of bounds: " + address);
        }
        memory[address] = value;
    }
    
    // Iss memory method add krega
    /**
     * 
     * @return The size of the memory in words
     */
    public int getSize() {
        return memory.length;
    }
}