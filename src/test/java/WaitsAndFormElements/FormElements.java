package WaitsAndFormElements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class FormElements {
    WebDriver driver;

    @BeforeMethod
    public void  open() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void radioButtonExample() throws InterruptedException {
        driver.get("http://demo.guru99.com/test/radio.html");
        WebElement radio1 = driver.findElement(By.id("vfb-7-1"));
        WebElement radio2 = driver.findElement(By.id("vfb-7-2"));

        //Radio Button1 is selected
        radio1.click();
        Thread.sleep(5000);
        System.out.println("Radio Button Option 1 Selected");

        //Radio Button1 is de-selected and Radio Button2 is selected
        radio2.click();
        Thread.sleep(5000);
        System.out.println("Radio Button Option 2 Selected");
    }

    @Test
    public void checkRadioButton() throws InterruptedException {
        driver.get("https://techcanvass.com/examples/register.html");
        driver.manage().window().maximize();
        WebElement male = driver.findElement(By.cssSelector("input[value='male']"));
        WebElement female = driver.findElement(By.cssSelector("input[value='female']"));
        System.out.println(male.isSelected());
        if (!female.isSelected())
            female.click();
        Thread.sleep(5000);
    }

    @Test
    public void isSelected() throws InterruptedException {
        driver.get("https://techcanvass.com/examples/register.html");
        //List<WebElement> radio = driver.findElements(By.xpath("//input[@type='radio']"));
        List<WebElement> radio = driver.findElements(By.name("gender"));

        boolean isSelectedValue = radio.get(0).isSelected();
        System.out.println(isSelectedValue);
        if (isSelectedValue = true) {
            radio.get(1).click(); // if the first radio button is selected by default, this will select Second radio button
            Thread.sleep(5000);
        } else {
            radio.get(0).click(); // if the first radio button is not selected by default, the first will be selected
            Thread.sleep(5000);
        }
    }

    @Test
    public void usingValue() throws InterruptedException {
        driver.get("https://techcanvass.com/examples/register.html");
        List<WebElement> RadioButton = driver.findElements(By.name("gender"));
        int Size = RadioButton.size();
        for (int i = 0; i < Size; i++) {
            String value = RadioButton.get(i).getAttribute("value");
            System.out.println(value);
            if (value.equalsIgnoreCase("Female")) {
                RadioButton.get(i).click();
                Thread.sleep(5000);
            }
            else {
                System.out.println("value mnishvneloba aris male");
            }
        }
    }

    @Test
    public void checkboxes() throws InterruptedException {
        driver.get("http://demo.guru99.com/test/radio.html");
        WebElement option1 = driver.findElement(By.id("vfb-6-0"));
//    WebElement option1 = driver.findElement(By.xpath("//input[@id='vfb-6-0']"));
//    WebElement option1 = driver.findElement(By.xpath("//input[@value='checkbox1']"));
//    WebElement option1 = driver.findElement(By.cssSelector("input[value='checkbox1']"));
        option1.click();
        Thread.sleep(5000);
        if (option1.isSelected()) {
            System.out.println("option1 is selected");

        } else {
            System.out.println("option1 is not selected");
        }
    }
    @Test
    public void selectFromDropDown() throws InterruptedException {
        driver.get("https://demo.guru99.com/test/newtours/register.php");
        //Declare the drop-down element as an instance of the Select class
        Select dropdown = new Select(driver.findElement(By.cssSelector("select[name='country']")));
        dropdown.selectByValue("GEORGIA");
        Thread.sleep(5000);
        System.out.println(dropdown.isMultiple());
    }

    @Test
    public void selectMultiple() throws InterruptedException {
        driver.get("https://demoqa.com/select-menu");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("cars")));
        Select name = new Select(driver.findElement(By.id("cars")));
        System.out.println("is multiple: " + name.isMultiple());
        if(name.isMultiple()) {
            name.selectByIndex(2);
            name.selectByIndex(3);
            Thread.sleep(5000);
            name.deselectByValue("opel");
            Thread.sleep(5000);
        }
    }
    @Test
    public void get() throws InterruptedException {
        driver.get("https://demoqa.com/select-menu");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("cars")));
        Select name = new Select(driver.findElement(By.id("cars")));
        name.selectByIndex(2);
        name.selectByIndex(3);
        Thread.sleep(5000);
        List<WebElement> allOptions =  name.getOptions();
        for (WebElement allOption : allOptions) {
            System.out.println(allOption.getText());
        }
        List<WebElement> selectedOptions =  name.getAllSelectedOptions();
        for (WebElement selectedOption : selectedOptions) {
            System.out.println(selectedOption.getText());
        }
    }

    @Test
    public void getOptionFromDropDown_Angular() throws InterruptedException {
        driver.get("https://ng-bootstrap.github.io/#/components/dropdown/examples");
        driver.manage().window().maximize();
        driver.findElement(By.id("dropdownBasic1")).click();
        Thread.sleep(5000);
        List<WebElement> dropDown = driver.findElements(By.xpath("//div[@class='dropdown-menu show']/button"));
        System.out.println(dropDown.get(1).getText());
        dropDown.get(1).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}