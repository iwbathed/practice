package pages.listing;

import org.openqa.selenium.*;

import org.testng.Assert;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListingPage extends BasePage {
    public ListingPage(WebDriver driver) {
        super(driver);
    }

    private final By lastPageNumberButton = By.xpath("//nav[contains(@class, 'pagBlock')]//ul//li[last()-1]");
    private final By nextPageBtn = By.xpath("//nav[contains(@class, 'pagBlock')]//ul//li[last()]");
    private final By previousPageBtn = By.xpath("//nav[contains(@class, \"pagBlock\")]//ul//li[1]");

    private final By selectDrpSorting = By.xpath("//div[contains(@class, \"absDrop\")]");


    private final By lastCardElement = By.xpath("//div[contains(@class, \"goodWrap\") ][last()]");
    private final By cookiesAccept = By.xpath("//div[contains(@class, \"cookiesAccept\")]");



    private final By price = By.xpath("//div[@class=\"newPrice\"]");
    public By price(int i){

        return By.xpath(
                "//div[@class=\"newPrice\"][" + i + "]" +
                        "//span");
    }


    public int getPagesNumber(){
        waitElementIsVisibleFluent(lastPageNumberButton);
        return Integer.parseInt(driver.findElement(lastPageNumberButton).getText());
    }

    public ListingPage goNextPage(){

        waitElementIsVisibleFluent(nextPageBtn);
        waitElementIsClickable(nextPageBtn);
        driver.findElement(nextPageBtn).click();
        return this;
    }

    public ListingPage goPreviousPage(){
        waitElementIsVisibleFluent(previousPageBtn);
        waitElementIsClickable(previousPageBtn);
        driver.findElement(previousPageBtn).click();
        return this;
    }

    public ListingPage chooseSorting(String sortType){
        WebElement dropdown = waitElementIsVisible(selectDrpSorting);
        dropdown.click();
        By sortXpath =  By.xpath("//li[@class='d-block ']//a[contains(text(), '" + sortType + "')]");
        WebElement sort = waitElementIsVisible(sortXpath);
        sort.click();
        return this;
    }

    public ListingPage isSortedAsc(List<Integer> prices){
        List<Integer> pricesCopy = new ArrayList<>(prices);
        Collections.sort(pricesCopy);
        System.out.println(prices);
        System.out.println(pricesCopy);

        Assert.assertEquals(prices, pricesCopy);
        return this;
    }

    public ListingPage isSortedDesc(List<Integer> prices){
        List<Integer> pricesCopy = new ArrayList<>(prices);
        Collections.sort(pricesCopy, Collections.reverseOrder());
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
            elements=getPricesOfElementsFromPage();
            if (elements.size()==0)
                break;

            if (currentPage==pagesNumber){
                allPrices.addAll(elements);
            }else {
                waitElementIsVisibleFluent(lastCardElement);
                waitElementIsVisibleFluent(lastPageNumberButton);
                allPrices.addAll(elements);

                WebElement cookieMessage = driver.findElement(cookiesAccept);
                ((JavascriptExecutor) driver).executeScript("arguments[0].remove()", cookieMessage);
                goNextPage();
                waitUrlContains("page"+(currentPage+1));
            }

        }
        return allPrices;
    }
}
