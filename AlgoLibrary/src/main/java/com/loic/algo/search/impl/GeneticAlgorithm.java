package com.loic.algo.search.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.loic.algo.search.core.State;
import com.loic.algo.search.core.Transition;
import com.loic.algo.search.core.TreeSearch;

public class GeneticAlgorithm implements TreeSearch {
    private static final int MAX_SAME_FITNESS_GENERATION = 7;
    private static final float DEFAULT_MUTATION_RATE = 0.1f;

    private final Random random = new Random(new Date().getTime());

    private int population = 100;
    private int simuCount = 100;

    @Override
    public <Trans extends Transition> List<Trans> find(State<Trans> root, int maxDeep) {
        Objects.requireNonNull(root, "Root state is mandatory");
        Preconditions.checkState(maxDeep > 0, "Max deep must bigger than 0");

        List<Gene> candidatures = new ArrayList<>(population);
        double totalFitness = 0;
        Gene bestGene = null;

        double curBestFitness = Double.NEGATIVE_INFINITY;
        int bestGeneCount = 0;

        List<Gene> children = new ArrayList<>(population);
        for (int generation = 0; generation < simuCount; generation++) {
            if (candidatures.isEmpty()) { //first generation
                for (int i = 0; i < population; i++) {
                    Gene gene = newGene(root, maxDeep);
                    candidatures.add(gene);
                    totalFitness += gene.fitness;
                    if (bestGene == null || bestGene.fitness < gene.fitness) {
                        bestGene = gene;
                    }
                }
            } else {
                Preconditions.checkState(bestGene != null, "best Gene is null");
                children.clear();
                children.add(bestGene);
                double tempTotalFitness = bestGene.fitness;
                while (children.size() < population) {
                    Gene child = generateChild(root, select(totalFitness, candidatures), select(totalFitness, candidatures));
                    children.add(child);
                    tempTotalFitness += child.fitness;
                    if (bestGene.fitness < child.fitness) {
                        bestGene = child;
                    }
                }
                candidatures.clear();
                candidatures.addAll(children);
                totalFitness = tempTotalFitness;
            }
            if (bestGene.fitness != curBestFitness) {
                curBestFitness = bestGene.fitness;
                bestGeneCount = 1;
            } else {
                bestGeneCount++;
            }
            //fixme bestGene.isOver()
            if (bestGeneCount >= MAX_SAME_FITNESS_GENERATION) break;
        }
        Preconditions.checkState(bestGene != null, "returned best Gene is null");

        List<Trans> list = new ArrayList<>(bestGene.trans.length);
        for (Transition tran : bestGene.trans) {
            if (tran != null) {
                list.add((Trans)tran);
            } else {
                break;
            }
        }
        return ImmutableList.copyOf(list);
    }

    private Gene generateChild(State root, Gene parent1, Gene parent2) {
        int changeIndex = random.nextInt(parent1.trans.length -1);
        Transition[] trans = merge(changeIndex, parent1.trans, parent2.trans);

        State curState = root;
        for (int i = 0; i < trans.length; i++) {
            if (curState.isTerminal() || trans[i] == null) break;
            List<Transition> list = curState.nextPossibleTransitions();

            if (list.indexOf(trans[i]) < 0) {
                trans[i] = list.get(random.nextInt(list.size()));
            }
            curState = curState.apply(trans[i]);
        }
        //fixme mutation
        if (random.nextFloat() < DEFAULT_MUTATION_RATE) {
            int mutationIndex = random.nextInt(trans.length);
            //trans[mutationIndex] = transitions.get(random.nextInt(transitions.size()));
        }

        return new Gene(trans, curState.heuristic());
    }

    protected Transition[] merge(int changeIndex, Transition[] trans1, Transition[] trans2) {
        Transition[] children = new Transition[trans1.length];
        for (int i = 0; i < children.length; i++) {
            if (i <= changeIndex) {
                children[i] = trans1[i];
            } else {
                children[i] = trans2[i];
            }
        }
        return children;
    }

    private Gene select(double totalFitness, List<Gene> candidatures) {
        double value = random.nextDouble() * totalFitness;
        double temp = 0;
        for (Gene gene : candidatures) {
            temp += gene.fitness;
            if (value <= temp) return gene;
        }
        throw new RuntimeException("select gene failed");
    }

    private Gene newGene(State root, int deep) {
        Transition[] trans = new Transition[deep];

        State curState = root;
        for (int i = 0; i < deep; i++) {
            if (curState.isTerminal()) break;
            List<Transition> possibleTrans = curState.nextPossibleTransitions();
            trans[i] = possibleTrans.get(random.nextInt(possibleTrans.size()));
            curState = curState.apply(trans[i]);
        }

        Gene gene = new Gene(trans, curState.heuristic());
        return gene;
    }


    private static final class Gene {
        private final Transition[] trans;
        private final double fitness;

        private Gene(Transition[] trans, double fitness) {
            this.trans = trans;
            this.fitness = fitness;
        }
    }
}
