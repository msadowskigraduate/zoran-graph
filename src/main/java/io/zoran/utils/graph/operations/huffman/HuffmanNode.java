package io.zoran.utils.graph.operations.huffman;

import java.util.Objects;
import java.util.Random;

/**
 * Created by sadowsm3 on 19.05.2018
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
    private final Character character;
    private final Integer frequency;
    private HuffmanNode leftNode;
    private HuffmanNode rightNode;

    public HuffmanNode(Character character, Integer frequency, HuffmanNode leftNode, HuffmanNode rightNode) {
        this.character = character;
        this.frequency = frequency;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public HuffmanNode(Character character, Integer frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(HuffmanNode node2) {
        return this.getFrequency() - node2.getFrequency();
    }

    public Character getCharacter() {
        return character;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public HuffmanNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(HuffmanNode leftNode) {
        this.leftNode = leftNode;
    }

    public HuffmanNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(HuffmanNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "character=" + character +
                ", frequency=" + frequency +
                ", UUID = " + new Random().nextInt() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HuffmanNode that = (HuffmanNode) o;
        return Objects.equals(character, that.character) &&
                Objects.equals(frequency, that.frequency) &&
                Objects.equals(leftNode, that.leftNode) &&
                Objects.equals(rightNode, that.rightNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(character, frequency, leftNode, rightNode);
    }
}
