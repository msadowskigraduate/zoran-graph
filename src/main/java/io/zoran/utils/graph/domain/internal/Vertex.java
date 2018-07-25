package io.zoran.utils.graph.domain.internal;

import io.zoran.utils.graph.domain.Node;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Created by sadowsm3 on 03.06.2018
 */
public class Vertex implements Node {
    private Long vertexId;
    private String label;
    private LinkedList<Edge> edgeList;
    private LinkedList<Vertex> adjecencyList;

    private Vertex (VertexBuilder builder) {
        this.vertexId = builder.vertexId;
        this.label = builder.label;
        edgeList = new LinkedList<>();
        adjecencyList = new LinkedList<>();
    }

    void addAdjecencyVertex(Edge edge, Vertex vertex) {
        edgeList.add(edge);
        adjecencyList.add(vertex);
    }

    public Long getVertexId() {
        return vertexId;
    }

    public LinkedList<Edge> getEdgeList() {
        return edgeList;
    }

    public LinkedList<Vertex> getAdjecencyList() {
        return adjecencyList;
    }

    public Integer getVertexDegree() {
        return adjecencyList.size();
    }

    //TODO handle the same id but different lists
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(vertexId, vertex.vertexId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexId);
    }

    public String getLabel() {
        return label;
    }

    public static class VertexBuilder {
        private final Long vertexId;
        private String label;

        public VertexBuilder(Long vertexId) {
            this.vertexId = vertexId;
        }

        public VertexBuilder label(String label) {
            this.label = label;
            return this;
        }

        public Vertex build() {
            return  new Vertex(this);
        }
    }

}
