import java.util.HashMap;
import java.util.PriorityQueue;
class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
    }

    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.frequency - node.frequency;
    }
}

class HuffmanTree {
    private HuffmanNode root;

    public HuffmanTree(HuffmanNode root) {
        this.root = root;
    }

    public HuffmanNode getRoot() {
        return root;
    }
}

class HuffmanEncoder {
    private HashMap<Character, String> encodingMap = new HashMap<>();

    public HuffmanEncoder(HuffmanNode root) {
        buildEncodingMap(root, "");
    }

    private void buildEncodingMap(HuffmanNode node, String code) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                encodingMap.put(node.data, code);
            }
            buildEncodingMap(node.left, code + "0");
            buildEncodingMap(node.right, code + "1");
        }
    }

    public String encode(String message) {
        StringBuilder encodedMessage = new StringBuilder();
        for (char character : message.toCharArray()) {
            encodedMessage.append(encodingMap.get(character));
        }
        return encodedMessage.toString();
    }

    public String decode(String encodedMessage, HuffmanTree huffmanTree) {
        StringBuilder decodedMessage = new StringBuilder();
        HuffmanNode current = huffmanTree.getRoot();

        for (char bit : encodedMessage.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                decodedMessage.append(current.data);
                current = huffmanTree.getRoot();
            }
        }

        return decodedMessage.toString();
    }
}

public class HuffmanCoding {

    public static void main(String[] args) {
        String message = "hello world";

        HashMap<Character, Integer> frequencyMap = buildFrequencyMap(message);
        HuffmanNode root = buildHuffmanTree(frequencyMap);
        HuffmanTree huffmanTree = new HuffmanTree(root);
        HuffmanEncoder encoder = new HuffmanEncoder(root);

        String encodedMessage = encoder.encode(message);
        System.out.println("Encoded Message: " + encodedMessage);

        String decodedMessage = encoder.decode(encodedMessage, huffmanTree);
        System.out.println("Decoded Message: " + decodedMessage);
    }

    private static HashMap<Character, Integer> buildFrequencyMap(String message) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char character : message.toCharArray()) {
            frequencyMap.put(character, frequencyMap.getOrDefault(character, 0) + 1);
        }
        return frequencyMap;
    }

    private static HuffmanNode buildHuffmanTree(HashMap<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (char character : frequencyMap.keySet()) {
            priorityQueue.add(new HuffmanNode(character, frequencyMap.get(character)));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            int sumFrequency = left.frequency + right.frequency;
            priorityQueue.add(new HuffmanNode(sumFrequency, left, right));
        }

        return priorityQueue.poll();
    }
}
