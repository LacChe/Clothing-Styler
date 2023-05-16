package com.settlementGame.game.AStar;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

import com.settlementGame.game.gameObject.Walkable;

/**
 * A* Path Finding Algorithm Test.
 * 
 * @author Leonardo Ono (ono.leo80@gmail.com);
 */
public class AStar {

    private static final int TILE_SIZE = 1;
    
    private static final int LENGTH = 90;
    
    private static Node[][] grid;
    
    private static Graph<Point> graph;
    
    private static List<Node<Point>> path;

    private static final boolean diagonalEnabled = false;
    
    public static void init() {
        
        path = new ArrayList<>();
        grid = new Node[LENGTH][LENGTH];
        
        graph = new Graph<>((start, target, current) -> {
            // --- implement your heuristic here ---
            
            // heuristic = manhattan distance
            //int dx = Math.abs(target.getObj().x - current.getObj().x);
            //int dy = Math.abs(target.getObj().y - current.getObj().y);
            //return dx + dy;

            // heuristic = linear distance
            int dx = target.getObj().x - current.getObj().x;
            int dy = target.getObj().y - current.getObj().y;
            return Math.sqrt(dx * dx + dy * dy);
            
            // heuristic = 0 -> equivalent to Dijkstra
            //return 0;
        });
        
        createGrid();
    }

    private static void createGrid() {
        
        for (int y = 0; y < LENGTH; y++) {
            for (int x = 0; x < LENGTH; x++) {
                int nx = x * TILE_SIZE;
                int ny = y * TILE_SIZE;
                Node node = new Node(new Point(nx, ny));
                graph.addNode(node);
                grid[y][x] = node;
            }
        }
        
        // link all nodes
        double diagonalG = Math.sqrt(TILE_SIZE * TILE_SIZE
            + TILE_SIZE * TILE_SIZE);
        
        for (int y = 0; y < LENGTH - 1; y++) {
            for (int x = 0; x < LENGTH; x++) {
                // vertical '|'
                Node top = grid[y][x];
                Node bottom = grid[y + 1][x];
                graph.link(top, bottom, TILE_SIZE);
                
                // diagonals 'X'
                if (x < LENGTH - 1 && diagonalEnabled) {

                    // diagonal '\'
                    top = grid[y][x];
                    bottom = grid[y + 1][x + 1];
                    graph.link(top, bottom, diagonalG);

                    // diagonal '/'
                    top = grid[y][x + 1];
                    bottom = grid[y + 1][x];
                    graph.link(top, bottom, diagonalG);
                }
            }
        }

        for (int x = 0; x < LENGTH - 1; x++) {
            for (int y = 0; y < LENGTH; y++) {
                // horizontal '-'
                Node left = grid[y][x];
                Node right = grid[y][x + 1];
                graph.link(left, right, TILE_SIZE);
            }
        }
    }

    public static List<Node<Point>> getPathNextTo(int srcX, int srcY, int dstX, int dstY, Walkable srcW) {
        Node<Point> src = null, dst = null;
        for(Node<Point> n : graph.getNodes()){
            if(n.getObj().x == srcX && n.getObj().y == srcY) src = n;
            if(n.getObj().x == dstX && n.getObj().y == dstY) dst = n;
        }
        if(src == null || dst == null) return null;
        path.clear();
        graph.findPathNextTo(src, dst, path, srcW);
        // null path
        if(path.size() == 0
                && !((srcX == dstX) && (srcY == dstY + 1 || srcY == dstY - 1) || (srcY == dstY) && (srcX == dstX + 1 || srcX == dstX - 1))) return null;
        return clone(path);
    }

    public static List<Node<Point>> getPath(int srcX, int srcY, int dstX, int dstY, Walkable srcW) {
        Node<Point> src = null, dst = null;
        for(Node<Point> n : graph.getNodes()){
            if(n.getObj().x == srcX && n.getObj().y == srcY) src = n;
            if(n.getObj().x == dstX && n.getObj().y == dstY) dst = n;
        }
        if(src == null || dst == null) return null;
        path.clear();
        graph.findPath(src, dst, path, srcW);
        // null path
        if(path.size() == 0
                && !((srcX == dstX) && (srcY == dstY + 1 || srcY == dstY - 1) || (srcY == dstY) && (srcX == dstX + 1 || srcX == dstX - 1))) return null;
        return clone(path);
    }

    private static List<Node<Point>> clone(List<Node<Point>> old){
        List<Node<Point>> newList = new ArrayList<>();
        for(Node<Point> n : old){
            Node<Point> newNode = new Node<Point>(new Point(n.getObj().x, n.getObj().y));
            newList.add(newNode);
        }
        return newList;
    }
    
}