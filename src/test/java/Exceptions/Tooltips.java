package Exceptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Tooltips {
    WebDriver driver;

    @BeforeMethod
    public void open() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void test(){
        String baseUrl = "https://demo.guru99.com/test/social-icon.html";
        driver.get(baseUrl);
        String expectedTooltip = "Github";

        // Find the Github icon at the top right of the header
        WebElement github = driver.findElement(By.xpath(".//*[@class='fa fa-lg fa-github']"));

        //get the value of the "title" attribute of the github icon
        String actualTooltip = github.getAttribute("title");

        //Assert the tooltip's value is as expected
        System.out.println("Actual Title of Tool Tip"+actualTooltip);
        if(actualTooltip.equals(expectedTooltip)) {
            System.out.println("Test Case Passed");
        }
    }

    @Test
    public void handleTooltipWithClickAndHold() {
        driver.manage().window().maximize();
        driver.get("http://demo.guru99.com/test/tooltip.html");
        String expectedTooltip = "What's new in 3.2";
        WebElement download = driver.findElement(By.xpath(".//*[@id='download_now']"));
        Actions builder = new Actions (driver);
        builder.clickAndHold().moveToElement(download);
        builder.moveToElement(download).build().perform();
        WebElement toolTipElement = driver.findElement(By.xpath(".//*[@class='box']/div/a"));
        String actualTooltip = toolTipElement.getText();

        System.out.println("Actual Title of Tool Tip  "+actualTooltip);
        if(actualTooltip.equals(expectedTooltip)) {
            System.out.println("Test Case Passed");
        }
    }


    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
