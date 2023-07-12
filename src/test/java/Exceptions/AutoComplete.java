package Exceptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AutoComplete {
    WebDriver driver;

    @BeforeMethod
    public void open() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void autoSuggestion() throws InterruptedException {
        driver.get("http://www.amazon.in");
        driver.manage().window().maximize();
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone");
        Thread.sleep(5000);
        List<WebElement> searchListAutoComplete = driver
                .findElements(By.xpath("//*[@id='nav-flyout-searchAjax']/div[2]"));
        for (WebElement ele : searchListAutoComplete) {
            String searchTexts = ele.getText();
            String expected = "iphone 12";
            System.out.println(searchTexts);
            if (searchTexts.contains(expected)) {
                ele.click();
                Thread.sleep(5000);
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
