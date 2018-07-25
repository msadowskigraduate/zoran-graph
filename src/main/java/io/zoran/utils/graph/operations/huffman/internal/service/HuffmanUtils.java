package io.zoran.utils.graph.operations.huffman.internal.service;

import io.zoran.utils.graph.operations.huffman.HuffmanNode;

import java.util.Map;

/**
 * Created by sadowsm3 on 20.05.2018
 */
public class HuffmanUtils {
    private static Integer NODE_NUMBER = 1;

    public static boolean isChildless(HuffmanNode node) {
        return node != null && node.getRightNode() == null && node.getLeftNode() == null;
    }

    public static boolean hasRightChild(HuffmanNode node) {
        return node.getRightNode() != null;
    }

    public static boolean hasLeftChild(HuffmanNode node) {
        return node.getLeftNode() != null;
    }

    public static boolean isNodeValid(HuffmanNode node) {
        return node.getFrequency() != null && node.getCharacter() != null;
    }

    public static void assignNodeNumeration(HuffmanNode huffmanNode, Map<Integer,HuffmanNode> map) {
        map.put(NODE_NUMBER++, huffmanNode);
        if (hasLeftChild(huffmanNode)) {
            assignNodeNumeration(huffmanNode.getLeftNode(), map);
        }
        if (hasRightChild(huffmanNode)) {
            assignNodeNumeration(huffmanNode.getRightNode(), map);
        }
    }

    public static String stringfyEncodingMap(Map<Character, String> encodingMap) {
        return encodingMap.entrySet().stream().map(x -> x.getKey() + " -> " + x.getValue() + "\n").reduce("", String::concat);
    }
}
