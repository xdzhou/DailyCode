package com.loic.algo.GA;

import java.util.Random;

public class GeneTSP extends Gene {

	public GeneTSP(int nb) {
		super(nb);
		for (int i = 0; i < nb; i++)
			chromosome[i] = -1;
	}

	@Override
	public void generateGene() {
		int nb = chromosome.length;
		Random random = new Random();
		for (int i = 0; i < nb; i++) {
			int code = random.nextInt(nb);
			if (chromosome[code] == -1) {
				chromosome[code] = i;
			} else {
				while (true) {
					code++;
					code %= nb;
					if (chromosome[code] == -1) {
						chromosome[code] = i;
						break;
					}
				}
			}
		}

	}

}
