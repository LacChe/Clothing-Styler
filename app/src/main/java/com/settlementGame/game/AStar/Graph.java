package com.settlementGame.game.AStar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import static com.settlementGame.game.AStar.Node.State.*;

import android.graphics.Point;
import android.os.Build;

import com.settlementGame.game.gameObject.GameObject;
import com.settlementGame.game.gameObject.Walkable;
import com.settlementGame.game.terrain.BiomeInfo;
import com.settlementGame.game.terrain.TerrainInfo;

/**
 * Graph class.
 * 
 * @author Leonardo Ono (ono.leo80@gmail.com)
 */
public class Graph<T> {

    private final List<Node<T>> nodes = new ArrayList<>();
    private final Heuristic<T> heuristic;
    
    public Graph(Heuristic<T> heuristic) {
        this.heuristic = heuristic;
    }

    public void addNode(Node n) {
        nodes.add(n);
    }

    public List<Node<T>> getNodes() {
        return nodes;
    }

    public Heuristic<T> getHeuristic() {
        return heuristic;
    }
    
    public void link(Node a, Node b, double cost) {
        Edge edge = new Edge(cost, a, b);
        a.addEdge(edge);
        b.addEdge(edge);
    }
    
    public void findPath(Node<T> start, Node<T> target, List<Node<T>> path, Walkable src) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            nodes.forEach(node -> {
                node.setState(UNVISITED);
                node.setBackPathNode(null);
                node.setG(Double.MAX_VALUE);
            });
        }

        start.setG(0);
        start.setH(heuristic.calculate(start, target, start));
        
        PriorityQueue<Node<T>> activeNodes = new PriorityQueue<>();
        activeNodes.add(start);

        while (!activeNodes.isEmpty()) {
            Node<T> currentNode = activeNodes.poll();
            currentNode.setState(CLOSED);

            // target node found !
            if (currentNode == target) {
                path.clear();
                target.retrievePath(path);
                // path.remove(0);
                return;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                currentNode.getEdges().forEach((edge) -> {
                    Node<T> neighborNode = edge.getOppositeNode(currentNode);
                    double neighborG = currentNode.getG() + edge.getG();
                    if (traversable(neighborNode, src) && neighborG < neighborNode.getG()) {
                        neighborNode.setBackPathNode(currentNode);
                        neighborNode.setG(neighborG);
                        double h = heuristic.calculate(start, target, neighborNode);
                        neighborNode.setH(h);
                        if (!activeNodes.contains(neighborNode)) {
                            activeNodes.add(neighborNode);
                            neighborNode.setState(OPEN);
                        }
                    }
                });
            }
        }
    }

    public void findPathNextTo(Node<T> start, Node<T> target, List<Node<T>> path, Walkable src) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            nodes.forEach(node -> {
                node.setState(UNVISITED);
                node.setBackPathNode(null);
                node.setG(Double.MAX_VALUE);
            });
        }

        start.setG(0);
        start.setH(heuristic.calculate(start, target, start));

        PriorityQueue<Node<T>> activeNodes = new PriorityQueue<>();
        activeNodes.add(start);

        while (!activeNodes.isEmpty()) {
            Node<T> currentNode = activeNodes.poll();
            currentNode.setState(CLOSED);

            // target node found !
            if ((Math.abs(((Point)currentNode.getObj()).x - ((Point)target.getObj()).x) <= 1 && ((Point)currentNode.getObj()).y - ((Point)target.getObj()).y == 0)
                    || (Math.abs(((Point)currentNode.getObj()).y - ((Point)target.getObj()).y) <= 1  && ((Point)currentNode.getObj()).x - ((Point)target.getObj()).x == 0)) {
                path.clear();
                currentNode.retrievePath(path);
                // path.remove(0);
                return;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                currentNode.getEdges().forEach((edge) -> {
                    Node<T> neighborNode = edge.getOppositeNode(currentNode);
                    double neighborG = currentNode.getG() + edge.getG();
                    if (traversable(neighborNode, src) && neighborG < neighborNode.getG()) {
                        neighborNode.setBackPathNode(currentNode);
                        neighborNode.setG(neighborG);
                        double h = heuristic.calculate(start, target, neighborNode);
                        neighborNode.setH(h);
                        if (!activeNodes.contains(neighborNode)) {
                            activeNodes.add(neighborNode);
                            neighborNode.setState(OPEN);
                        }
                    }
                });
            }
        }
    }

    private boolean traversable(Node<T> node, Walkable src){
        // todo
        int x = ((Point)node.getObj()).x;
        int y = ((Point)node.getObj()).y;
        if(!inWorld(x, y)) return false;
        if(GameObject.gameObjects[x][y] != null && !GameObject.gameObjects[x][y].isPassable()) return false;
        if(TerrainInfo.tilesGrid[x][y] == null || !TerrainInfo.tilesGrid[x][y].isPassable()) return false;
        for(Walkable w : Walkable.homeCitizens){
            if(!w.name.equals(src.name) && (int)w.getX() == x && (int)w.getY() == y) return false;
        }
        return true;
    }

    protected static boolean inWorld(int x, int y){
        if(x < 0 || x >= GameObject.gameObjects.length
                || y < 0 || y >= GameObject.gameObjects.length
                || BiomeInfo.biomeGrid[x / TerrainInfo.tilesGridSize][y / TerrainInfo.tilesGridSize] == BiomeInfo.BiomeType.VOID)
            return false;
        return true;
    }
    
}
