package Automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Automation {
    public static void main(String[] args) throws Exception {
        // Set up the ChromeDriver from maven configuration
        WebDriverManager.chromedriver().setup();
      //  System.setProperty("webdriver.chrome.driver", "/Users/vivekyaduvanshi/Documents/chromedriver");

        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("iphone");
       //  driver.quit();
        //  driver.close();
    }
}
