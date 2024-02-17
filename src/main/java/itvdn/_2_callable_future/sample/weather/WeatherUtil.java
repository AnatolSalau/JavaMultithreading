package itvdn._2_callable_future.sample.weather;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class WeatherUtil {
      String getWeather(HttpClient httpClient, String url) throws IOException, InterruptedException {
            HttpRequest request = HttpRequest.newBuilder()
                  .uri(URI.create(url))
                  .build();

            HttpResponse<String> response = httpClient.send(request,
                  HttpResponse.BodyHandlers.ofString());

            String str = response.body();

            return str;
      }
}
