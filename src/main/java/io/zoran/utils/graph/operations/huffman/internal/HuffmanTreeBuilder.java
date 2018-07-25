package io.zoran.utils.graph.operations.huffman.internal;

import io.zoran.utils.graph.operations.huffman.HuffmanNode;

import java.io.Serializable;
import java.util.Map;
import java.util.Queue;

import static io.zoran.utils.graph.operations.huffman.internal.service.HuffmanUtils.hasLeftChild;
import static io.zoran.utils.graph.operations.huffman.internal.service.HuffmanUtils.hasRightChild;
import static io.zoran.utils.graph.operations.huffman.internal.service.HuffmanUtils.isChildless;


/**
 * Created by sadowsm3 on 19.05.2018
 */
public class HuffmanTreeBuilder implements Serializable {

    public HuffmanNode buildTree(Queue<HuffmanNode> priorityQueue) {
        while(priorityQueue.size() > 1) {
            final HuffmanNode node1 = priorityQueue.poll();
            final HuffmanNode node2 = priorityQueue.poll();

            //todo node validity check
            HuffmanNode parentNode = new HuffmanNode(null , node1.getFrequency() + node2.getFrequency());
            parentNode.setLeftNode(node1);
            parentNode.setRightNode(node2);
            priorityQueue.add(parentNode);
        }

        return priorityQueue.poll(); //contains information about entire tree
    }

    public void huffmanCodeGenerator(HuffmanNode rootNode, Map<Character, String> encodingMap, String code) {
        if(isChildless(rootNode))
            encodingMap.put(rootNode.getCharacter(), code);

        if(hasLeftChild(rootNode))
            huffmanCodeGenerator(rootNode.getLeftNode(), encodingMap,code + "0");

        if(hasRightChild(rootNode))
            huffmanCodeGenerator(rootNode.getRightNode(), encodingMap,code + "1");
    }
}
