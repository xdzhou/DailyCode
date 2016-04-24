package com.loic.algo.AStart;

public class Node {
    private int x;
    private int y;
    private Node parentNode;
    private int G; // cost from start point
    private int H; // cost to end point (estimed)
    private int F; // cost total

    public Node(int x, int y, Node parentNode) {
        super();
        this.x = x;
        this.y = y;
        this.parentNode = parentNode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getH() {
        return H;
    }

    public void setH(int h) {
        H = h;
    }

    public int getF() {
        return F;
    }

    public void updateF() {
        F = G + H;
    }

    @Override
    public String toString() {
        return "Node (" + x + "," + y + "), G=" + G + ", H=" + H + ", F=" + F;
    }

}
