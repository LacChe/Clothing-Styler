package com.settlementGame.game.AStar;

/**
 * Heuristic functional interface.
 * 
 * @author Leonardo Ono (ono.leo80@gmail.com)
 */
public interface Heuristic<T> {
   
    public double calculate(Node<T> start, Node<T> target, Node<T> current);
    
}
