import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanNode implements Comparable<HuffmanNode> {
    char character;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}

public class HuffmanCoding1 {

    public static void main(String[] args) {
        char[] characters = {'A', 'E', 'I', 'O', 'U', 'S', 'T'};
        int[] frequencies = {10, 15, 12, 3, 4, 13, 1};

        Map<Character, String> huffmanCodes = getHuffmanCodes(characters, frequencies);
        double averageCodeLength = getAverageCodeLength(huffmanCodes, frequencies);
        int encodedMessageLength = getEncodedMessageLength(huffmanCodes, frequencies);

        // Print Huffman Codes
        System.out.println("Huffman Codes:");
        for (char character : huffmanCodes.keySet()) {
            System.out.println(character + ": " + huffmanCodes.get(character));
        }

        // Print Average Code Length
        System.out.println("Average Code Length: " + averageCodeLength);

        // Print Length of Huffman encoded message (in bits)
        System.out.println("Length of Huffman Encoded Message: " + encodedMessageLength + " bits");
    }

    private static Map<Character, String> getHuffmanCodes(char[] characters, int[] frequencies) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < characters.length; i++) {
            priorityQueue.add(new HuffmanNode(characters[i], frequencies[i]));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode x = priorityQueue.poll();
            HuffmanNode y = priorityQueue.poll();

            HuffmanNode internalNode = new HuffmanNode('-', x.frequency + y.frequency);
            internalNode.left = x;
            internalNode.right = y;

            priorityQueue.add(internalNode);
        }

        HuffmanNode root = priorityQueue.poll();
        Map<Character, String> huffmanCodes = new HashMap<>();
        buildHuffmanCodes(root, "", huffmanCodes);

        return huffmanCodes;
    }

    private static void buildHuffmanCodes(HuffmanNode node, String code, Map<Character, String> huffmanCodes) {
        if (node != null) {
            if (node.character != '-') {
                huffmanCodes.put(node.character, code);
            }
            buildHuffmanCodes(node.left, code + "0", huffmanCodes);
            buildHuffmanCodes(node.right, code + "1", huffmanCodes);
        }
    }

    private static double getAverageCodeLength(Map<Character, String> huffmanCodes, int[] frequencies) {
        double totalLength = 0;
        for (char character : huffmanCodes.keySet()) {
            int frequency = frequencies[indexOfCharacter(character, characters)];
            totalLength += huffmanCodes.get(character).length() * frequency;
        }
        return totalLength / getTotalFrequency(frequencies);
    }

    private static int getEncodedMessageLength(Map<Character, String> huffmanCodes, int[] frequencies) {
        int totalLength = 0;
        for (char character : huffmanCodes.keySet()) {
            int frequency = frequencies[indexOfCharacter(character, characters)];
            totalLength += huffmanCodes.get(character).length() * frequency;
        }
        return totalLength;
    }

    private static int getTotalFrequency(int[] frequencies) {
        int total = 0;
        for (int frequency : frequencies) {
            total += frequency;
        }
        return total;
    }

    private static int indexOfCharacter(char character, char[] characters) {
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == character) {
                return i;
            }
        }
        return -1;
    }
}
