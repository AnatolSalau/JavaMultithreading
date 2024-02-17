package itvdn._2_callable_future.sample.weather;

import java.io.IOException;
import java.net.http.HttpClient;

public class Main {
      private static final String OPEN_WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?lat=53.90&lon=27.55&appid=252c0950663084f347d9b3f79b8f25ba";
      private static final String TOMORROW_WEATHER_URL = "https://api.tomorrow.io/v4/weather/forecast?location=53.9006,27.5590&apikey=Ibadk8a0n9R27L1gXnOTi0Q4f6tsJAuS";
      private static final String WEATHERBIT_WEATHER_URL = "https://api.weatherbit.io/v2.0/current?lat=53.9006&lon=27.5590&key=e1c7e6088ca549458378472403c3a03b&include=minutely";

      private static final WeatherUtil weatherUtil = new WeatherUtil();

      public static void main(String[] args) {
            testSynchronous();

      }

      private static void testSynchronous() {
            try(HttpClient httpClient = HttpClient.newHttpClient()) {
                  long start = System.currentTimeMillis();
                  String openWeather = weatherUtil.getWeather(httpClient, OPEN_WEATHER_URL);
                  String tomorrowWeather = weatherUtil.getWeather(httpClient, TOMORROW_WEATHER_URL);
                  String weatherbitWeather = weatherUtil.getWeather(httpClient, WEATHERBIT_WEATHER_URL);
                  long end = System.currentTimeMillis();
                  long duration = end - start;
                  System.out.println("Test 1 synchronous");
                  System.out.println(duration);
                  System.out.println();

            } catch (IOException | InterruptedException e) {
                  throw new RuntimeException(e);
            }
      }

      private static void testAsynchronous() {

      }
}
