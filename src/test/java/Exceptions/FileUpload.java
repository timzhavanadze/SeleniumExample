package Exceptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

public class FileUpload {
    WebDriver driver;

    @BeforeMethod
    public void open() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void upload() {
        String baseUrl = "https://demo.guru99.com/test/upload/";

        driver.get(baseUrl);
        WebElement uploadElement = driver.findElement(By.id("uploadfile_0"));
        File file = new File("src/test/java/Exceptions/selenium.png");

        // enter the file path onto the file-selection input field
        uploadElement.sendKeys(file.getAbsolutePath());

        // check the "I accept the terms of service" check box
        driver.findElement(By.id("terms")).click();

        // click the "UploadFile" button
        driver.findElement(By.name("send")).click();
    }
    @Test
    public void uploadWithRobot() throws InterruptedException, AWTException {

        driver.get("http://my.monsterindia.com/create_account.html");
        // scroll to reach upload button
        JavascriptExecutor j = (JavascriptExecutor)driver;
      //  j.executeScript("scroll(0,500)");
        // file path passed as parameter to StringSelection
        StringSelection s = new StringSelection("C:\\Users\\qeti\\Desktop\\Academy\\SeleniumExample\\src\\test\\java\\Exceptions\\selenium.png");
        // Clipboard copy
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s,null);
        //identify element and click
        driver.findElement(By.xpath("//*[@id=\"basicDetails\"]/div[1]/div[4]/div[1]")).click();
        // Robot object creation
        Robot r = new Robot();
        //pressing enter
        r.keyPress(KeyEvent.VK_ENTER);
        //releasing enter
        r.keyRelease(KeyEvent.VK_ENTER);
        //pressing ctrl+
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        //releasing ctrl+v
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_V);
        //pressing enter
        r.keyPress(KeyEvent.VK_ENTER);
        //releasing enter
        r.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
