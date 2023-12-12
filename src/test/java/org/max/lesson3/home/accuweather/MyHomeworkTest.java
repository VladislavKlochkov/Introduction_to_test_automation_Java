package org.max.lesson3.home.accuweather;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.lesson3.home.accuweather.location.Location;
import org.max.lesson3.home.accuweather.weather.Weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MyHomeworkTest extends AccuweatherAbstractTest {
    @Test
    void getWeatherOneDay() {
        Weather oneDayWeather = given()
                .queryParams("apikey", getApiKey()).pathParam("locationKey", 134)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/1day/{locationKey}")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .response()
                .body()
                .as(Weather.class);
        Assertions.assertEquals(1, oneDayWeather.getDailyForecasts().size());
    }

    @Test
    void getWeatherTenDay() {
        Weather tenDayWeather = given()
                .queryParams("apikey", getApiKey()).pathParam("locationKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/10day/{locationKey}")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .response()
                .body()
                .as(Weather.class);
        Assertions.assertEquals(10, tenDayWeather.getDailyForecasts().size());
    }

    @Test
    void getWeatherFifteenDay() {
        Weather fifteenDayWeather = given()
                .queryParams("apikey", getApiKey()).pathParam("locationKey", 429)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/15day/{locationKey}")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .response()
                .body()
                .as(Weather.class);
        Assertions.assertEquals(15, fifteenDayWeather.getDailyForecasts().size());
    }

    @Test
    void getLocation() {
        Map<String, String> mapQuery = new HashMap<>();
        mapQuery.put("apikey", getApiKey());
        mapQuery.put("q", "Engels");
        List<Location> listLocations = given().queryParams(mapQuery)
                .when()
                .get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertAll(() -> Assertions.assertEquals(10, listLocations.size()),
                () -> Assertions.assertEquals("Engelsdorf", listLocations.get(2).getLocalizedName()));
    }
}
