package de.ir31k0.nonogramsolver.tools.downloader;

import de.ir31k0.nonogramsolver.tools.downloader.page.WebPBNPage;
import org.openqa.selenium.WebDriver;
import org.tinylog.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class WebPBNDownloader extends Downloader {

    private static final long ONE_SECOND_IN_MILLIS = 1_000L;
    private static final String RELATIVE_EXPORT_DIRECTORY_PATH = "./etc/downloads/webpbn/";
    private static final int START = 4191;
    private static final int END = 100000;

    public static void main(final String... args) throws InterruptedException {
        final WebDriver driver = createEdgeDriver();
        for (int i = START; END >= i; i++) {
            final int nonogramId = i;
            executor.execute(() -> {
                final WebPBNPage page = new WebPBNPage(driver);
                page.navigateTo();
                page.typeNonogramId(nonogramId);
                page.checkNonFormat();
                page.uncheckSolutionExport();
                waitForOneSecond();
                page.clickOnExport();
                waitForOneSecond();
                if (page.nonogramExists()) {
                    writeNonogramFile(nonogramId, page.getExportedNonogram());
                }
            });
        }
        executor.shutdown();
        final boolean done = executor.awaitTermination(24L, TimeUnit.HOURS);
        if (!done) {
            executor.shutdownNow();
        }
        driver.quit();
    }

    private static void waitForOneSecond() {
        try {
            Thread.sleep(ONE_SECOND_IN_MILLIS);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeNonogramFile(final int id, final String content) {
        final File nonogramFile = new File(RELATIVE_EXPORT_DIRECTORY_PATH + id + ".nono");
        if (nonogramFile.exists()) {
            Logger.warn("Nonogram file " + id + " already exists!");
        }
        try (final Writer writer = new FileWriter(nonogramFile, StandardCharsets.UTF_8)) {
            writer.write(content);
        } catch (final IOException e) {
            throw new RuntimeException("Error while writing nonogram file " + id, e);
        }
    }
}
