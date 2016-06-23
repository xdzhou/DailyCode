package com.sky.codingame.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Ragnarok {
    List<Position> gaintList = new ArrayList<>();

    public static void main(String[] args) {
        new Ragnarok().start();
    }

    public void start() {
        Scanner in = new Scanner(System.in);
        String[] p = in.nextLine().split(" ");
        int currentX = Integer.parseInt(p[0]);
        int currentY = Integer.parseInt(p[1]);
        PositionComparator pComparator = new PositionComparator();

        while (true) {
            String[] indi = in.nextLine().split(" ");
            int H = Integer.parseInt(indi[0]);
            int N = Integer.parseInt(indi[1]);

            addGaintPosition(in, N, currentX, currentY);
            Collections.sort(gaintList, pComparator);
            Position centreP;
            if (N > 3) {
                centreP = getCircumcenter();
            } else {
                centreP = nearestGaintCentre(N, currentX, currentY);
            }
            int LX = centreP.x;
            int LY = centreP.y;

            Position nearestP = gaintList.get(0);
            System.err.println("P =" + nearestP.x + " " + nearestP.y);
            // if(getDistance(currentX, currentY, nearestP.x, nearestP.y)< 2)
            if (Math.abs(nearestP.x - currentX) <= 2 && Math.abs(nearestP.y - currentY) <= 2) {
                System.out.println("STRIKE");
            } else {
                if (LX > currentX && LY > currentY) {
                    currentX++;
                    currentY++;
                    System.out.println("NE");
                } else if (LX == currentX && LY > currentY) {
                    currentY++;
                    System.out.println("N");
                } else if (LX == currentX && LY < currentY) {
                    currentY--;
                    System.out.println("S");
                } else if (LX > currentX && LY == currentY) {
                    currentX++;
                    System.out.println("E");
                } else if (LX > currentX && LY < currentY) {
                    currentX++;
                    currentY--;
                    System.out.println("SE");
                } else if (LX < currentX && LY < currentY) {
                    currentX--;
                    currentY--;
                    System.out.println("SW");
                } else if (LX < currentX && LY == currentY) {
                    currentX--;
                    System.out.println("W");
                } else {
                    currentX--;
                    currentY++;
                    System.out.println("NW");
                }
            }

        }
    }

    private void addGaintPosition(Scanner in, int N, int currentX, int currentY) {
        gaintList.clear();
        for (int i = 0; i < N; i++) {
            String[] indi = in.nextLine().split(" ");
            Position p = new Position();
            p.x = Integer.parseInt(indi[0]);
            p.y = Integer.parseInt(indi[1]);
            p.length = (currentX - p.x) * (currentX - p.x) + (currentY - p.y) * (currentY - p.y);
            gaintList.add(p);
        }
    }

    private Position nearestGaintCentre(int num, int currentX, int currentY) {
        int sommeX = currentX;
        int sommeY = currentY;
        for (int i = 0; i < num; i++) {
            sommeX += gaintList.get(i).x;
            sommeY += gaintList.get(i).y;
        }
        num++;
        Position centre = new Position();
        centre.x = sommeX / num;
        if (sommeX % num > num / 2)
            centre.x += 1;
        centre.y = sommeY / num;
        if (sommeY % num > num / 2)
            centre.y += 1;
        return centre;
    }

    private Position getCircumcenter() {
        Position p1 = gaintList.get(0);
        Position p2 = gaintList.get(1);
        Position p3 = gaintList.get(2);
        int a = 2 * (p2.x - p1.x);
        int b = 2 * (p2.y - p1.y);
        int dp1 = p1.x * p1.x + p1.y * p1.y;
        int dp2 = p2.x * p2.x + p2.y * p2.y;
        int dp3 = p3.x * p3.x + p3.y * p3.y;
        int c = dp2 - dp1;
        int d = 2 * (p3.x - p2.x);
        int e = 2 * (p3.y - p2.y);
        int f = dp3 - dp2;
        Position p = new Position();
        p.x = (b * f - e * c) / (b * d - e * a);
        p.y = (d * c - a * f) / (b * d - e * a);
        return p;
    }

    private double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    class Position {
        int x;
        int y;
        int length = 0;
    }

    private class PositionComparator implements Comparator<Position> {
        @Override
        public int compare(Position o1, Position o2) {
            return o1.length - o2.length;
        }
    }
}