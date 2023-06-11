package test.base;

//import common.CommonActions;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
//import pages.listing.RozetkaListingPage;
//import pages.rozetka_home.RozetkaHomePage;
import pages.listing.ListingPage;
import setup.SetupDriver;
import pages.home.HomePage;

public class BaseTest {
    protected WebDriver driver = SetupDriver.createDriver();
    protected BasePage basePage = new BasePage(driver);

    protected HomePage homePage = new HomePage(driver);
//
    protected ListingPage listingPage = new ListingPage(driver);



//    @AfterTest
//    public void clearCookiesAndLocalStorage(){
//        if (CLEAR_COOKIES_AND_STORAGE) {
//            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//            driver.manage().deleteAllCookies();
//            javascriptExecutor.executeScript("window.sessionStorage.clear()");
//        }
//    }
//
//    @AfterSuite(alwaysRun = true)
//    public void close() {
//        if (QUIT_BROWSER){
//            driver.quit();
//        }
//    }

}
