package de.ir31k0.nonogramsolver.tools.downloader.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebPBNPage extends DefaultPage {

    public WebPBNPage(WebDriver driver) {
        super(driver, "https://webpbn.com/export.cgi");
    }

    public void typeNonogramId(int id) {
        typeIn(By.name("id"), String.valueOf(id));
    }

    public void checkNonFormat() {
        clickOn(By.xpath("//*[@id=\"form\"]/table/tbody/tr[7]/td[1]/input"));
    }

    public void uncheckSolutionExport() {
        clickOn(By.xpath("//*[@id=\"form\"]/table/tbody/tr[7]/td[2]/input"));
    }

    public void clickOnExport() {
        clickOn(By.xpath("//*[@id=\"form\"]/table/tbody/tr[29]/td/input"));
    }

    public boolean nonogramExists() {
        return existsElement(By.xpath("/html/body/pre"));
    }

    public String getExportedNonogram() {
        return getInnerHTMLOf(By.xpath("/html/body/pre"));
    }
}
