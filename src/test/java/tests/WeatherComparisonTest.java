package tests;

import api.OpenWeatherAPI;
import comparator.WeatherComparator;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.AccuWeatherUI;
import utils.ConfigReader;
import utils.Log;

public class WeatherComparisonTest {
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setupReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Weather Comparator");
        sparkReporter.config().setReportName("Weather Test Automation");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @Test
    public void compareWeather() throws InterruptedException {
        test = extent.createTest("Weather Comparison Test");

        // Fetch parameters
        String city = System.getProperty("CITY", ConfigReader.getProperty("city")); // Jenkins parameter or properties file
        double allowedVariance = Double.parseDouble(System.getProperty("ALLOWED_VARIANCE", ConfigReader.getProperty("allowedVariance")));

        test.info("Starting weather comparison test for city: " + city);

        AccuWeatherUI ui = new AccuWeatherUI();
        OpenWeatherAPI api = new OpenWeatherAPI();
        WeatherComparator comparator = new WeatherComparator();

        try {
            Log.info("Fetching temperature from UI...");
            double tempUI = ui.getTemperature(city);
            test.info("Temperature from UI: " + tempUI);

            Log.info("Fetching temperature from API...");
            double tempAPI = api.getTemperature(city);
            test.info("Temperature from API: " + tempAPI);

            Log.info("Comparing temperatures...");
            comparator.compareTemperatures(tempUI, tempAPI, allowedVariance);

            test.pass("Weather comparison test passed. UI: " + tempUI + ", API: " + tempAPI);
            test.info("Weather comparison test passed.");
        } catch (Exception e) {
            test.fail("Weather comparison test failed: " + e.getMessage());
            Log.error("Test failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Helper method to fetch Jenkins parameter or default to properties file value.
     *
     * @param paramName    Jenkins parameter name
     * @param defaultValue Default value from properties file
     * @return Parameter value
     */
    private String getParameter(String paramName, String defaultValue) {
        String paramValue = System.getProperty(paramName); // Fetch from Jenkins
        return (paramValue != null && !paramValue.isEmpty()) ? paramValue : defaultValue;
    }

    @AfterClass
    public void tearDownReport() {
        extent.flush();
    }
}
