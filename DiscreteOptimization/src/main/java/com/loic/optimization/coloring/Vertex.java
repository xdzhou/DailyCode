package com.loic.optimization.coloring;

import java.util.HashSet;
import java.util.Set;

public class Vertex {
  private final int id;
  private final Set<Vertex> neighbours = new HashSet<>();

  public Vertex(int id) {
    this.id = id;
  }

  public int id() {
    return id;
  }

  public void addNeighbours(Vertex n) {
    neighbours.add(n);
  }

  public Set<Vertex> neighbours() {
    return neighbours;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Vertex vertex = (Vertex) o;

    return id == vertex.id;
  }

  @Override
  public int hashCode() {
    return id;
  }
}
