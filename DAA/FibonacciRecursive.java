import java.util.Scanner;

public class FibonacciRecursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of terms: ");
        int n = sc.nextInt();
        System.out.println("Fibonacci Number at position " + n + " is: " + fibonacci(n));
    }

    // Recursive function to calculate Fibonacci
    public static int fibonacci(int n) {
        if (n <= 1) return n; // Base cases: F(0) = 0, F(1) = 1
        return fibonacci(n - 1) + fibonacci(n - 2); // Recursive relation
    }
}
