package CrossHeadlessAndJsExecutor;

import java.util.concurrent.TimeUnit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.*;


public class CrossBrowser {

    WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception{

        //Check if parameter passed as 'chrome'
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        //Check if parameter passed as 'Edge'
        else if(browser.equalsIgnoreCase("Edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
    }

    @Test
    public void handleStaticTable() {
        driver.get("http://demo.guru99.com/test/radio.html");
        WebElement option1 = driver.findElement(By.id("vfb-6-0"));
        option1.click();
        if (option1.isSelected()) {
            System.out.println("option1 is selected");

        } else {
            System.out.println("option1 is not selected");
        }
    }

}