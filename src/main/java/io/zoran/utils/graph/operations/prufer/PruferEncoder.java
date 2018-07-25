package io.zoran.utils.graph.operations.prufer;

import io.zoran.utils.graph.operations.huffman.HuffmanNode;

import java.util.*;
import java.util.function.Predicate;

import static io.zoran.utils.graph.operations.huffman.internal.service.HuffmanUtils.isChildless;

/**
 * Created by sadowsm3 on 20.05.2018
 */
//FIXME
public class PruferEncoder {

    private Predicate<Map.Entry<Integer, HuffmanNode>> isAParentToThatNode(HuffmanNode node) {
        return parent -> parent.getValue().getLeftNode() == node || parent.getValue().getRightNode() == node;
    }

    public List<Integer> encode(Map<Integer, HuffmanNode> map) {
        List<Integer> pruferSequence = new ArrayList<>();
        encodeTreeToPrufer(map, pruferSequence, map.size());
        return pruferSequence;
    }

    private void encodeTreeToPrufer(Map<Integer, HuffmanNode> map, List<Integer> pruferSequence, Integer initialSize) {
        if (initialSize > pruferSequence.size() + 2) {
            Optional<Map.Entry<Integer, HuffmanNode>> entry = findLeafWithLowestLabel(map); //find leaf with lowest label
            if (entry.isPresent()) {
                pruferSequence.add(findNeighbouringNode(map, entry.get().getValue()));// add parent node
                map.remove(entry.get().getKey()); //remove it from the tree + cant remove node after it has been modified!!!
                encodeTreeToPrufer(map, pruferSequence, initialSize);
            }
        }
    }

    private Optional<Map.Entry<Integer, HuffmanNode>> findLeafWithLowestLabel(Map<Integer, HuffmanNode> map) {
        return map.entrySet().stream().filter(entry -> isChildless(entry.getValue())).min(Comparator.comparingInt(Map.Entry::getKey));
    }

    private Integer findNeighbouringNode(Map<Integer, HuffmanNode> map, HuffmanNode node) {
        Optional<Map.Entry<Integer, HuffmanNode>> entry = map.entrySet().stream().filter(isAParentToThatNode(node)).findFirst();
        if (entry.isPresent()) {
            HuffmanNode foundNode = entry.get().getValue();
            if (foundNode.getRightNode().equals(node)) {
                map.remove(entry.get().getKey());
                foundNode.setRightNode(null);
                map.put(entry.get().getKey(), foundNode);
            } else if (foundNode.getLeftNode().equals(node)) {
                map.remove(entry.get().getKey());
                foundNode.setLeftNode(null);
                map.put(entry.get().getKey(), foundNode);
            }
        }
        return entry.get().getKey();
    }
}
