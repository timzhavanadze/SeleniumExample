package SwitchToAndTables;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.plaf.IconUIResource;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SwitchTo {
    WebDriver driver;
    @BeforeMethod
    public void open() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void frameCount() {
        driver.get("https://www.hyrtutorials.com/p/frames-practice.html");
        List<WebElement> iframeElements = driver.findElements(By.tagName("iframe"));
        System.out.println(iframeElements.size());
        for(int i=0; i<iframeElements.size(); i++){
            System.out.println(iframeElements.get(i).getAttribute("id"));
        }
    }
    @Test
    public void switchToFrame() {
        driver.get("https://demoqa.com/frames");
        //driver.switchTo().frame(0);
        driver.switchTo().frame("frame1");
        System.out.println(driver.findElement(By.id("sampleHeading")).getText());
    }

    @Test
    public void switchToFrame1() throws InterruptedException {
        driver.get("https://www.hyrtutorials.com/p/frames-practice.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("name")).sendKeys("TestAutomation");
        driver.switchTo().frame("frm1"); //try with webelement
        Select course = new Select(driver.findElement(By.id("course")));
        course.selectByVisibleText("Java");
        Thread.sleep(5000);

//        driver.findElement(By.id("name")).sendKeys("UpdatedText");
//        Thread.sleep(5000);
    }

    @Test
    public void switchToNestedFrame() throws InterruptedException {
        driver.get("https://www.hyrtutorials.com/p/frames-practice.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("name")).sendKeys("TestAutomation");
        driver.switchTo().frame(driver.findElement(By.id("frm3")));
        driver.switchTo().frame(driver.findElement(By.id("frm1")));
        Select course = new Select(driver.findElement(By.id("course")));
        course.selectByVisibleText("Java");
        Thread.sleep(5000);

//        driver.switchTo().parentFrame();  //try switch to defaultContent
//        driver.findElement(By.id("name")).sendKeys("this is frame 3");
//        Thread.sleep(5000);

//        driver.switchTo().defaultContent();
//        driver.findElement(By.id("name")).clear();
//        driver.findElement(By.id("name")).sendKeys("this is main page");
//        Thread.sleep(5000);
    }

    @Test
    public void switchToNestedFrames() {
        driver.get("http://the-internet.herokuapp.com/nested_frames");
        driver.switchTo().frame("frame-top")
                .switchTo()
                .frame("frame-left");
        System.out.println(driver.findElement(By.tagName("body")).getText());
        driver.switchTo().defaultContent();
        System.out.println(driver.findElement(By.tagName("frame")).getAttribute("name"));
    }

    @Test
    public void handleAlerts() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.id("alertButton")).click();
        Thread.sleep(5000);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        Thread.sleep(5000);
    }

    @Test
    public void catchWindows() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");

        driver.findElement(By.id("windowButton")).click();
        String mainWindow = driver.getWindowHandle();
        // To handle all new opened window.
        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> iterator = allWindows.iterator();

        while (iterator.hasNext()) {
            String childWindow = iterator.next();
            if (!mainWindow.equalsIgnoreCase(childWindow)) {
                // Switching to Child window
                driver.switchTo().window(childWindow);
                Thread.sleep(5000);
                WebElement text = driver.findElement(By.id("sampleHeading"));
                System.out.println("Heading of child window is " + text.getText());
                // Closing the Child Window.
                driver.close();
                Thread.sleep(5000);
                System.out.println("Child window closed");
            }
        }
        // Switching to Parent window i.e Main Window.
        driver.switchTo().window(mainWindow);
    }
    @Test
    public void WindowHandle() throws InterruptedException {
        //Launching the site.
        driver.get("http://demo.guru99.com/popup.php");
        driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();
        Thread.sleep(5000);
        String mainWindow=driver.getWindowHandle();
        // To handle all new opened window.
        Set<String> s1=driver.getWindowHandles();
        Iterator<String> i1=s1.iterator();
        while(i1.hasNext())
        {
            String childWindow=i1.next();
            if(!mainWindow.equalsIgnoreCase(childWindow))
            {
                // Switching to Child window
                driver.switchTo().window(childWindow);
                driver.findElement(By.name("emailid"))
                        .sendKeys("test@gmail.com");
                driver.findElement(By.name("btnLogin")).click();
                Thread.sleep(5000);
                // Closing the Child Window.
                driver.close();
                Thread.sleep(5000);
            }
        }
        // Switching to Parent window i.e Main Window.
        driver.switchTo().window(mainWindow);
    }

    @Test
    public void switchToActiveElement() throws InterruptedException {
        driver.get("https://www.google.com/");
        Thread.sleep(5000);
        WebElement activeElement = driver.switchTo().activeElement();
        activeElement.sendKeys("Test");
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
