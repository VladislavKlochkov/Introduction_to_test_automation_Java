package org.max.home.accu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MyTest extends AbstractTest {
    @Test
    void getWeatherOneDayStatusCode503() throws IOException {
        //given
        stubFor(get(urlPathEqualTo("/forecasts/v1/daily/1day/100"))
                .willReturn(aResponse()
                        .withStatus(503).withBody("Погода на один день!")));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(getBaseUrl() + "/forecasts/v1/daily/1day/100");
        //when
        HttpResponse response = httpClient.execute(request);
        //then
        verify(getRequestedFor(urlPathEqualTo("/forecasts/v1/daily/1day/100")));
        Assertions.assertEquals(503, response.getStatusLine().getStatusCode());
    }

    @Test
    void getWeatherTenDayStatusCode200() throws IOException {
        //given
        stubFor(get(urlPathEqualTo("/forecasts/v1/daily/10day/123"))
                .willReturn(aResponse()
                        .withStatus(200).withBody("Погода на десять дней!")));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(getBaseUrl() + "/forecasts/v1/daily/10day/123");
        //when
        HttpResponse response = httpClient.execute(request);
        //then
        verify(getRequestedFor(urlPathEqualTo("/forecasts/v1/daily/10day/123")));
        Assertions.assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    void getPostCoffeeStatusCode200() throws IOException {
        //given
        stubFor(post(urlEqualTo("/menu/coffee"))
                .withHeader("CoffeeMenu", equalTo("application/x-www-form-urlencoded"))
                .willReturn(aResponse()
                        .withStatus(200).withBody("Кофе!")));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(getBaseUrl() + "/menu/coffee");
        request.addHeader("CoffeeMenu", "application/x-www-form-urlencoded");
        //when
        HttpResponse response = httpClient.execute(request);
        //then
        verify(postRequestedFor(urlEqualTo("/menu/coffee"))
                .withHeader("CoffeeMenu", equalTo("application/x-www-form-urlencoded")));
        assertEquals(200, response.getStatusLine().getStatusCode());
    }
}
