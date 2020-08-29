package com.loic.optimization.tsp;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class TspGui implements TspPathListener {
  private static final int DIMENSION = 850;

  private final PathCanvas pathCanvas;
  private final JLabel resultLabel;

  private List<Point> points = Collections.emptyList();
  private double minX, minY, ratio;
  private int iteration;
  private long preDrawTimeMs = -1;

  public TspGui() {
    JFrame frame = new JFrame("TSP");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    pathCanvas = new PathCanvas();
    resultLabel = new JLabel();
    resultLabel.setSize(30, DIMENSION);
    frame.add(resultLabel, BorderLayout.NORTH);
    frame.add(pathCanvas, BorderLayout.CENTER);
    frame.pack();
    frame.setSize(DIMENSION, DIMENSION);
    frame.setVisible(true);
  }

  private int convertX(double x) {
    return 5 + (int) ((x - minX) * ratio);
  }

  private int convertY(double y) {
    return 5 + (int) ((y - minY) * ratio);
  }

  @Override
  public void updatePath(List<Point> points) {
    iteration++;
    if (System.currentTimeMillis() - preDrawTimeMs < 1000) {
      return;
    }
    preDrawTimeMs = System.currentTimeMillis();
    this.points = points;
    minX = points.stream().mapToDouble(p -> p.x).min().getAsDouble();
    double maxX = points.stream().mapToDouble(p -> p.x).max().getAsDouble();
    minY = points.stream().mapToDouble(p -> p.y).min().getAsDouble();
    double maxY = points.stream().mapToDouble(p -> p.y).max().getAsDouble();
    double delta = Math.max(maxX - minX, maxY - minY);

    ratio = (DIMENSION - 30) / delta;

    DistanceManager disManager = new DistanceManager(points);
    double distance = 0;
    for (int i = 0; i < points.size(); i++) {
      distance += disManager.distance(i, (i + 1) % points.size());
    }
    distance = Utils.convertPrecision(distance);

    pathCanvas.repaint();
    resultLabel.setText("Iteration : " + iteration + ", distance : " + distance);
  }

  private final class PathCanvas extends Canvas {

    @Override
    public void paint(Graphics g) {
      //draw point
      g.setColor(Color.RED);
      for (Point p : points) {
        g.fillOval(convertX(p.x), convertY(p.y), 3, 3);
      }
      // draw path
      g.setColor(Color.BLUE);
      for (int i = 0; i < points.size(); i++) {
        Point p1 = points.get(i);
        Point p2 = points.get((i + 1) % points.size());
        g.drawLine(convertX(p1.x), convertY(p1.y), convertX(p2.x), convertY(p2.y));
      }
    }
  }
}
