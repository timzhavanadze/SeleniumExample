package Commands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class WebElementCommands  {
    WebDriver driver;
    public WebElementCommands(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void getTextAndClear() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/inputs");
        driver.manage().window().maximize();
        System.out.println("Get text "+ driver.findElement(By.xpath("//div[@class='example']")).getText());
        WebElement element= driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/input"));
        element.sendKeys("1");
        Thread.sleep(5000);
        element.clear();
        Thread.sleep(5000);
        driver.quit();
    }
    @Test
    public void getLocation()  {
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");
        driver.manage().window().maximize();

        int element= driver.findElement(By.id("column-a")).getLocation().getX();
        System.out.println(driver.findElement(By.id("column-a")).getLocation());
        int elementB= driver.findElement(By.id("column-b")).getLocation().getX();
        System.out.println(element+ " "+elementB);
        driver.quit();
    }

    @Test
    public void checkElementStates() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        driver.manage().window().maximize();

        WebElement element= driver.findElement(By.cssSelector("#checkboxes > input[type=checkbox]:nth-child(3)"));
        element.click();
        System.out.println("isEnabled " + element.isEnabled());
        System.out.println("isDisplayed " + element.isDisplayed());
        System.out.println("isSelected " + element.isSelected());
        element.click();
        System.out.println("isSelected " + element.isSelected());
        driver.quit();
    }

    @Test
    public void getInfo() {
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");
        driver.manage().window().maximize();
        WebElement element= driver.findElement(By.id("column-a"));

        Dimension dimension= element.getSize();
        System.out.println(dimension);
        System.out.println("Height :" + dimension.height + "\n"+ "Width : "+ dimension.width);
        System.out.println("Css value " + element.getCssValue("background-color"));

        Point point = element.getLocation();
        System.out.println("X cordinate : " + point.x + "\n"+ "Y cordinate: " + point.y);

        System.out.println("getAttribute : " + element.getAttribute("id"));
        System.out.println("getTagName : " + element.getTagName());
        driver.quit();
    }

    @Test
    public void submit() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/forgot_password");
        driver.manage().window().maximize();
        WebElement element= driver.findElement(By.id("email"));
        element.sendKeys("timzhavanadze@gmail.com");
        Thread.sleep(3000);
        element.submit();
        driver.quit();
    }

    @Test
    public void findElements() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        driver.manage().window().maximize();
        WebElement addButton=driver.findElement(By.xpath("/html/body/div[2]/div/div/button"));
        addButton.click();
        Thread.sleep(2000);
        WebElement element= driver.findElement(By.cssSelector(".added-manually"));
        Thread.sleep(2000);
        element.click();
        for (int i = 0; i <4 ; i++) {
            addButton.click();
            Thread.sleep(2000);
        }

        WebElement deleteButton1=driver.findElement(By.className("added-manually"));
        WebElement deleteButton2=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/button"));
        WebElement deleteButton3=driver.findElement(By.xpath("//button[@class='added-manually']"));
        driver.quit();
    }

    @Test
    public void getInfo1() {
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");
        driver.manage().window().maximize();
        WebElement element= driver.findElement(By.id("column-a"));
        WebElement elementB= driver.findElement(By.id("column-b"));


        Point point = element.getLocation();
        System.out.println("X cordinate : " + point.x + "\n"+ "Y cordinate: " + point.y);

        Point pointB = elementB.getLocation();
        System.out.println("X cordinate : " + pointB.x + "\n"+ "Y cordinate: " + pointB.y);
        driver.quit();
    }


}
