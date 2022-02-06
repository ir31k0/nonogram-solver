package de.ir31k0.nonogramsolver.tools.downloader;

import org.tinylog.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FindTheBiggest {
    private static final  String ROOT_PATH = "./etc/downloads/webpbn/";

    public static void main(final String[] args) throws Exception {
        final File rootDirectory = new File(ROOT_PATH);

        if (!rootDirectory.isDirectory()) {
            throw new RuntimeException("Root path '" + rootDirectory.getAbsolutePath() + "' is not a directory.");
        }

        final File[] files = rootDirectory.listFiles();

        if (null == files) {
            throw new RuntimeException("Problem occurred on path '" + rootDirectory.getAbsolutePath() + "'.");
        }

        Logger.debug("Scan " + files.length + " files..");

        int highestWidth = 0, highestHeight = 0;

        for (final File file : files) {
            try (final BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                int width = -1, height = -1;
                while (null != (line = br.readLine())) {
                    if (line.startsWith("width")) {
                        width = Integer.parseInt(line.substring(6));
                        if (height > 0) {
                            break;
                        }
                    } else if (line.startsWith("height")) {
                        height = Integer.parseInt(line.substring(7));
                        if (width > 0) {
                            break;
                        }
                    }
                }
                if (width > 0 && height > 0) {
                    if (width > highestWidth) {
                        highestWidth = width;
                        Logger.debug("Highest width " + highestWidth + " with height " + height);

                        if (width >= 80) {
                            Logger.debug(file.getAbsolutePath());
                        }
                    }
                    if (height > highestHeight) {
                        highestHeight = height;
                        Logger.debug("Highest height: " + highestHeight + " with width " + width);
                        if (height >= 80) {
                            Logger.debug(file.getAbsolutePath());
                        }
                    }
                } else {
                    throw new Exception("Width or height was not found!");
                }
            }
        }
    }


}
