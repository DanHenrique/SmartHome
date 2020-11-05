package SmartHome.services;

import SmartHome.models.RequestBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public final class HomeAssistant {
    //private static String url = "http://raspberrypi:8123/api";
    private static String url = "http://localhost:5555/test";

    public static String getURL(){
        return url;
    }

    public static boolean callService(String domain, String service, RequestBody body) throws IOException {
        URL endpoint = new URL(url + "/services/" + domain + "/service");

        HttpURLConnection conn = (HttpURLConnection) endpoint.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        String jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";

        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        //ObjectMapper a = new ObjectMapper

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
        return true;
    }
}
