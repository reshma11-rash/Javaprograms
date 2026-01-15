import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class RestApiClient {

    // URL of the public API
    static String apiUrl = "https://api.agify.io/?name=reshma"; // Example API

    public static void main(String[] args) {
        fetchData();
    }

    // Method to fetch data from API
    public static void fetchData() {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Reading the response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response
            JSONObject json = new JSONObject(response.toString());
            System.out.println("API Response: " + json.toString(4)); // Pretty print

        } catch (Exception e) {
            System.out.println("Error fetching API: " + e.getMessage());
        }
    }
}
