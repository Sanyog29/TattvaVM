#include <iostream>
#include <vector>
#include <string>

// Memory leak example
void memoryLeakExample() {
    int* arr = new int[10];
    // Missing delete[] arr;
}

// Uninitialized pointer usage
void uninitializedPointerExample() {
    int* ptr;
    // Uncomment to test uninitialized pointer
    // *ptr = 42;
}

// Potential buffer overflow
void bufferOverflowExample() {
    char buffer[5];
    // Uncomment to test buffer overflow
    // strcpy(buffer, "This is too long for the buffer");
}

// Missing return value
int missingReturnExample() {
    int x = 42;
    // Missing return statement
}

int main() {
    std::cout << "Testing C++ debugging features" << std::endl;
    
    // Memory allocation without deallocation
    int* data = new int[100];
    
    // Smart pointer usage (good practice)
    std::unique_ptr<int[]> smartData(new int[100]);
    
    // Vector with potential issues
    std::vector<int> vec;
    for (int i = 0; i < 10; i++) {
        vec.push_back(i);
    }
    
    // Uncomment to test out of bounds access
    // std::cout << vec[10] << std::endl;
    
    // Call functions with issues
    memoryLeakExample();
    
    // Uncomment to test other issues
    // uninitializedPointerExample();
    // bufferOverflowExample();
    // int result = missingReturnExample();
    
    // Memory leak (forgot to delete)
    // delete[] data;
    
    return 0;
}