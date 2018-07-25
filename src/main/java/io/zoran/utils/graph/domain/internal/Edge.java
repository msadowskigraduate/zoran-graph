package io.zoran.utils.graph.domain.internal;

import java.util.Objects;

/**
 * Created by sadowsm3 on 03.06.2018
 */
public class Edge {
    private final Vertex v1;
    private final Vertex v2;
    private Integer edgeLength;
    private String edgeLabel;

    private Edge(EdgeBuilder edgeBuilder) {
        this.v1 = edgeBuilder.v1;
        this.v2 = edgeBuilder.v2;
        this.edgeLength = edgeBuilder.edgeLength;
        this.edgeLabel = edgeBuilder.edgeLabel;
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public Vertex getOutboundVertex() {
        return getV1();
    }

    public Vertex getInboundVertex() {
        return getV2();
    }

    public String getEdgeLabel() {
        return edgeLabel;
    }

    public Integer getEdgeLength() {
        return edgeLength;
    }

    @Override
    public String toString() {
        if(v1.getVertexId() < v2.getVertexId()) {
            return v1.getVertexId() + " " + v2.getVertexId();
        }
        return v2.getVertexId() + " " + v1.getVertexId();
    }

    //Allow support for multigrafs
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(v1, edge.v1) &&
                Objects.equals(v2, edge.v2) &&
                Objects.equals(edgeLength, edge.edgeLength) &&
                Objects.equals(edgeLabel, edge.edgeLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1, v2, edgeLength, edgeLabel);
    }

    public static class EdgeBuilder {
        private final Vertex v1;
        private final Vertex v2;
        private Integer edgeLength;
        private String edgeLabel;

        public EdgeBuilder(Vertex v1, Vertex v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        public EdgeBuilder DirectedEdgeBuilder (Vertex outboundVertex, Vertex inboundVertex) {
            return new EdgeBuilder(outboundVertex, inboundVertex);
        }

        public EdgeBuilder edgeLength(Integer edgeLength) {
            this.edgeLength = edgeLength;
            return this;
        }

        public EdgeBuilder addLabel(String label) {
            this.edgeLabel = label;
            return this;
        }

        public Edge build() {
            return new Edge(this);
        }
    }
}
