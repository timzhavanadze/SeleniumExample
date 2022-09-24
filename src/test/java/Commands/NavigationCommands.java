package Commands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class NavigationCommands {

   @Test
   public void navigate() throws InterruptedException {
      //System.setProperty("webdriver.chrome.driver","D:\\ChromeDriver\\chromedriver.exe");
      WebDriverManager.chromedriver().setup();
      WebDriver driver = new ChromeDriver();
      // driver.get("https://the-internet.herokuapp.com/");
      driver.navigate().to("https://the-internet.herokuapp.com/");
      driver.manage().window().maximize();
      driver.get("https://classroom.google.com/");
      driver.navigate().back();
      Thread.sleep(3000);
      driver.navigate().forward();
      Thread.sleep(3000);
      driver.navigate().refresh();
      Thread.sleep(3000);
      driver.quit();
   }
}