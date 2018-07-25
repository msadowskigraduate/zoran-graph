package io.zoran.utils.graph.operations.huffman.internal;

import io.zoran.utils.graph.operations.huffman.HuffmanNode;
import io.zoran.utils.graph.operations.huffman.internal.service.HuffmanUtils;
import io.zoran.utils.graph.operations.huffman.internal.service.MapToNodeConverter;

import java.util.HashMap;
import java.util.Map;

import static io.zoran.utils.graph.operations.huffman.internal.service.HuffmanConst.HUFFMAN_INIT_CODE;

/**
 * Created by sadowsm3 on 19.05.2018
 */
public class HuffmanRunner {
    private final HuffmanEncoder huffmanEncoder;
    private final HuffmanTreeBuilder huffmanTreeBuilder;

    private Map<Character, String> encodingMap = new HashMap<>();

    public HuffmanRunner(HuffmanEncoder huffmanEncoder, HuffmanTreeBuilder huffmanTreeBuilder) {
        this.huffmanEncoder = huffmanEncoder;
        this.huffmanTreeBuilder = huffmanTreeBuilder;
    }

    public Map<Integer, HuffmanNode> run(String text) {
        clearResources();
        Map<Integer, HuffmanNode> valuemap = new HashMap<>();
        HuffmanNode huffmanNode = huffmanTreeBuilder.buildTree(
                MapToNodeConverter.convert(huffmanEncoder.encode(text)));
        huffmanTreeBuilder.huffmanCodeGenerator(huffmanNode, encodingMap, HUFFMAN_INIT_CODE);
        HuffmanUtils.assignNodeNumeration(huffmanNode, valuemap);
        return valuemap;
    }

    public Map<Character, String> getEncodingMap() {
        return encodingMap;
    }

    private void clearResources() {
        if(encodingMap.size() > 0) {
            encodingMap = new HashMap<>();
        }
    }
}
