package graph;

import java.util.*;

public class LinkedinShortestPath {

    private final Map<String, List<String>> networkMap;

    public LinkedinShortestPath() {
        networkMap = new HashMap<>();
    }

    public void addConnection(String p1, String p2) {
        addPerson(p1);
        addPerson(p2);
        networkMap.get(p1).add(p2);
        networkMap.get(p2).add(p1);
    }

    private void addPerson(String p) {
        networkMap.putIfAbsent(p, new ArrayList<>());
    }

    public int findShortestDistance(String p1, String p2) {
        if (!networkMap.containsKey(p1) || !networkMap.containsKey(p2)) {
            return -1;
        }
        if (p1.equals(p2)) {
            return 0;
        }
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, Integer> distance = new HashMap<>();
        q.add(p1);
        visited.add(p1);
        distance.put(p1, 0);
        while (!q.isEmpty()) {
            String currP = q.poll();
            for (String conn : networkMap.get(currP)) {
                if (!visited.contains(conn)) {
                    visited.add(conn);
                    int newDistance = distance.get(conn) + 1;
                    distance.put(conn, newDistance);
                    q.add(conn);
                    if (conn.equals(p2)) {
                        return newDistance;
                    }
                }
            }
        }
        return -1;
    }
}
