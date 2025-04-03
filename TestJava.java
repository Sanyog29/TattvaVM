public class TestJava {
    // Missing package declaration
    
    // Some imports
    import java.util.List;
    import java.util.ArrayList;
    
    public static void main(String[] args) {
        // Potential null pointer issue
        String str = null;
        if (Math.random() > 0.5) {
            str = "Hello";
        }
        
        // Uncomment to test null pointer exception
        // System.out.println(str.length());
        
        // Unbalanced braces example
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);
        // Missing closing brace here
        
        // Method with potential issues
        calculateSum(5);
        
        // Unused variable
        int unusedVar = 42;
    }
    
    public static int calculateSum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        // Missing return statement
    }
}