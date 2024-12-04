package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OpenWeatherAPI {
    private static final String API_KEY = "7fe67bf08c80ded756e598d6f8fedaea";

    public double getTemperature(String city) {
        Response response = RestAssured
                .given()
                .baseUri("http://api.openweathermap.org/data/2.5/weather")
                .queryParam("q", city)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .get();

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("API response failed: " + response.getStatusLine());
        }

        return response.jsonPath().getDouble("main.temp");
    }
}
