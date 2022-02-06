package de.ir31k0.nonogramsolver.core;

import de.ir31k0.nonogramsolver.api.data.Nonogram;
import de.ir31k0.nonogramsolver.api.util.SolverUtil;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NonogramByDownloadTest extends AbstractNonogramSolverTest {
    private static final String BASE_PATH = "etc/downloads/webpbn";

    @Test
    public void testAll() throws IOException {
        Path path = Path.of(BASE_PATH);
        Logger.debug("Use folder: " + path.toAbsolutePath());
        assertTrue(Files.exists(path));
        assertTrue(Files.isDirectory(path));

        DirectoryStream<Path> paths = Files.newDirectoryStream(path);

        for (Path nonoPath : paths) {
            NonogramSolver solver = new NonogramSolver();
            Nonogram nonogram = SolverUtil.fromPath(nonoPath);
            solver.solve(nonogram);
            boolean solved = nonogram.isSolved();
            if (solved) {
                Logger.info(nonogram.toString());
            }

            assertTrue(solved);
        }
    }
}
