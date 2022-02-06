package de.ir31k0.nonogramsolver.core;

import de.ir31k0.nonogramsolver.api.exception.IllegalFieldException;
import de.ir31k0.nonogramsolver.api.util.SolverUtil;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;

public abstract class AbstractNonogramSolverTest {

    private static final String NONO_FILE_SUFFIX = ".nono";

    protected void solveNonogramFileByMethodName() throws IOException, IllegalFieldException {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String fileName = methodName.replaceAll("(.)([A-Z])", "$1-$2").toLowerCase() + NONO_FILE_SUFFIX;
        Logger.debug("Test nonogram solver with " + fileName);
        File testFile = new File("./src/test/resources/" + fileName);
        NonogramSolver solver = new NonogramSolver();
        solver.solve(SolverUtil.fromFile(testFile));
    }

    /*
        Usage of createLineByPattern
        Field[] line1 = createLineByPattern("_ _ _ X _ _ _ _ _ X");
        Field[] line2 = createLineByPattern(10, "_ _ _ X _ _ _ _ _ X");
        Field[] line3 = createLineByPattern("_", "_", "_", "X", "_", "_", "_", "_", "_", "X");
        Field[] line4 = createLineByPattern(10, "_", "_", "_", "X", "_", "_", "_", "_", "_", "X");
        Field[] line5 = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.EMPTY, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.EMPTY};

    protected Field[] createLineByPattern(int size, String pattern) {
        return createLineByPattern(size, pattern.split(PATTERN_DELIMITER));
    }

    protected Field[] createLineByPattern(int size, String... fields) {
        if (size != fields.length) throw new RuntimeException("The expected size " + size + " is not equal with real size of " + fields.length);
        return createLineByPattern(fields);
    }

    protected Field[] createLineByPattern(String pattern) {
        return createLineByPattern(pattern.split(PATTERN_DELIMITER));
    }
     */
}
