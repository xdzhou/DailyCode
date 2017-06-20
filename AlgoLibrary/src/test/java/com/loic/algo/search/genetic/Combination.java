package com.loic.algo.search.genetic;

public class Combination {
    public int first;
    public int second;
    public int third;
    public int fourth;

    public Combination(int first, int second, int third, int fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Combination that = (Combination) o;

        if (first != that.first) return false;
        if (second != that.second) return false;
        if (third != that.third) return false;
        return fourth == that.fourth;
    }

    @Override
    public int hashCode() {
        int result = first;
        result = 31 * result + second;
        result = 31 * result + third;
        result = 31 * result + fourth;
        return result;
    }

    @Override
    public String toString() {
        return "Combination{" +
            "first=" + first +
            ", second=" + second +
            ", third=" + third +
            ", fourth=" + fourth +
            '}';
    }
}
