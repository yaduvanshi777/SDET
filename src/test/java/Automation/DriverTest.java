package Automation;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;


public class DriverTest {
    public static String browserName = "chrome"; // this can used as the csv or xls file
    public static WebDriver driver;

    public static void main(String[] args) throws Exception {

        if(browserName.equals("safari"))
        {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }
        else if(browserName.equals("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

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
