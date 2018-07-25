package io.zoran.utils.graph.domain;

import io.zoran.utils.graph.domain.internal.Edge;
import io.zoran.utils.graph.domain.internal.Vertex;

import java.util.Collection;

/**
 * @author Michal Sadowski (michal.sadowski@roche.com) on 25.07.2018
 */
public interface Graph {

    boolean isEmpty();

    boolean isFull();

    boolean isPlanar();

    boolean isAcyclic();

    Edge addEdge(Edge edge);

    Vertex addVertex(Vertex v);

    Vertex getVertexById(long id);

    Collection<Vertex> getVerticies();
}
