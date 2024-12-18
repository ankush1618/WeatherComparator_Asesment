package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelUtility;

import java.io.IOException;

public class WeatherTest {
    @DataProvider(name = "WeatherData")
    public Object[][] fetchData() throws IOException {
        String excelpath = System.getProperty("user.dir") + "\\src\\main\\resources\\TestData.xlsx";
        ExcelUtility excel = new ExcelUtility(excelpath, "Sheet1");
        return excel.getData();
    }

    @Test(dataProvider = "WeatherData")
    public void compareWeather(String city, String allowedVariance) {
        // Use city and allowedVariance dynamically
        System.out.println("Testing for City: " + city + " with Variance: " + allowedVariance);
    }
}

