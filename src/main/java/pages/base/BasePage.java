package pages.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static constants.Constant.TimeOutVariable.EXPLICITLY_WAIT;

public class BasePage {
    protected WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void open(String url){
        driver.get(url);
    }

    public void waitElementIsVisibleFluent(WebElement element){
        FluentWait wait = new FluentWait(driver);

        wait.withTimeout(Duration.ofSeconds(EXPLICITLY_WAIT));
        wait.pollingEvery(Duration.ofMillis(100));
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public WebElement waitElementIsVisible(WebElement element){
        return new WebDriverWait(driver, Duration.ofSeconds(EXPLICITLY_WAIT)).until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitElementIsClickable(WebElement element){
        return new WebDriverWait(driver, Duration.ofSeconds(EXPLICITLY_WAIT)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUrlContains(String text){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITLY_WAIT)).until(ExpectedConditions.urlContains(text));
    }

    public void checkUrlContains(String text) {

        boolean contains;
        try {

            contains= java.net.URLDecoder.decode(driver.getCurrentUrl(), StandardCharsets.UTF_8.name()).contains(text);

        }catch (UnsupportedEncodingException e){
            contains=false;
        }
        Assert.assertTrue(contains);

    }

    public static void waitForPageLoad(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String pageLoadStatus;
        do {
            pageLoadStatus = (String) js.executeScript("return document.readyState");
        } while (!pageLoadStatus.equals("complete"));
    }




}
