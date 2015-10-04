package com.loic.algo.GA;

/*
 * 基因编码
 */
public abstract class Gene
{
	protected int[] chromosome;
	private double fitness;
	private int gene_len;

	public Gene(int nb)
	{
		gene_len = nb;
		chromosome = new int[nb];
	}

	public double getFitness()
	{
		return fitness;
	}

	public void setFitness(double fitness)
	{
		this.fitness = fitness;
	}

	public int getGene_len()
	{
		return gene_len;
	}

	public void setChromosome(int[] chromosome)
	{
		if(chromosome.length != gene_len)
		{
			throw new IllegalArgumentException("chromosome.length != gene_len");
		}
		this.chromosome = chromosome;
	}

	public void showGene()
	{
		for (int i = 0; i < chromosome.length; i++)
		{
			System.out.print(chromosome[i] + " ");
		}
		System.out.println();
	}

	public abstract void generateGene();
}
