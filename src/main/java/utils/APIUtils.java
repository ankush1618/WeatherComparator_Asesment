package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class APIUtils {
    public static Response getRequest(String endpoint, Map<String, String> headers) {
        return RestAssured.given().headers(headers).get(endpoint);
    }
}

