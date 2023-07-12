package Exceptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ExceptionTests {
    WebDriver driver;

    @BeforeMethod
    public void open() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void handleExceptions(){
        driver.get("https://demoqa.com/alerts");
        driver.manage().window().maximize();
        driver.findElement(By.id("timerAlertButton")).click();

        try {
            driver.switchTo().alert().accept();
        }
        catch(NoAlertPresentException e){
            System.out.println("no alert");
            System.out.println(e.getMessage());
        }
        System.out.println("Out block");

    }

    @Test
    public void invalidSelector() {
        driver.get("https://demoqa.com/frames");
        try {
            driver.findElement(By.xpath("//button[@type=#button']"));
        }
        catch (InvalidSelectorException e) {
            System.out.println("incorrect selector");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
