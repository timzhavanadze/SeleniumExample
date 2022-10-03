package WaitsAndFormElements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;


import org.openqa.selenium.support.ui.FluentWait;


public class Waits {
    WebDriver driver;

    @BeforeMethod
    public void open() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void waitImplicitly() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement submitButton = driver.findElement(By.xpath("//form[@id='input-example']/button[@type='button']"));
        submitButton.click();
    }

    @Test
    public void waitExplicitly() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        WebElement submitButton = driver.findElement(By.xpath("//form[@id='input-example']/button[@type='button']"));
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    @Test
    public void waitExplicitly1() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//div[@id='start']//following-sibling::button")).click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']//following::h4")));
        //new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.textToBe(By.xpath("//div[@id='finish']//following::h4"),"Hello World!"));
        //new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeSelected(By.xpath("//div[@id='finish']//following::h4")));
    }

    @Test
    public void fluentWait() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("test"))).click();
        driver.close();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
