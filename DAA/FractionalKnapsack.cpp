#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// Structure to represent an item with value and weight
struct Item
{
    int value, weight;

    // Constructor to initialize the item
    Item(int v, int w)
    {
        value = v;
        weight = w;
    }
};

// Comparator to sort items based on value-to-weight ratio in descending order
bool compare(Item a, Item b)
{
    double r1 = (double)a.value / a.weight;
    double r2 = (double)b.value / b.weight;
    return r1 > r2; // Descending order
}

// Function to calculate the maximum value we can put in the knapsack
double fractionalKnapsack(int capacity, vector<Item> &items)
{
    // Sort items based on value-to-weight ratio
    sort(items.begin(), items.end(), compare);

    double totalValue = 0.0; // Store total value of knapsack

    for (const auto &item : items)
    {
        if (capacity >= item.weight)
        {
            // If the item fits entirely, take it
            capacity -= item.weight;
            totalValue += item.value;
        }
        else
        {
            // Take a fraction of the item to fill the remaining capacity
            double fraction = (double)capacity / item.weight;
            totalValue += item.value * fraction;
            break; // Knapsack is full
        }
    }

    return totalValue;
}

int main()
{
    int n, capacity;

    // Input the number of items and the knapsack capacity
    cout << "Enter the number of items: ";
    cin >> n;
    cout << "Enter the capacity of the knapsack: ";
    cin >> capacity;

    // Input each item's value and weight
    vector<Item> items;
    for (int i = 0; i < n; i++)
    {
        int value, weight;
        cout << "Enter value and weight of item " << (i + 1) << ": ";
        cin >> value >> weight;
        items.push_back(Item(value, weight));
    }

    // Calculate the maximum value
    double maxValue = fractionalKnapsack(capacity, items);
    cout << "The maximum value in the knapsack is: " << maxValue << endl;

    return 0;
}
