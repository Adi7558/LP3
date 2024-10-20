import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

// Node class representing each character and its frequency
class HuffmanNode {
    int frequency;
    char character;
    HuffmanNode left, right;

    // Constructor for internal (non-leaf) nodes
    HuffmanNode(int frequency, char character) {
        this.frequency = frequency;
        this.character = character;
        this.left = null;
        this.right = null;
    }
}

// Comparator to prioritize nodes with smaller frequencies
class NodeComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode n1, HuffmanNode n2) {
        return n1.frequency - n2.frequency;
    }
}

public class HuffmanEncoding {
    // Main method to drive the program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the input string: ");
        String input = sc.nextLine();

        // Calculate frequency of each character
        int[] frequency = new int[256]; // Supports extended ASCII characters
        for (char c : input.toCharArray()) {
            frequency[c]++;
        }

        // Build the Huffman Tree
        HuffmanNode root = buildHuffmanTree(frequency);

        // Print the Huffman Codes
        System.out.println("Character | Huffman Code");
        System.out.println("------------------------");
        printCodes(root, "");
    }

    // Method to build the Huffman Tree using a priority queue (min-heap)
    public static HuffmanNode buildHuffmanTree(int[] frequency) {
        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>(new NodeComparator());

        // Create a leaf node for each character with non-zero frequency
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] > 0) {
                minHeap.add(new HuffmanNode(frequency[i], (char) i));
            }
        }

        // Build the tree until only one node remains in the heap
        while (minHeap.size() > 1) {
            // Extract the two nodes with the smallest frequency
            HuffmanNode left = minHeap.poll();
            HuffmanNode right = minHeap.poll();

            // Create a new internal node with these two nodes as children
            HuffmanNode newNode = new HuffmanNode(left.frequency + right.frequency, '-');
            newNode.left = left;
            newNode.right = right;

            // Add the new node back to the heap
            minHeap.add(newNode);
        }

        // The remaining node is the root of the Huffman Tree
        return minHeap.poll();
    }

    // Method to print the Huffman Codes by traversing the tree
    public static void printCodes(HuffmanNode node, String code) {
        if (node == null) return;

        // If it's a leaf node, print the character and its code
        if (node.left == null && node.right == null) {
            System.out.println("   " + node.character + "      |   " + code);
            return;
        }

        // Traverse left and right with updated code
        printCodes(node.left, code + "0");
        printCodes(node.right, code + "1");
    }
}
