package Automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    public static void main(String[] args) throws Exception {
        // Set up the ChromeDriver from maven configuration
        WebDriverManager.chromedriver().setup();
        //  System.setProperty("webdriver.chrome.driver", "/Users/vivekyaduvanshi/Documents/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        //open the ChromeDriver and navigate to the website
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("visual_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/input")).click();
        Thread.sleep(5000);
        // close the ChromeDriver only the current tab
        driver.close();
        // quit the Chromebroswer all tabs
        driver.quit();
    }
}
