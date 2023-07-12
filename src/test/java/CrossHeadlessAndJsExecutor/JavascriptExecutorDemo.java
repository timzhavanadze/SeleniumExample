package CrossHeadlessAndJsExecutor;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


public class JavascriptExecutorDemo {

    static WebDriver driver;

    public JavascriptExecutorDemo(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public static void javaScriptExeMethod() throws InterruptedException {
        driver.get("https://www.gmail.com");
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/span"));
        JavascriptExecutor js = (JavascriptExecutor)driver;

        //to type text in Selenium WebDriver without using sendKeys() method
        js.executeScript("document.getElementById('identifierId').value='tmzhavanadze@gmail.com';");

        //to click a button in Selenium WebDriver using JavaScript
        js.executeScript("arguments[0].click();", loginButton);


        //or
        //       js.executeScript("document.getElementById('enter your element id').click();");

        // to get innertext of the entire webpage in Selenium
        String sText =  js.executeScript("return document.documentElement.innerText;").toString();
        System.out.println("innerText " + sText);

        //to get the Title of our webpage
        String title =  js.executeScript("return document.title;").toString();
        System.out.println("title " + title);

        //to get the domain
        Object domain =  js.executeScript("return document.domain;");
        System.out.println("domain " + domain);

        //to get the URL of our webpage
        String url =  js.executeScript("return document.URL;").toString();
        System.out.println("url " + url);

        //Navigate to new Page
        js.executeScript("window.location = 'https://www.google.com/'");
        System.out.println(driver.getTitle());
        sleep(5000);
    }

    @Test
    public void scroll() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        driver.manage().window().maximize();
        driver.get("http://moneyboats.com/");
        //Vertical scroll - down by 600  pixels
       // js.executeScript("window.scrollBy(0,600)");
        // for scrolling till the bottom of the page we can use the code like
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        sleep(4000);
        js.executeScript("window.scrollBy(0,-500)");
        sleep(4000);
    }

    @Test
    public void testTimeout() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        //Launching the Site.
        driver.get("https://demo.guru99.com/V4/");

        //Maximize window
        driver.manage().window().maximize();
        //Call executeAsyncScript() method to wait for 5 seconds
        js.executeAsyncScript("window.setTimeout(arguments[0], 5000);");
    }

    @Test
    public void checkBox() throws InterruptedException {
        driver.get("http://demo.guru99.com/test/radio.html");
        WebElement option1 = driver.findElement(By.id("vfb-6-0"));
        JavascriptExecutor js= (JavascriptExecutor)driver;
        // js.executeScript("arguments[0].click();", option1);
        js.executeScript("document.getElementById('vfb-6-0').checked=true;");
        sleep(4000);
        js.executeScript("document.getElementById('vfb-6-0').checked=false;");
        sleep(4000);
    }

    @Test
    public void doScroll() throws InterruptedException {
        driver.get("http://webdriveruniversity.com/Scrolling/index.html");
        JavascriptExecutor js= (JavascriptExecutor)driver;
        WebElement element=driver.findElement(By.id("zone4"));
        System.out.println(element.getText());
        sleep(4000);
        js.executeScript("arguments[0].scrollIntoView();",element);
        sleep(4000);
    }

    @Test
    public void navigate() throws InterruptedException {
        driver.get("https://www.google.com/");
        JavascriptExecutor js= (JavascriptExecutor)driver;
        sleep(3000);
        js.executeScript("history.go(0)");
        sleep(3000);
        driver.navigate().to("https://the-internet.herokuapp.com/");
        sleep(3000);
        js.executeScript("history.go(-1)");
        sleep(3000);
        js.executeScript("history.go(1)");
        sleep(3000);
    }

    @Test
    public void alert() throws InterruptedException {
        JavascriptExecutor js= (JavascriptExecutor)driver;
        driver.get("https://www.browserstack.com/users/sign_in");
        js.executeScript("document.getElementById('user_email_login').value='test@xyz.com';");
        js.executeScript("document.getElementById('user_password').value='1111111';");
        js.executeScript("document.getElementById('user_submit').click();");
        js.executeScript("alert('enter correct login credentials to continue');");
        sleep(20000);
    }


    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
