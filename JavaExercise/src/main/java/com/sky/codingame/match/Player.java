package com.sky.codingame.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Player {
    private List<Ship> myShips = new ArrayList<>();
    private List<Ship> otherShips = new ArrayList<>();
    private List<Barrel> barrels = new ArrayList<>();

    public static void main(String args[]) {
        new Player().start();
    }

    public void start() {
        Scanner in = new Scanner(System.in);

        // game loop
        while (true) {
            myShips.clear();
            otherShips.clear();
            barrels.clear();

            int myShipCount = in.nextInt(); // the number of remaining ships
            int entityCount = in.nextInt(); // the number of entities (e.g. ships, mines or cannonballs)
            for (int i = 0; i < entityCount; i++) {
                int entityId = in.nextInt();
                String entityType = in.next();
                int x = in.nextInt();
                int y = in.nextInt();
                int arg1 = in.nextInt();
                int arg2 = in.nextInt();
                int arg3 = in.nextInt();
                int arg4 = in.nextInt();
                if ("SHIP".equals(entityType)) {
                    Ship ship = new Ship(entityId, x, y);
                    ship.direction = arg1;
                    ship.speed = arg2;
                    ship.rhumCount = arg3;
                    if (arg4 == 1) {
                        myShips.add(ship);
                    } else {
                        otherShips.add(ship);
                    }
                } else {
                    Barrel barrel = new Barrel(entityId, x, y);
                    barrel.quantity = arg1;
                    barrels.add(barrel);
                }
            }
            for (int i = 0; i < myShipCount; i++) {

                Collections.sort(barrels, new EntityComparator(myShips.get(i)));

                Coor coor = barrels.get(0).coor;

                System.out.println("MOVE " + coor.x + " " + coor.y); // Any valid action, such as "WAIT" or "MOVE x y"
            }
        }
    }

    private static final class Barrel extends Entity {
        private int quantity;

        public Barrel(int id, int x, int y) {
            super(id, x, y);
        }
    }

    private static final class Ship extends Entity {
        private int direction;
        private int speed;
        private int rhumCount;

        private Ship(int id, int x, int y) {
            super(id, x, y);
        }
    }

    private static class Entity {
        final int id;
        Coor coor;

        private Entity(int id, int x, int y) {
            this.id = id;
            coor = new Coor(x, y);
        }

        private float dis(Entity entity) {
            return coor.dis(entity.coor.x, entity.coor.y);
        }
    }

    private static final class Coor {
        private final int x, y;

        private Coor(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public float dis(float dx, float dy) {
            return (float) Math.sqrt((x - dx) * (x - dx) + (y - dy) * (y - dy));
        }
    }

    private static class EntityComparator implements Comparator<Entity> {
        private final Entity mFrom;

        public EntityComparator(Entity e) {
            mFrom = e;
        }

        @Override
        public int compare(Entity o1, Entity o2) {
            return Float.compare(mFrom.dis(o1), mFrom.dis(o2));
        }
    }
}
