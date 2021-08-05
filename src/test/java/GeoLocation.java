import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class GeoLocation {

        WebDriver driver;
        DevTools devTools;
        WebDriverWait wait;

        @BeforeMethod
        void Login() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            devTools = ((ChromeDriver) driver).getDevTools();

            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }

        @Test
        public void emulateGeoLocation() throws InterruptedException {

            driver.get("https://mylocation.org/");

            WebElement geoLocation = driver.findElement(By.cssSelector("#ui-accordion-accordion-header-1"));

            geoLocation.click();

            Thread.sleep(3000);

            WebElement startTest = driver.findElement(By.xpath("//span[.='Start Test']"));

            startTest.click();

            Thread.sleep(8000);


            Map<String, Object> params = new HashMap<>();
            params.put("latitude", 34.9333);
            params.put("longitude", 76.7667);
            params.put("accuracy", 1);

            ((ChromeDriver) driver).executeCdpCommand("Emulation.setGeolocationOverride", params);

            driver.get("https://mylocation.org/");

            Thread.sleep(5000);
            WebElement geoLocation1 = driver.findElement(By.xpath("//h3[.='Browser Geolocation']"));

            geoLocation1.click();

            Thread.sleep(8000);

            WebElement startTest1 = driver.findElement(By.xpath("(//button/span[@class='ui-button-text'])[1]"));

            startTest1.click();

            Thread.sleep(8000);

            driver.quit();

        }
}
