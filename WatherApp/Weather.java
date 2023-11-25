package WatherApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    private static final String API_KEY = "052879b8a928a135256a4508f5bcf5ba";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the city name: ");
        String city = scanner.nextLine();

        try {
            String weatherData = getWeatherData(city);
            System.out.println("Weather information for " + city + ":\n" + weatherData);
        } catch (IOException e) {
            System.err.println("Error fetching weather data: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static String getWeatherData(String city) throws IOException {
        String urlString = API_URL + "?q=" + city + "&appid=" + API_KEY;

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();
        } finally {
            connection.disconnect();
        }
    }
}
