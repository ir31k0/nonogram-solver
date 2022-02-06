package de.ir31k0.nonogramsolver.tools.downloader.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class DefaultPage {

    private final WebDriver driver;
    private final String url;
    private final Map<By, WebElement> webElementCache = new HashMap<>();

    /** Timeout to wait in seconds */
    private final int timeout = 2;
    private final WebDriverWait driverWait;

    public DefaultPage(WebDriver driver, String url) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        this.url = url;
    }

    public void navigateTo() {
        driver.get(url);
    }

    protected void clickOn(By by) {
        getElementFromCache(by).click();
    }
    
    protected void typeIn(By by, CharSequence... keysToTypeIn) {
        getElementFromCache(by).sendKeys(keysToTypeIn);
    }

    protected String getInnerHTMLOf(By by) {
        return getElementFromCache(by).getAttribute("innerHTML");
    }

    protected boolean existsElement(By by) {
        return driver.findElements(by).size() != 0;
    }

    private WebElement getElementFromCache(By by) {
        WebElement element = webElementCache.get(by);
        if (element == null) {
            element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            webElementCache.put(by, element);
        }
        return element;
    }
}
