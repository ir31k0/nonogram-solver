package de.ir31k0.nonogramsolver;

import de.ir31k0.nonogramsolver.exception.IllegalFieldException;
import de.ir31k0.nonogramsolver.util.SolverUtil;

import java.io.File;
import java.io.IOException;

public class NonogramSolverStarter {
    public static void main(String[] args) throws IllegalFieldException, IOException {
        if (args.length != 1) {
            System.out.println("Please pass the following parameters: <file path>");
            System.out.println("file path: Path to the .nono file");
            return;
        }
        NonogramSolver solver = new NonogramSolver(SolverUtil.fromFile(new File(args[0])));
        solver.run();
        solver.printBoard();
    }
}
