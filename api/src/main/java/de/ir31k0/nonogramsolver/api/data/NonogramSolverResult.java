package de.ir31k0.nonogramsolver.api.data;

public class NonogramSolverResult {
    private final Nonogram nonogram;
    private final boolean solved;

    public NonogramSolverResult(Nonogram nonogram, boolean solved) {
        this.nonogram = nonogram;
        this.solved = solved;
    }

    public Nonogram getNonogram() {
        return nonogram;
    }

    public boolean isSolved() {
        return solved;
    }
}
