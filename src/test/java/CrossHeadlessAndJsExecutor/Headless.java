package CrossHeadlessAndJsExecutor;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Headless {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new HtmlUnitDriver();
    }

    @Test
    public void handleStaticTable() throws InterruptedException {
        driver.get("http://techcanvass.com/Examples/webtable.html");
        //Thread.sleep(5000);
        driver.manage().window().maximize();
        for(int rowno=2;rowno<=4;rowno++)
        {
            for(int colno=1;colno<=3;colno++)
            {
                System.out.println(driver.findElement(By.xpath("//*[@id='t01']/tbody/tr[" + rowno + "]/td[" + colno + "]")).getText());
            }
        }
    }

    @Test
    public void test() {
        driver.get("http://demo.guru99.com/test/radio.html");
        WebElement option1 = driver.findElement(By.id("vfb-6-0"));
        option1.click();
        if (option1.isSelected()) {
            System.out.println("option1 is selected");

        } else {
            System.out.println("option1 is not selected");
        }
    }

    @Test
    public void headlsess(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        ChromeDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get("http://webdriveruniversity.com/Scrolling/index.html");
        JavascriptExecutor js= (JavascriptExecutor)driver;
        String checkboxText= js.executeScript("return document.getElementById('zone2-entries').innerText;").toString();
        System.out.println(checkboxText);
    }

}
