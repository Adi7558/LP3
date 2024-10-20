//  Write a program non-recursive and recursive program to calculate Fibonacci 
// numbers and analyze their time and space complexity.

import java.util.Scanner;

public class FibonacciIterative {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of terms: ");
        int n = sc.nextInt();
        System.out.println("Fibonacci Number at position " + n + " is: " + fibonacci(n));
    }

    // Iterative function to calculate Fibonacci
    public static int fibonacci(int n) {
        if (n <= 1) return n; // Base cases: F(0) = 0, F(1) = 1

        int a = 0, b = 1, result = 0;
        for (int i = 2; i <= n; i++) {
            result = a + b; // Calculate the next term
            a = b; // Update a to the next value
            b = result; // Update b to the next value
        }
        return result;
    }
}
