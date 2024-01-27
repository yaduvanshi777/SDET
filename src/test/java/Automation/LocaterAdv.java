package Automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class LocaterAdv {
    public static void main(String[] args) throws Exception {

        // Set up the ChromeDriver from maven configuration
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
      //  WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
//       // driver.findElement(WithTagName("input").above(passwordField)).sendKeys("secret_sauce");
        // Enter password
        By passwordFiel = RelativeLocator.with(By.tagName("input")).above(By.id("password"));
        driver.findElement(passwordFiel).sendKeys("visual_user");

        // Enter username
        By passwordLocator = RelativeLocator.with(By.tagName("input")).below(By.id("user-name"));
        driver.findElement(passwordLocator).sendKeys("secret_sauce");
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/input")).click();

        // Wait for 5 seconds
        Thread.sleep(5000);
        // close the ChromeDriver only the current tab
        driver.close();
        // quit the Chromebroswer all tabs
        driver.quit();

    }
}
