package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccuWeatherPage {
    @FindBy(id = "city-input")
    WebElement cityInput;

    @FindBy(id = "search-btn")
    WebElement searchButton;

    @FindBy(xpath = "//span[@class='temperature']")
    WebElement temperatureText;

    public AccuWeatherPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void searchCity(String city) {
        cityInput.sendKeys(city);
        searchButton.click();
    }

    public String getTemperature() {
        return temperatureText.getText();
    }
}

