package graph.dfs;

import java.util.*;

public class NetworkPathWithinGivenLatency implements Iterator<List<Integer>> {

    static class Edge {
        int to;
        int latency;

        public Edge(int to, int latency) {
            this.to = to;
            this.latency = latency;
        }
    }

    private Iterator<List<Integer>> pathIterator;

    public void findNetworkPathWithinGivenLatency(Map<Integer, List<Edge>> graph, int source, int destination, int latencyLimit) {
        // Sort the edges to guarantee strict lexicographical branching
        Map<Integer, List<Edge>> sortedGraph = new HashMap<>();
        for (Map.Entry<Integer, List<Edge>> entry : graph.entrySet()) {
            List<Edge> edgesCopy = new ArrayList<>(entry.getValue());
            //edgesCopy.add(Comparator.comparingInt(e -> e.to));
            sortedGraph.put(entry.getKey(), edgesCopy);
        }

        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currPath = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        currPath.add(source);
        visited.add(destination);

        this.dfs(source, destination, 0, latencyLimit, sortedGraph, currPath, allPaths, visited);
        this.pathIterator = allPaths.iterator();
    }

    private void dfs(int current, int destination, int currentLatency, int latencyLimit, Map<Integer, List<Edge>> graph,
                     List<Integer> currPath, List<List<Integer>> allPaths, Set<Integer> visited) {
        if (currentLatency > latencyLimit) {
            return;
        }
        if (current == destination) {
            allPaths.add(new ArrayList<>(currPath));
            return;
        }
        if (!graph.containsKey(current)) {
            return;
        }

        for (Edge edge : graph.get(current)) {
            int neighbor = edge.to;

            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                currPath.add(neighbor);
                // explore neighbors
                this.dfs(neighbor, destination, currentLatency + edge.latency, latencyLimit, graph, currPath, allPaths, visited);
                // unselect (backtrack)
                currPath.remove(currPath.size() - 1);
                visited.remove(neighbor);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return pathIterator.hasNext();
    }

    @Override
    public List<Integer> next() {
        return pathIterator.next();
    }

}
