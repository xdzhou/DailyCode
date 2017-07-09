package com.loic.codejam.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ChargingChaos {

    public static void main(String[] args) {
        new ChargingChaos().start();
    }

    public void start() {
        Scanner in = new Scanner(System.in, "UTF-8");
        int nbCase = in.nextInt();
        BREAKFLAG:
        for (int i = 0; i < nbCase; i++) {
            int nbSwitch = 0;
            int N = in.nextInt();
            int L = in.nextInt();
            List<Integer> electric = new ArrayList<>(N);
            List<Integer> device = new ArrayList<>(N);
            in.nextLine();
            String[] elcStr = in.nextLine().split(" ");
            for (int j = 0; j < N; j++) {
                electric.add(Integer.valueOf(elcStr[j], 2));
            }
            String[] devStr = in.nextLine().split(" ");
            for (int j = 0; j < N; j++) {
                device.add(Integer.valueOf(devStr[j], 2));
            }
            int[] elcNBone = getNbOne(electric, L);
            int[] devNBone = getNbOne(device, L);

            List<Integer> specialIndex = new ArrayList<>();
            for (int j = 0; j < L; j++) {
                if (elcNBone[j] == devNBone[j]) {
                    if (elcNBone[j] == N / 2 && N % 2 == 0) {
                        specialIndex.add(j); // not sure
                    }
                } else if (elcNBone[j] + devNBone[j] == N) {
                    bitSwitch(electric, L - 1 - j);
                    nbSwitch++;
                } else {
                    System.out.println("Case #" + (i + 1) + ": NOT POSSIBLE 1");
                    continue BREAKFLAG;
                }
            }
            showList(specialIndex);
            int min = getMinSwitch(device, electric, specialIndex, L);
            if (min == -1) {
                System.out.println("Case #" + (i + 1) + ": NOT POSSIBLE 2");
            } else {
                System.out.println("Case #" + (i + 1) + ": " + (nbSwitch + min));
            }
        }
        in.close();
    }

    private void showList(List<Integer> list) {
        System.out.print("Arraylist: ");
        for (int k = 0; k < list.size(); k++) {
            System.out.print(list.get(k) + " ");
        }
        System.out.println();
    }

    private int getMinSwitch(List<Integer> device, List<Integer> electric, List<Integer> specialIndex, int L) {
        if (isListEqual(electric, device)) {
            return 0;
        }
        if (!isBitEqual(device, electric, specialIndex, L)) {
            return -1;
        }

        List<Integer> copyelectric = new ArrayList<>(electric.size());
        copyelectric.addAll(electric);

        int index = specialIndex.remove(0);
        int min1 = getMinSwitch(device, electric, specialIndex, L);
        bitSwitch(copyelectric, L - 1 - index);
        int min2 = getMinSwitch(device, copyelectric, specialIndex, L);

        if (min1 == min2 && min1 == -1) {
            return -1;
        } else if (min1 == -1) {
            return min2 + 1;
        } else if (min2 == -1) {
            return min1;
        } else {
            return (min1 > min2 + 1) ? min2 + 1 : min1;
        }
    }

    private int[] getNbOne(List<Integer> list, int L) {
        int[] nbOne = new int[L];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < L; j++) {
                nbOne[j] += (list.get(i) & (1 << (L - 1 - j))) >>> (L - 1 - j);
            }
        }
        return nbOne;
    }

    private void bitSwitch(List<Integer> list, int position) {
        for (int i = 0; i < list.size(); i++) {
            int temp = list.get(i) ^ (1 << position);
            list.set(i, temp);
        }
    }

    private boolean isListEqual(List<Integer> l1, List<Integer> l2) {
        Collections.sort(l1);
        Collections.sort(l2);
        for (int i = 0; i < l1.size(); i++) {
            if (!l1.get(i).equals(l2.get(i))) {
                // System.out.println(l1.get(i)+" != "+l2.get(i));
                return false;
            }
        }
        return true;
    }

    private boolean isBitEqual(List<Integer> l1, List<Integer> l2, List<Integer> specialIndex, int L) {
        if (specialIndex.size() == l1.size()) {
            return true;
        }
        List<Integer> copyL1 = new ArrayList<>(l1.size());
        copyL1.addAll(l1);
        List<Integer> copyL2 = new ArrayList<>(l2.size());
        copyL2.addAll(l2);
        for (int index : specialIndex) {
            for (int i = 0; i < copyL1.size(); i++) {
                copyL1.set(i, copyL1.get(i) & (~(1 << (L - 1 - index))));
                copyL2.set(i, copyL2.get(i) & (~(1 << (L - 1 - index))));
            }
        }
        boolean b = isListEqual(copyL1, copyL2);
        return b;
    }

}
