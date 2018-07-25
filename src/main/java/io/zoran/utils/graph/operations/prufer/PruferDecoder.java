package io.zoran.utils.graph.operations.prufer;


import io.zoran.utils.graph.domain.Graph;
import io.zoran.utils.graph.domain.internal.Edge;
import io.zoran.utils.graph.domain.internal.GraphImpl;
import io.zoran.utils.graph.domain.internal.Vertex;
import io.zoran.utils.graph.operations.prufer.domain.PruferCode;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by sadowsm3 on 21.05.2018
 */
public class PruferDecoder {

    private Predicate<Integer> findNotPresentInOriginalCode(List<String> pruferVertex) {
        return vertex -> !pruferVertex.contains(vertex.toString());
    }

    public String decode(PruferCode pruferCode) {
        Set<Integer> originalVertices = new HashSet<>();
        originalVertices = restoreOriginalVertices(originalVertices, pruferCode);
        Graph graph = new GraphImpl.GraphBuilder().build();
        restoreOriginalTreeFromPruferCode(originalVertices, pruferCode.getIdentifierList(), graph);
        addLiteralLabellingToNodes(graph, pruferCode.getNodeList());
        return originalVertices.toString();
    }

    private Set<Integer> restoreOriginalVertices(Set<Integer> originalVertices, PruferCode pruferCode) {
        for (int i = 1; i <= pruferCode.getIdentifierList().length + 2; i++) {
            originalVertices.add(i);
        }
        return originalVertices;
    }

    private void restoreOriginalTreeFromPruferCode(Set<Integer> originalVertices, String[] pruferCode, Graph graph) {
        List<String> pruferCodeList = new ArrayList<>(Arrays.asList(pruferCode));
        System.out.println(pruferCodeList);
        Iterator iterator = pruferCodeList.listIterator();
        while (iterator.hasNext()) {
            String pruferVertex = iterator.next().toString();
            Optional<Integer> min = originalVertices.stream().filter(findNotPresentInOriginalCode(pruferCodeList)).min(Integer::compareTo);
            if (min.isPresent()) {
                addNodes(graph, pruferVertex, min.get().toString());
                originalVertices.remove(min.get());
                iterator.remove();
            }
        }

        if (originalVertices.size() == 2) {
            Iterator originalIterator = originalVertices.iterator();
            while (originalIterator.hasNext()) {
                Long one = (Long) originalIterator.next();
                Long two = (Long) originalIterator.next();
                addNodes(graph, one, two);
            }
        }
    }

    private void addNodes(Graph graph, Long pruferVertex, Long min) {
        Vertex node1 = graph.getVertexById(pruferVertex);
        Vertex node2 = graph.getVertexById(min);

        Vertex node1 = graph.addVertex(new Vertex.VertexBuilder(pruferVertex).build());
        graph.addVertex(new Vertex.VertexBuilder(min).build());
        graph.addEdge(new Edge.EdgeBuilder(node1, node2).build());
    }

    private void addLiteralLabellingToNodes(Graph graph, String[] labels) {
        Collection<Vertex> nodeSet = graph.getVerticies();
        Iterator nodeIterator = nodeSet.iterator();
        Iterator labelIterator = Arrays.stream(labels).iterator();
        while (nodeIterator.hasNext()) {
            Vertex node = (Vertex) nodeIterator.next();
            if (node.getVertexDegree() < 2 && labelIterator.hasNext()) {
                node.clearAttributes();
                String label = (String) labelIterator.next();
                System.out.println(label);
                node.setAttribute("ui.label", label);
            }
        }
    }
}
