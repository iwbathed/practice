package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;


public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    private final By search = By.xpath("//input[@name='sname']");
    private final By dropdownMenu = By.xpath("//div[contains(@class, 'cd-dropdown-wrapper')]");



    public HomePage searchFieldClearAndEnterString(String inputText){
        WebElement searchField = driver.findElement(search);
        searchField.clear();
        searchField.sendKeys(inputText, Keys.ENTER);
        return this;
    }

    public HomePage chooseFromCatalog(String catalogName){
        WebElement catalog = driver.findElement(dropdownMenu);
        catalog.click();
        By catalogElementXpath = By.xpath("//a[contains(text(),  '" + catalogName + "')]");
        waitElementIsVisible(catalogElementXpath);
        catalog.findElement(catalogElementXpath).click();

        return this;
    }

}