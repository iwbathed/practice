package setup;




import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;

import static constants.Constant.TimeOutVariable.IMPLICITLY_WAIT;
import static setup.Config.BROWSER;

public class SetupDriver {

    public static WebDriver createDriver(){
        WebDriver driver = null;


        switch (BROWSER){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");

                driver = new ChromeDriver(options);

                break;
            default:
                Assert.fail("Incorrect platform, or browser name : " + BROWSER);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT));


        return driver;
    }



}



