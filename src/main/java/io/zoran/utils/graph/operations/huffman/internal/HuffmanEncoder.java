package io.zoran.utils.graph.operations.huffman.internal;

import io.zoran.utils.graph.operations.huffman.internal.service.CharacterConverter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sadowsm3 on 19.05.2018
 */
public class HuffmanEncoder {

    public Map<Character, Integer> encode(String textToEncode) {
       return encodeText(CharacterConverter.convert(textToEncode));
    }

    public Map<Character, Integer> encode(Character[] charsToEncode) {
        return encodeText(charsToEncode);
    }

    private Map<Character, Integer> encodeText(Character[] charsToEncode) {
        Map<Character, Integer> occuranceFreqMap = new HashMap<>();
        return createFrequenceMap(occuranceFreqMap, charsToEncode);
    }

    private Map<Character, Integer> createFrequenceMap(Map<Character, Integer> occuranceFreqMap, Character[] charsToEncode) {
        Arrays.stream(charsToEncode).forEach( character -> {
            if(occuranceFreqMap.containsKey(character)) {
                occuranceFreqMap.compute(character, (x,y) -> y +1 );
            }
            else {
                occuranceFreqMap.put(character, 1);
            }
        });
        return occuranceFreqMap;
    }
}
