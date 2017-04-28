package com.loic.algo.search;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm<G extends GeneticAlgorithm.Gene> {
    private static final int MAX_SAME_FITNESS_GENERATION = 7;
    private static final float DEFAULT_MUTATION_RATE = 0.1f;

    private final IGAListener<G> mListener;
    private final Random mRandom;
    private final float mParamMutationRate;

    public GeneticAlgorithm(IGAListener<G> listener) {
        this(listener, DEFAULT_MUTATION_RATE);
    }

    public GeneticAlgorithm(IGAListener<G> listener, float mutationRate) {
        mListener = listener;
        mRandom = new Random(new Date().getTime());
        mParamMutationRate = mutationRate;
    }

    public G execute(int population, int simulationCount) {
        List<G> candidatures = new ArrayList<>(population);
        float totalFitness = 0;
        G bestGene = null;

        float curBestFitness = Float.NEGATIVE_INFINITY;
        int bestGeneCount = 0;

        List<G> children = new ArrayList<>(population);
        for (int generation = 0; generation < simulationCount; generation++) {
            if (candidatures.isEmpty()) { //first generation
                for (int i = 0; i < population; i++) {
                    G gene = mListener.createNewGene(mRandom);
                    gene.mFitness = mListener.computerFitness(gene);
                    candidatures.add(gene);
                    totalFitness += gene.mFitness;
                    if (bestGene == null || bestGene.mFitness < gene.mFitness) {
                        bestGene = gene;
                    }
                }
            } else {
                if (bestGene == null) throw new RuntimeException("best Gene is null");
                children.clear();
                children.add(bestGene);
                float tempTotalFitness = bestGene.mFitness;
                while (children.size() < population) {
                    G child = getChild(select(totalFitness, candidatures), select(totalFitness, candidatures));
                    child.mFitness = mListener.computerFitness(child);
                    children.add(child);
                    tempTotalFitness += child.mFitness;
                    if (bestGene.mFitness < child.mFitness) {
                        bestGene = child;
                    }
                }
                candidatures.clear();
                candidatures.addAll(children);
                totalFitness = tempTotalFitness;
            }
            if (bestGene.mFitness != curBestFitness) {
                curBestFitness = bestGene.mFitness;
                bestGeneCount = 1;
            } else {
                bestGeneCount++;
            }
            if (bestGene.isOver() || bestGeneCount >= MAX_SAME_FITNESS_GENERATION) break;
        }
        if (bestGene == null) throw new RuntimeException("returned best Gene is null");
        return bestGene;
    }

    private G select(float totalFitness, List<G> candidatures) {
        float value = mRandom.nextFloat() * totalFitness;
        float temp = 0;
        for (G gene : candidatures) {
            temp += gene.mFitness;
            if (value <= temp) return gene;
        }
        throw new RuntimeException("select gene failed");
    }

    private G getChild(G parent1, G parent2) {
        G child = mListener.getChild(mRandom, parent1, parent2);
        if (mRandom.nextFloat() < mParamMutationRate) child.mutation(mRandom);
        return child;
    }

    public interface IGAListener<G extends GeneticAlgorithm.Gene> {
        G createNewGene(Random random);

        float computerFitness(G gene);

        G getChild(Random random, G parent1, G parent2);
    }

    public abstract static class Gene<E> implements Comparable<Gene<E>> {
        public final E[] mData;
        protected float mFitness;

        @SuppressWarnings("unchecked")
        public Gene(E[] data) {
            mData = (E[]) Array.newInstance(data.getClass().getComponentType(), data.length);
            System.arraycopy(data, 0, mData, 0, data.length);
        }

        protected boolean isOver() {
            return false;
        }

        public float getFitness() {
            return mFitness;
        }

        protected abstract void mutation(Random random);

        @Override
        public int compareTo(Gene<E> another) {
            return Float.compare(mFitness, another.mFitness);
        }
    }
}