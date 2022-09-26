package Elements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Elements {
    WebDriver driver;
    @BeforeMethod
    public void open() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void findElement() {
        driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");
        WebElement checkBox = driver.findElement(By.id("isAgeSelected"));
        checkBox.click();
        System.out.println("Clicked on the Checkbox");

        WebElement successMessage = driver.findElement(By.id("txtAge"));
        String expectedMessage = successMessage.getText();
        System.out.println(expectedMessage);
        driver.quit();
    }

    @Test
    public void findElementsByXPath() {
        driver.get("https://www.lambdatest.com/selenium-playground/");

        List<WebElement> sectionHeaders = driver.findElements(By.xpath("//h2[@class='st_heading']"));
        System.out.println(sectionHeaders.size());
        for (WebElement e : sectionHeaders) {
            System.out.println("The list of headers are:" + e.getText());
        }
        driver.quit();
    }

    @Test
    public void linkText() {
        String baseUrl = "http://demo.guru99.com/test/link.html";
        driver.get(baseUrl);
        driver.findElement(By.linkText("click here")).click();
        System.out.println("title of page is: " + driver.getTitle());
    }

    @Test
    public void partialLinkText() {
        String baseUrl = "http://demo.guru99.com/test/accessing-link.html";
        driver.get(baseUrl);
        driver.findElement(By.partialLinkText("here")).click();
        System.out.println("Title of page is: " + driver.getTitle());
    }

    @Test
    public void basicSyntax() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        driver.manage().window().maximize();
        System.out.println("text: "+ driver.findElement(By.xpath("//div[@class='example']")).getText()); //Relative Xpath
        System.out.println(driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div")).getText()); //Absolute Xpath
        driver.quit();
    }

    @Test
    public void link() {
        String baseUrl = "http://demo.guru99.com/test/accessing-link.html";
        driver.get(baseUrl);
        driver.findElement(By.xpath("//a[@href='http://www.google.com']")).click();
        System.out.println("Title of page is: " + driver.getTitle());
        driver.quit();
    }

    @Test
    public void usingOrAnd() {
        driver.get("https://demoqa.com/elements");
        System.out.println(driver.findElements(By.xpath("//li[@class='btn btn-light ' or @id='item-0']")).size());
        System.out.println(driver.findElements(By.xpath("//li[@class='btn btn-light ' and @id='item-0']")).size());
    }

    @Test
    public void containsAndChain() {
        driver.get("https://demoqa.com/elements");
        System.out.println(driver.findElements(By.xpath("//li[contains (@id, 'item')]")).size());
        System.out.println(driver.findElements(By.xpath("//*[contains (@id, 'item')]")).size());
        System.out.println(driver.findElements(By.xpath("//div[contains (@class, 'list collapse ')]")).size());
        driver.findElement(By.xpath("//ul[@class='menu-list']//li[@class='btn btn-light ']"));
    }
    @Test
    public void text() throws InterruptedException {
        driver.get("https://demoqa.com/elements");
        driver.findElement(By.xpath("//span[text()='Text Box']")).click();
        Thread.sleep(3000);
        System.out.println(driver.getCurrentUrl());
        driver.navigate().back();
        System.out.println(driver.getCurrentUrl());
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[contains(text(), 'Text')]")).click();
    }
    @Test
    public void index() {
        driver.get("https://demo.guru99.com/test/accessing-link.html");
        System.out.println(driver.findElements(By.xpath("//a[contains(text(), 'here')]")).size());
        String text = driver.findElement(By.xpath("//a[contains(text(), 'here')][1]")).getText();
        System.out.println(text);

    }
    @Test
    public void following() {
        driver.get("https://demo.guru99.com/test/selenium-xpath.html");
        System.out.println(driver.findElements(By.xpath("//*[@type='text']//following::input")).size());
        driver.findElement(By.xpath("//*[@type='text']//following::input[2]"));

        driver.findElements(By.xpath("//*[@type='text']//following::label"));
        driver.findElement(By.xpath("//*[@type='text']//following-sibling::label"));

        driver.findElements(By.xpath("//*[@type='reset']//preceding::input"));
        driver.findElements(By.xpath("//*[@type='reset']//preceding-sibling::input"));

    }
    @Test
    public void child() {
        driver.get("https://demo.guru99.com/test/selenium-xpath.html");
        driver.findElements(By.xpath("//*[@id='java_technologies']//child::li"));
    }
    @Test
    public void tagAndId() {
        driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");
        WebElement checkBox = driver.findElement(By.cssSelector("input#isAgeSelected"));
        checkBox.click();
        System.out.println("Clicked on the Checkbox");
        WebElement successMessage = driver.findElement(By.cssSelector("div#txtAge"));
        String expectedMessage = successMessage.getText();
        System.out.println(expectedMessage);
        driver.quit();
    }
    @Test
    public void tagAndClass() {
        driver.get("https://demo.guru99.com/test/selenium-xpath.html");
        WebElement heading = driver.findElement(By.cssSelector("h1.thick-heading"));
        System.out.println(heading.getText());
    }

    @Test
    public void tagAndAttribute() throws InterruptedException {
        driver.get("https://demo.guru99.com/test/selenium-xpath.html");
        WebElement passwordField = driver.findElement(By.cssSelector("input[name='password']"));
        //WebElement password = driver.findElement(By.cssSelector("input[name='password'][type='password']"));
        passwordField.sendKeys("test");
        Thread.sleep(3000);
    }

    @Test
    public void tagClassAndAttribute() {
        driver.get("https://demoqa.com/elements");
        WebElement navBar = driver.findElement(By.cssSelector("button.navbar-toggler[type='button']"));
    }

    @Test
    public void matches() {
        driver.get("https://demo.guru99.com/test/selenium-xpath.html");
        driver.findElement(By.cssSelector("input[name^='pass']"));//starts
        driver.findElement(By.cssSelector("input[name$='word']"));//ends
        driver.findElement(By.cssSelector("input[name*='word']"));//contains
        driver.findElement(By.cssSelector("input[name*='sswor']"));//contains
    }

    @Test
    public void childElements() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        driver.manage().window().maximize();
        WebElement firstCheckBox = driver.findElement(By.cssSelector("form#checkboxes input:nth-child(1)"));
        WebElement lastCheckBox = driver.findElement(By.cssSelector("form#checkboxes input:last-child"));
    }
    @AfterMethod
    public void close() {
        driver.quit();
    }
}
