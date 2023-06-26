package Commands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;


public class BrowserCommands {
    @Test
    public void getInfo() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/windows");
        driver.manage().window().maximize();

        System.out.println("Title " + driver.getTitle().startsWith("The"));
        System.out.println("URL " + driver.getCurrentUrl());
        System.out.println("Page Source " + driver.getPageSource());
        System.out.println("Page Source Contains " + driver.getPageSource().contains("testtest"));
        // driver.findElement(By.xpath("//*[text()='Click Here']")).click();
        driver.quit();
    }
    @Test
    public void close() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://uitestpractice.com");
        driver.manage().window().maximize();
        sleep(4000);
        driver.close();
    }
}