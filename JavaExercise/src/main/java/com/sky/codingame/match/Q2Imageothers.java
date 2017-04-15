package com.sky.codingame.match;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Read inputs from System.in, Write outputs to System.out.
class Q2Imageothers {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in, "UTF-8");
        int W = in.nextInt();
        int H = in.nextInt();
        boolean[][] image = buildImage(in, W, H);
        // System.out.println(printNotes(W, H, image));
        List<Integer> portee = determinerPortee(W, H, image);

        System.out.println(computeNotes(W, H, image, portee).trim());
        in.close();
    }

    private static boolean[][] buildImage(Scanner in, int W, int H) {
        in.nextLine();
        String line = in.nextLine();
        String[] pixels = line.split(" ");
        int index = 0;
        String current = "";
        int length = 0;
        boolean[][] image = new boolean[W][H];

        for (int j = 0; j < H; j++) {
            for (int i = 0; i < W; i++) {
                if (length == 0) {
                    current = pixels[index++];
                    length = Integer.parseInt(pixels[index++]);
                }
                if ("W".equals(current)) {
                    image[i][j] = false;
                } else {
                    image[i][j] = true;
                }
                length--;
            }
        }
        return image;
    }

    private static String computeNotes(int W, int H, boolean[][] image, List<Integer> portee) {
        StringBuilder sb = new StringBuilder();
        int curseur = 0;
        curseur = findNote(W, H, image, portee, curseur);
        while (curseur < W) {
            int begin = curseur;
            List<Integer> note = getNote(W, H, image, portee, curseur);
            curseur = findBeforeEndNote(W, H, image, portee, curseur);
            List<Integer> noteEnd = getNote(W, H, image, portee, curseur);
            sb.append(getPositionAsString(W, H, note.size() < noteEnd.size() ? note : noteEnd, portee));
            sb.append(isBlack(W, H, image, note.size() < noteEnd.size() ? note : noteEnd, begin, curseur) ? "Q" : "H");
            curseur++; // findEndNote(W, H, image, portee, curseur);
            curseur = findNote(W, H, image, portee, curseur);

            sb.append(' ');
        }
        return sb.toString();
    }

    private static Boolean isBlack(int W, int H, boolean[][] image, List<Integer> note, int begin, int curseur) {
        int milieuW = (begin + curseur) >>> 1;
        int milieuH = (note.get(0) + note.get(note.size() - 1)) >>> 1;
        return image[milieuW][milieuH];
    }

    private static String getPositionAsString(int W, int H, List<Integer> note, List<Integer> portee) {
        int position = getPosition(W, H, note, portee);
        if (position == 0 || position == 7) {
            return "G";
        } else if (position == 1 || position == 8) {
            return "F";
        } else if (position == 2 || position == 9) {
            return "E";
        } else if (position == 3 || position == 10) {
            return "D";
        } else if (position == 4 || position == 11) {
            return "C";
        } else if (position == 5 || position == 12) {
            return "B";
        } else if (position == 6 || position == 13) {
            return "A";
        }
        return "";
    }

    private static int getPosition(int w, int h, List<Integer> note, List<Integer> portee) {
        int firstNote1 = note.get(0);
        int pos1 = -1; // -1 -> unset
        int firstNote2 = note.get(note.size() - 1);
        int pos2 = -1; // -1 -> unset
        for (int i = 0; i < portee.size(); i++) {
            pos1 = setPosition(firstNote1, pos1, i, portee.get(i));
            pos2 = setPosition(firstNote2, pos2, i, portee.get(i));
        }
        if (pos1 == -1) {
            pos1 = portee.size();
        }
        if (pos2 == -1) {
            pos2 = portee.size();
        }

        return (pos1 + pos2) / (portee.size() / 6);
    }

    private static int setPosition(int firstNote, int pos, int i, int intPortee) {
        if (pos == -1 && intPortee > firstNote) {
            return i;
        }
        return pos;
    }

    private static int findNote(int W, int H, boolean[][] image, List<Integer> portee, int curseur) {
        while (curseur < W) {
            for (int i = 0; i < H; i++) {
                if (image[curseur][i] && !portee.contains(i)) {
                    return curseur;
                }
            }
            curseur++;
        }
        return curseur;
    }

    private static int findBeforeEndNote(int W, int H, boolean[][] image, List<Integer> portee, int curseur) {
        while (curseur < W) {
            boolean hasNote = false;
            for (int i = 0; i < H; i++) {
                if (image[curseur][i] && !portee.contains(i)) {
                    hasNote = true;
                }
            }
            if (!hasNote) {
                return curseur - 1;
            }
            curseur++;
        }
        return -1;
    }

    private static List<Integer> getNote(int W, int H, boolean[][] image, List<Integer> portee, int curseur) {
        List<Integer> note = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            if (image[curseur][i] && !portee.contains(i)) {
                note.add(i);
            }
        }
        return note;
    }

    private static List<Integer> determinerPortee(int W, int H, boolean[][] image) {
        List<Integer> portee = new ArrayList<>();
        int curseur = 0;
        while (portee.isEmpty()) {
            for (int i = 0; i < H; i++) {
                if (image[curseur][i]) {
                    portee.add(i);
                }
            }
            curseur++;
        }
        // Ajout de la portee "virtuelle" du bas
        int size = portee.size();
        int ecart = portee.get(size / 5) - portee.get((size / 5) - 1);
        int last = portee.get(size - 1);
        for (int i = 0; i < size / 5; i++) {
            portee.add(last + ecart + i);
        }
        return portee;
    }
}