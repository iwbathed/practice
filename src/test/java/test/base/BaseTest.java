package test.base;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import pages.base.BasePage;

import pages.listing.ListingPage;
import setup.SetupDriver;
import pages.home.HomePage;

import static setup.Config.CLEAR_COOKIES_AND_STORAGE;
import static setup.Config.QUIT_BROWSER;

public class BaseTest {
    protected WebDriver driver = SetupDriver.createDriver();
    protected BasePage basePage = new BasePage(driver);

    protected HomePage homePage = new HomePage(driver);
//
    protected ListingPage listingPage = new ListingPage(driver);



    @AfterTest
    public void clearCookiesAndLocalStorage(){
        if (CLEAR_COOKIES_AND_STORAGE) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void close() {
        if (QUIT_BROWSER){
            driver.quit();
        }
    }

}
