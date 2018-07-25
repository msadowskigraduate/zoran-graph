package io.zoran.utils.graph.domain.internal;

import io.zoran.utils.graph.domain.Graph;

import java.util.*;

/**
 * Created by sadowsm3 on 02.06.2018
 * Base class for graph data structures.
 * GraphImpl is an ordered pair of: set of edges and set of vertices
 */
public class GraphImpl implements Graph {
    private Set<Edge> edgeList;
    private Set<Vertex> vertexList;

    private GraphImpl(GraphBuilder builder) {
        if (builder.edges == null) {
            this.edgeList = new HashSet<>();
        } else {
            this.edgeList = builder.edges;
        }

        if (builder.vertices == null) {
            this.vertexList = new HashSet<>();
        } else {
            this.vertexList = builder.vertices;
        }
    }

    /**
     * Add vertex to this Graph instance
     * @param vertex
     * @return true if vertex has been added, false if this vertex already exis
     */
    public Vertex addVertex(Vertex vertex) {
        if (!vertexList.contains(vertex)) {
            vertexList.add(vertex);
        }
        else {
            vertex = getVertexById(vertex.getVertexId());
        }
        return vertex;
    }

    @Override
    public boolean isEmpty() {
        return vertexList.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isPlanar() {
        return false;
    }

    @Override
    public boolean isAcyclic() {
        return false;
    }

    public Edge addEdge(Edge edge) {
        if (edge != null) {
            this.edgeList.add(edge);
        }
        return edge;
    }

    public Vertex getVertexById(long id) {
        return vertexList.stream().filter(x -> x.getVertexId() == id).findFirst().orElse(null);
    }

    public Collection<Vertex> getVerticies() {
        return vertexList;
    }

    public static class GraphBuilder {
        private Set<Vertex> vertices;
        private Set<Edge> edges;

        public GraphBuilder() {
        }

        public GraphBuilder vertices(Set<Vertex> vertices) {
            this.vertices = vertices;
            return this;
        }

        public GraphBuilder edges(Set<Edge> edges) {
            this.edges = edges;
            return this;
        }

        public GraphImpl build() {
            return new GraphImpl(this);
        }
    }
}
