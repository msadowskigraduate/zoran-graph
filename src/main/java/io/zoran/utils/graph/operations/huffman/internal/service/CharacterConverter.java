package io.zoran.utils.graph.operations.huffman.internal.service;

/**
 * Created by sadowsm3 on 19.05.2018
 */
public class CharacterConverter {

    public static Character[] convert(String string) {
        return  string.trim().chars().mapToObj(c -> (char)c).toArray(Character[]::new);
    }
}
