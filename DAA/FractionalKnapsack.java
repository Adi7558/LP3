import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    int value, weight;

    // Constructor to initialize the item
    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {
    // Method to calculate the maximum value that can be obtained
    public static double getMaxValue(Item[] items, int capacity) {
        // Sort items by value-to-weight ratio in descending order
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                double r1 = (double) a.value / a.weight;
                double r2 = (double) b.value / b.weight;
                return Double.compare(r2, r1); // Descending order
            }
        });

        double totalValue = 0.0; // Store total value of items in the knapsack

        for (Item item : items) {
            if (capacity - item.weight >= 0) {
                // If the item can fit entirely, take it
                capacity -= item.weight;
                totalValue += item.value;
            } else {
                // Take the fraction of the remaining capacity
                double fraction = (double) capacity / item.weight;
                totalValue += item.value * fraction;
                break; // Knapsack is full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of items
        System.out.print("Enter the number of items: ");
        int n = sc.nextInt();

        // Input the capacity of the knapsack
        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = sc.nextInt();

        // Create an array of items
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter value and weight of item " + (i + 1) + ": ");
            int value = sc.nextInt();
            int weight = sc.nextInt();
            items[i] = new Item(value, weight);
        }

        // Calculate the maximum value
        double maxValue = getMaxValue(items, capacity);
        System.out.printf("The maximum value in the knapsack is: %.2f\n", maxValue);
    }
}
