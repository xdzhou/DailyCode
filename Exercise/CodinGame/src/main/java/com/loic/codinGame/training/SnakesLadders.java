package com.loic.codinGame.training;

import java.io.Serializable;
import java.util.*;

public class SnakesLadders {
  NodeComparator comparator = new NodeComparator();
  private String[] square;
  private int startP, endP;
  private List<Node> openList = new ArrayList<>();
  private List<Node> closeList = new ArrayList<>();

  public static void main(String[] args) {
    new SnakesLadders().start();
  }

  public void start() {
    Scanner in = new Scanner(System.in, "UTF-8");
    int n = Integer.parseInt(in.nextLine());
    square = new String[n];
    for (int i = 0; i < n; i++) {
      String s = in.nextLine();
      square[i] = s;
      if (s.equals("S")) {
        startP = i;
      }
      if (s.equals("E")) {
        endP = i;
      }
    }
    Node startNode = new Node(startP);
    startNode.G = startNode.F = 0;
    openList.add(startNode);
    avancer();
    in.close();
  }

  private void avancer() {
    Node endNode = null;
    while (openList.size() > 0) {
      Node currentNode = openList.get(0);
      int position = currentNode.position;
      // System.out.println(currentNode);
      if (position == endP) {
        endNode = currentNode;
        break;
      }
      int nextPosition;
      if (square[position].equals("S") || square[position].equals("R")) {
        for (int i = 1; i <= 6; i++) {
          nextPosition = position + i;
          if (nextPosition < 0 || nextPosition >= square.length) {
            continue;
          }
          Node tempNode = new Node(nextPosition);
          if (closeList.contains(tempNode)) {
            continue;
          }
          if (openList.contains(tempNode)) {
            Node node = getNodeEqualTo(tempNode);
            int newG = currentNode.G + 1;
            if (newG < node.G) {
              node.G = newG;
              node.updateF();
            }
          } else {
            tempNode.G = currentNode.G + 1;
            tempNode.updateF();
            openList.add(tempNode);
          }
        }
      } else {
        int step = Integer.parseInt(square[position]);
        nextPosition = position + step;
        if (nextPosition >= 0 || nextPosition < square.length) {
          Node tempNode = new Node(nextPosition);
          if (openList.contains(tempNode)) {
            Node node = getNodeEqualTo(tempNode);
            int newG = currentNode.G + 1;
            if (newG < node.G) {
              node.G = newG;
              node.updateF();
            }
          } else {
            if (!closeList.contains(tempNode)) {
              tempNode.G = currentNode.G + 1;
              tempNode.updateF();
              openList.add(tempNode);
            }
          }
        }
      }
      openList.remove(0);
      closeList.add(currentNode);
      Collections.sort(openList, comparator);
    }
    if (endNode == null) {
      System.out.println("impossible");
    } else {
      System.out.println(endNode.G);
    }
  }

  private Node getNodeEqualTo(Node node) {
    for (Node n : openList) {
      if (n.equals(node)) {
        return n;
      }
    }
    return null;
  }

  private static class NodeComparator implements Comparator<Node>, Serializable {
    public int compare(Node o1, Node o2) {
      return o1.F - o2.F;
    }
  }

  private class Node {
    int position;
    int G; // cost from start point
    int H; // cost to end point (estimed)
    int F; // cost total

    Node(int position) {
      this.position = position;
      H = (Math.abs(endP - position) + 5) / 6;
    }

    @Override
    public String toString() {
      return "Node [position=" + position + ", G=" + G + ", H=" + H + ", F=" + F + "]";
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + getOuterType().hashCode();
      result = prime * result + position;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      Node other = (Node) obj;
      if (!getOuterType().equals(other.getOuterType())) {
        return false;
      }
      if (position != other.position) {
        return false;
      }
      return true;
    }

    private SnakesLadders getOuterType() {
      return SnakesLadders.this;
    }

    void updateF() {
      F = G + H;
    }
  }
}
