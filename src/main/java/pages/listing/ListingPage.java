package pages.listing;

import constants.Constant.PageDirection;
import constants.Constant.SortDirection;
import constants.Constant.SortTypes;
import org.openqa.selenium.*;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListingPage extends BasePage {
    public ListingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//nav[contains(@class, 'pagBlock')]//ul//li[last()-1]")
    WebElement lastPageNumberButton;
    @FindBy(xpath = "//nav[contains(@class, 'pagBlock')]//ul//li[last()]")
    WebElement nextPageBtn;
    @FindBy(xpath = "//nav[contains(@class, \"pagBlock\")]//ul//li[1]")
    WebElement previousPageBtn;
    @FindBy(xpath = "//div[contains(@class, \"absDrop\")]")
    WebElement selectDrpSorting;
    @FindBy(xpath = "//div[contains(@class, \"goodWrap\") ][last()]")
    WebElement lastCardElement;
    @FindBy(xpath = "//div[contains(@class, \"cookiesAccept\")]")
    WebElement cookiesAccept;

    private final By price = By.xpath("//div[@class=\"newPrice\"]");
    public By price(int i){
        return By.xpath(
                "//div[@class=\"newPrice\"][" + i + "]" +
                        "//span");
    }

    public int getPagesNumber(){
        waitElementIsVisibleFluent(lastPageNumberButton);
        return Integer.parseInt(lastPageNumberButton.getText());
    }

    public ListingPage goPage(PageDirection direction){
        WebElement pageBtn;
        if (direction == PageDirection.NEXT) {
            pageBtn = nextPageBtn;
        } else {
            pageBtn = previousPageBtn;
        }
        waitElementIsVisibleFluent(pageBtn);
        waitElementIsClickable(pageBtn);
        pageBtn.click();
        return this;
    }

    public ListingPage chooseSorting(SortTypes sortType){
        WebElement dropdown = waitElementIsVisible(selectDrpSorting);
        dropdown.click();
        By sortXpath =  By.xpath("//li[@class='d-block ']//a[contains(text(), '" + sortType.getValue() + "')]");
        WebElement sortElement = waitElementIsVisible(driver.findElement(sortXpath));
        sortElement.click();
        return this;
    }


public ListingPage verifySorted(List<Integer> prices, SortDirection sortDirection) {
    List<Integer> pricesCopy = new ArrayList<>(prices);
    if (sortDirection == SortDirection.ASCENDING) {
        Collections.sort(pricesCopy);
    } else if (sortDirection == SortDirection.DESCENDING) {
        Collections.sort(pricesCopy, Collections.reverseOrder());
    }

    System.out.println(prices);
    System.out.println(pricesCopy);
    Assert.assertEquals(prices, pricesCopy);
    return this;
}

    public List <Integer> getPricesOfElementsFromPage()  {

        List <Integer> pricesValue = new ArrayList<>();
        String priceText;
        List <WebElement> priceElements = driver.findElements(price);

        for (WebElement element : priceElements){
            for(int ii=0;;ii++){
                try {
                    priceText=element.getText();
                    break;
                }catch (StaleElementReferenceException e){
                    System.out.println(ii + " " + e.getMessage());
                }
            }
            priceText=priceText.strip().split(" грн.")[0];

            pricesValue.add(Integer.parseInt( priceText));
        }

        return pricesValue;
    }

    public List <Integer> getPricesOfElementsFromAllPages() {
        List<Integer> allPrices = new ArrayList<>();
        List<Integer> elements;
        int pagesNumber = getPagesNumber();
        for (int currentPage = 1; currentPage <= pagesNumber; currentPage++){
            waitElementIsVisibleFluent(lastCardElement);
            elements=getPricesOfElementsFromPage();
            if (elements.size()==0)
                break;

            allPrices.addAll(elements);

            ((JavascriptExecutor) driver).executeScript("arguments[0].remove()", cookiesAccept);
            goPage(PageDirection.NEXT);
            waitUrlContains("page"+(currentPage+1));


        }
        return allPrices;
    }
}
