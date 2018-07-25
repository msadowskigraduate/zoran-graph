package io.zoran.utils.graph.operations.prufer.domain;

import java.util.Arrays;

/**
 * Created by sadowsm3 on 21.05.2018
 */
public class PruferCode {
    private String rootNode;
    private String[] nodeList;
    private String[] identifierList;

    private PruferCode(PruferCodeBuilder builder) {
        this.rootNode = builder.rootNode;
        this.nodeList = builder.nodeList;
        this.identifierList = builder.identifierList;
    }

    public String getRootNode() {
        return rootNode;
    }

    public void setRootNode(String rootNode) {
        this.rootNode = rootNode;
    }

    public String[] getNodeList() {
        return nodeList;
    }

    public void setNodeList(String[] nodeList) {
        this.nodeList = nodeList;
    }

    public String[] getIdentifierList() {
        return identifierList;
    }

    public void setIdentifierList(String[] identifierList) {
        this.identifierList = identifierList;
    }

    @Override
    public String toString() {
        return "PruferCode{" +
                "rootNode='" + rootNode + '\'' +
                ", nodeList=" + (nodeList == null ? null : Arrays.asList(nodeList)) +
                ", identifierList=" + (identifierList == null ? null : Arrays.asList(identifierList)) +
                '}';
    }

    public static class PruferCodeBuilder {
        private String rootNode;
        private String[] nodeList;
        private String[] identifierList;

        public PruferCodeBuilder() {
        }

        public PruferCodeBuilder rootNode(String rootNode) {
            this.rootNode = rootNode;
            return this;
        }

        public PruferCodeBuilder nodeList(String[] nodeList) {
            this.nodeList = nodeList;
            return this;
        }

        public PruferCodeBuilder identifier(String[] identifierList) {
            this.identifierList = identifierList;
            return this;
        }

        public PruferCode build() {
            return new PruferCode(this);
        }
    }
}
