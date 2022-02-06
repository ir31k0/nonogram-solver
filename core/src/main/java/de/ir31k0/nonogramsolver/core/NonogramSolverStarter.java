package de.ir31k0.nonogramsolver.core;

import de.ir31k0.nonogramsolver.api.data.Nonogram;
import de.ir31k0.nonogramsolver.api.util.SolverUtil;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;

import static de.ir31k0.nonogramsolver.api.util.Comparison.not;

/**
 * Starter class to run the nonogram solver.
 *
 * Required passable parameters:
 * file path:       Path to the .nono file
 */
public class NonogramSolverStarter {
    public static void main(String... args) throws IOException {
        if (args.length == 0) {
            Logger.info("Please pass the following parameters: <file | directory path...>");
            return;
        }
        NonogramSolver solver = new NonogramSolver();
        for (String arg : args) {

            File file = new File(arg);
            if (not(file.exists())) {
                Logger.error("'{}' not does not exists", file.getAbsolutePath());
                continue;
            }

            if (file.isDirectory()) {
                for (File nonoFile : file.listFiles()) {
                    solve(solver, nonoFile);
                }
            } else {
                solve(solver, file);
            }
        }
    }

    private static void solve(NonogramSolver solver, File file) throws IOException {
        Nonogram nonogram = SolverUtil.fromFile(file);
        solver.solve(nonogram);
        Logger.info("---------- RESULT ----------");
        Logger.info("Solved: " + (nonogram.isSolved() ? "yes" : "no"));
        Logger.info(nonogram);
    }
}
