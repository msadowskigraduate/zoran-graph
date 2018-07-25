package io.zoran.utils.graph.operations.huffman.internal.service;

import io.zoran.utils.graph.operations.huffman.HuffmanNode;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by sadowsm3 on 19.05.2018
 */
public class MapToNodeConverter {

    public static PriorityQueue<HuffmanNode> convert(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> nodehuffmanNodePriorityQueue = new PriorityQueue<>(freqMap.size());
        freqMap.forEach((key, x) -> nodehuffmanNodePriorityQueue.add(new HuffmanNode(key, x)));
        return nodehuffmanNodePriorityQueue;
    }
}
