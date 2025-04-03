package vm;

/**
 * Represents a register in the virtual machine's CPU.
 * Each register can hold a 32-bit integer value.
 */
public class Register {
    private int value;
    private final String name;

    /**
     * @param name
     */
    public Register(String name) {
        this.name = name;
        this.value = 0;
    }

    /**.
     * @param name  
     * @param value 
     */
    public Register(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value 
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param amount 
     */
    public void increment(int amount) {
        this.value += amount;
    }

    /**
     * @param amount 
     */
    public void decrement(int amount) {
        this.value -= amount;
    }

    @Override
    public String toString() {
        return name + " = " + value;
    }
}