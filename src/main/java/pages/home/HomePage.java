package pages.home;

import constants.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.base.BasePage;


public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//input[@name='sname']")
    WebElement searchField;

    @FindBy(xpath = "//div[contains(@class, 'cd-dropdown-wrapper')]")
    WebElement catalog;

    public HomePage searchFieldClearAndEnterString(String inputText){
        searchField.clear();
        searchField.sendKeys(inputText, Keys.ENTER);
        return this;
    }

    public HomePage chooseFromCatalog(Constant.CatalogNames catalogName){

        catalog.click();

        WebElement catalogToChoseElement = driver.findElement(
                By.xpath("//a[contains(text(),  '" + catalogName.getValue() + "')]")
        );
        waitElementIsVisible(catalogToChoseElement);
        catalogToChoseElement.click();

        return this;
    }

}