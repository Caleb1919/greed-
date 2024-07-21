package algorithms.Greedy;

import algorithms.Algorithm;
import java.util.PriorityQueue;

public class HuffmanCodes implements Algorithm {
    private int[] freq;
    private char[] chars;
    private HuffmanNode root;

    class HuffmanNode implements Comparable<HuffmanNode> {
        int frequency;
        char c;
        HuffmanNode left;
        HuffmanNode right;

        HuffmanNode(char c, int frequency) {
            this.c = c;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(HuffmanNode node) {
            return this.frequency - node.frequency;
        }
    }

    public HuffmanCodes(char[] chars, int[] freq) {
        this.chars = chars;
        this.freq = freq;
    }

    @Override
    public void execute() {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();

        for (int i = 0; i < chars.length; i++) {
            queue.add(new HuffmanNode(chars[i], freq[i]));
        }

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode node = new HuffmanNode('-', left.frequency + right.frequency);
            node.left = left;
            node.right = right;
            queue.add(node);
        }

        root = queue.poll();
    }

    @Override
    public void displayResult() {
        System.out.println("Huffman Codes:");
        printCode(root, "");
    }

    private void printCode(HuffmanNode root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + ": " + s);
            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }
}
