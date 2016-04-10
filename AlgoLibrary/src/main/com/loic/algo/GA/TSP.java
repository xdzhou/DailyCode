package com.loic.algo.GA;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 旅行推销员问题（Travelling Salesman Problem， 又称为旅行商问题、货郎担问题、TSP问题）
 * 是一个多局部最优的最优化问题：有n个城市，一个推销员要从其中某一个城市出发，唯一走遍所有的城市，
 * 再回到他出发的城市，求最短的路线。也即求一个最短的哈密顿回路。
 */
public class TSP {
	private final int num_population = 31;
	private final int num_generation = 1000;
	private final float pv = 1 / 10; // 变异概率为10%
	private final int MAX_FITNESS = 1000000;

	private List<GeneTSP> gene_list = new ArrayList<GeneTSP>(num_population);

	public void start(int[] x, int[] y) {
		if (x.length != y.length) {
			throw new IllegalArgumentException("x.length != y.length");
		}
		double sommeFitness = 0;
		int maxFitnessIndi = 0;

		for (int i = 0; i < num_population; i++) {
			GeneTSP gene = new GeneTSP(x.length);
			gene.generateGene();
			// gene.showGene();
			calculeFitness(gene, x, y);
			sommeFitness += gene.getFitness();
			gene_list.add(gene);
			if (gene.getFitness() > gene_list.get(maxFitnessIndi).getFitness())
				maxFitnessIndi = i;
		}

		List<GeneTSP> chirdrenGene = new ArrayList<GeneTSP>(num_population);
		for (int i = 0; i < num_generation; i++) {
			chirdrenGene.clear();
			for (int j = 0; j < (num_population - 1) / 2; j++) {
				GeneTSP parent_gene1 = getChromoRoulette(sommeFitness);
				GeneTSP parent_gene2 = getChromoRoulette(sommeFitness);
				generateChildren(parent_gene1, parent_gene2, chirdrenGene);
			}
			chirdrenGene.add(gene_list.get(maxFitnessIndi));
			sommeFitness = 0;
			maxFitnessIndi = 0;
			for (int j = 0; j < chirdrenGene.size() - 1; j++) {
				Random r = new Random();
				float taux = r.nextFloat();
				GeneTSP gene = chirdrenGene.get(j);
				if (taux < pv) {
					int gene_len = gene.getGene_len();
					int p1 = r.nextInt(gene_len);
					int p2 = r.nextInt(gene_len);
					int tempG = gene.chromosome[p1];
					gene.chromosome[p1] = gene.chromosome[p2];
					gene.chromosome[p1] = tempG;
				}
				calculeFitness(gene, x, y);
				sommeFitness += gene.getFitness();
				if (gene.getFitness() > chirdrenGene.get(maxFitnessIndi).getFitness())
					maxFitnessIndi = j;
			}
			if (chirdrenGene.get(chirdrenGene.size() - 1).getFitness() > chirdrenGene.get(maxFitnessIndi).getFitness())
				maxFitnessIndi = chirdrenGene.size() - 1;
			gene_list.clear();
			gene_list.addAll(chirdrenGene);
		}
		System.out.println("length = " + (MAX_FITNESS - gene_list.get(maxFitnessIndi).getFitness()));
		gene_list.get(maxFitnessIndi).showGene();
	}

	/*
	 * 单交叉点法 （用于互换编码）
	 * 选择一个交叉点，子代的从初始位置出发的部分从一个基因复制，然后在另一个基因中扫描，如果某个位点在子代中没有，就把它添加进去。 如：交叉前：
	 * 87213 | 09546 98356 | 71420 交叉后： 87213 | 95640 98356 | 72104
	 */
	private void generateChildren(GeneTSP pg1, GeneTSP pg2, List<GeneTSP> chirdrenGene) {
		Random r = new Random();
		int pointor = r.nextInt(pg1.getGene_len() - 1);
		int[] chro1 = new int[pg1.getGene_len()];
		int[] chro2 = new int[pg1.getGene_len()];
		for (int i = 0; i <= pointor; i++) {
			chro1[i] = pg1.chromosome[i];
			chro2[i] = pg2.chromosome[i];
		}
		int indi = pointor + 1;
		for (int i = 0; i < pg2.getGene_len(); i++) {
			boolean flag = false;
			int g = pg2.chromosome[i];
			for (int j = 0; j <= pointor; j++) {
				if (chro1[j] == g) {
					flag = true;
					break;
				}
			}
			if (!flag)
				chro1[indi++] = g;
		}
		indi = pointor + 1;
		for (int i = 0; i < pg1.getGene_len(); i++) {
			boolean flag = false;
			int g = pg1.chromosome[i];
			for (int j = 0; j <= pointor; j++) {
				if (chro2[j] == g) {
					flag = true;
					break;
				}
			}
			if (!flag)
				chro2[indi++] = g;
		}
		GeneTSP child_gene1 = new GeneTSP(pg1.getGene_len());
		child_gene1.setChromosome(chro1);
		GeneTSP child_gene2 = new GeneTSP(pg1.getGene_len());
		child_gene2.setChromosome(chro2);
		chirdrenGene.add(child_gene1);
		chirdrenGene.add(child_gene2);
	}

	private GeneTSP getChromoRoulette(double sommeFitness) {
		Random r = new Random();
		float taux = r.nextFloat();
		float temp = 0;
		for (int i = 0; i < num_population; i++) {
			GeneTSP gene = gene_list.get(i);
			temp += (gene.getFitness() / sommeFitness);
			if (temp >= taux)
				return gene;
		}
		return null;
	}

	private void calculeFitness(Gene g, int[] x, int[] y) {
		double f = 0;
		int gene_len = g.getGene_len();
		for (int i = 0; i < gene_len; i++) {
			f += distancePoints(x[g.chromosome[i]], y[g.chromosome[i]], x[g.chromosome[(i + 1) % gene_len]],
					y[g.chromosome[(i + 1) % gene_len]]);
		}

		g.setFitness(MAX_FITNESS - f);
	}

	private double distancePoints(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}
}
