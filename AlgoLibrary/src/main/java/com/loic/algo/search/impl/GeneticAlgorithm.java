package com.loic.algo.search.impl;

import static com.loic.algo.search.TreeSearchUtils.asStringSort;
import static java.util.Objects.requireNonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import com.google.common.base.Preconditions;
import com.loic.algo.search.TimeoutException;
import com.loic.algo.search.Timer;
import com.loic.algo.search.TreeSearchUtils;
import com.loic.algo.search.core.SearchParam;
import com.loic.algo.search.core.TreeSearch;

public class GeneticAlgorithm implements TreeSearch {
    private static final int MAX_SAME_FITNESS_GENERATION = 7;
    private static final float DEFAULT_MUTATION_RATE = 0.1f;

    private final Random random = new Random(new Date().getTime());
    private final Timer timer = new Timer();

    private int population = 100;
    private int simuCount = 100;

    @Override
    public <Trans, State> Optional<Trans> find(State root, SearchParam<Trans, State> param) {
        requireNonNull(root, "Root state is mandatory");
        requireNonNull(param, "SearchParam is mandatory");

        Optional<Trans> next = TreeSearchUtils.nextTrans(root, param.transitionStrategy());
        if (next != null) return next;

        List<Gene<Trans>> candidatures = new ArrayList<>(population);
        double totalFitness = 0;
        Gene<Trans> bestGene = null;

        double curBestFitness = Double.NEGATIVE_INFINITY;
        int bestGeneCount = 0;

        List<Gene<Trans>> children = new ArrayList<>(population);
        timer.startTimer(param.timerDuration());

        for (int generation = 0; generation < simuCount; generation++) {
            try {
                timer.checkTime();
            } catch (TimeoutException e) {
                break;
            }
            if (candidatures.isEmpty()) { //first generation
                for (int i = 0; i < population; i++) {
                    Gene<Trans> gene = newGene(param, root, param.getMaxDepth());
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
                    Gene<Trans> child = generateChild(param, root, select(totalFitness, candidatures), select(totalFitness, candidatures));
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

        return Optional.ofNullable(bestGene.trans[0]);
    }

    private <Trans, State> Gene<Trans> generateChild(SearchParam<Trans, State> param, State root, Gene<Trans> parent1, Gene<Trans> parent2) {
        int changeIndex = random.nextInt(parent1.trans.length -1);
        Trans[] trans = merge(changeIndex, parent1.trans, parent2.trans);

        State curState = root;
        int depth = 0;
        for (int i = 0; i < trans.length; i++) {
            List<Trans> list = asStringSort(param.transitionStrategy().generate(curState));
            if (list.isEmpty()) break;
            if (list.indexOf(trans[i]) < 0) {
                trans[i] = list.get(random.nextInt(list.size()));
            }
            curState = param.applyStrategy().apply(curState, trans[i]);
            depth ++;
        }
        //fixme mutation
        if (random.nextFloat() < DEFAULT_MUTATION_RATE) {
            int mutationIndex = random.nextInt(trans.length);
            //trans[mutationIndex] = transitions.get(random.nextInt(transitions.size()));
        }

        return new Gene<>(trans, param.heuristicStrategy().heuristic(curState, depth));
    }

    protected <Trans> Trans[] merge(int changeIndex, Trans[] trans1, Trans[] trans2) {
        return Stream.concat(Stream.of(trans1).limit(changeIndex + 1), Stream.of(trans2).skip(changeIndex + 1))
                .toArray(l -> (Trans[]) Array.newInstance(trans1[0].getClass(), l));
    }

    private <Trans> Gene<Trans> select(double totalFitness, List<Gene<Trans>> candidatures) {
        double value = random.nextDouble() * totalFitness;
        double temp = 0;
        for (Gene gene : candidatures) {
            temp += gene.fitness;
            if (value <= temp) return gene;
        }
        throw new RuntimeException("select gene failed");
    }

    private <Trans, State> Gene<Trans> newGene(SearchParam<Trans, State> param, State root, int maxDepth) {
        Trans[] trans = null;
        State curState = root;
        int depth = 0;

        for (int i = 0; i < maxDepth; i++) {
            List<Trans> possibleTrans = asStringSort(param.transitionStrategy().generate(curState));
            if (possibleTrans.isEmpty()) break;
            if (trans == null) {
                trans = (Trans[]) Array.newInstance(possibleTrans.get(0).getClass(), maxDepth);
            }
            trans[i] = possibleTrans.get(random.nextInt(possibleTrans.size()));
            curState = param.applyStrategy().apply(curState, trans[i]);
            depth ++;
        }

        return new Gene<>(trans, param.heuristicStrategy().heuristic(curState, depth));
    }


    private static final class Gene<Trans> {
        private final Trans[] trans;
        private final double fitness;

        private Gene(Trans[] trans, double fitness) {
            this.trans = trans;
            Preconditions.checkState(fitness >= 0, "fitness need positive");
            this.fitness = fitness;
        }
    }
}
