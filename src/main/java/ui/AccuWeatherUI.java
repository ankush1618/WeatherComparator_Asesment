package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AccuWeatherUI {
    private WebDriver driver;

    public AccuWeatherUI() {
        // Get the path of the ChromeDriver from the resources folder
        String chromeDriverPath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        this.driver = new ChromeDriver();
    }


    public double getTemperature(String city) throws InterruptedException {
        // Navigate to the AccuWeather homepage
        driver.get("https://www.accuweather.com/");
        driver.manage().window().maximize();

        // Initialize WebDriverWait with a 10-second timeout
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait for the search box to be clickable and enter the city
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".search-input")));
        searchBox.sendKeys(city);
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(10000);
        WebElement citySelected = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@class='location-name' and contains(text(), '"+ city + "')])[1]")));
        citySelected.click();

        // Wait for the temperature element to be visible
        WebElement tempElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='header-city-link']//span[@class='header-temp']")));
        // Get the temperature text, remove the degree symbol, and parse the value
        String tempText = tempElement.getText().replaceAll("[°C]", "");

        // Close the browser session
        driver.quit();
        // Convert the temperature text to a double and return it
        return Double.parseDouble(tempText);
    }

}
