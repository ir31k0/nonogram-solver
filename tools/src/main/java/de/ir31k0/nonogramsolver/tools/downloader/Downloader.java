package de.ir31k0.nonogramsolver.tools.downloader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Downloader {

    protected static final ExecutorService executor = Executors.newSingleThreadExecutor();

    protected static WebDriver createEdgeDriver() {
        System.setProperty("webdriver.edge.driver", "./etc/driver/msedgedriver_87.exe");
        return new EdgeDriver();
    }
}
