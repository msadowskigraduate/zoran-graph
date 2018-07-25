package io.zoran.utils.graph.operations.prufer;

import io.zoran.utils.graph.operations.huffman.HuffmanNode;

import java.util.List;
import java.util.Map;

/**
 * Created by sadowsm3 on 20.05.2018
 */
public class PruferRunner {
    private final PruferEncoder pruferEncoder;

    public PruferRunner(PruferEncoder pruferEncoder) {
        this.pruferEncoder = pruferEncoder;
    }

    public List<Integer> encode(Map< Integer, HuffmanNode> nodeMap) {
        return pruferEncoder.encode(nodeMap);
    }
}
