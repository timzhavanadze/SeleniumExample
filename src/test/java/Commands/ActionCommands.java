package Commands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class ActionCommands {
    @Test
    public void holdAndRelease() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String url = "https://jqueryui.com/slider/";
        driver.get(url);
        driver.manage().window().maximize();
        try
        {
            /* create an object for the Actions class and pass the driver argument */
            Actions action = new Actions(driver);
            driver.switchTo().frame(0);
            WebElement elemSlider = driver.findElement(By.cssSelector(".ui-slider-handle"));
            action
                    .clickAndHold(elemSlider)
                    .moveByOffset(100,0)
                    .release()
                    .perform();
            sleep(5000);
            System.out.println("Drag & Drop test case successful");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        driver.quit();
    }
    @Test
    public void contextClick() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/context_menu");
        driver.manage().window().maximize();

        WebElement element= driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions
                .contextClick(element)
                .perform();
        sleep(3000);
        driver.quit();
    }
    @Test
    public void dragNDrop() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://jqueryui.com/droppable/");
        driver.manage().window().maximize();
        driver.switchTo().frame(0);
        WebElement SourceElement= driver.findElement(By.id("draggable"));
        WebElement TargetElement= driver.findElement(By.id("droppable"));
        Actions action = new Actions(driver);
        sleep(3000);
        action
                .dragAndDrop(SourceElement, TargetElement)
                .build().perform();
//        action.clickAndHold(SourceElement).moveToElement(TargetElement).release().perform();
        sleep(3000);
        driver.quit();
    }
    @Test
    public void doKeyPresses() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/key_presses");
        driver.manage().window().maximize();

        WebElement element= driver.findElement(By.id("target"));
        element.sendKeys("test");
        element.sendKeys(Keys.CONTROL,"A");
        sleep(3000);
        element.sendKeys(Keys.DELETE);
        sleep(3000);
        driver.quit();
    }

}