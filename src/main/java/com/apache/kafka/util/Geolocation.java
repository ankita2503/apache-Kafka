package com.apache.kafka.util;

import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class Geolocation {

    public static String getLocation() {
        String ip = "8.8.8.8"; // IP address for which you want to get the geolocation

        double latitude = 0;
        double longitude = 0;
        try {
            URL url = new URL("http://ip-api.com/json/" + ip); // API endpoint for geolocation lookup
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            JSONObject json = new JSONObject(response.toString());
            latitude = json.getDouble("lat");
            longitude = json.getDouble("lon");
            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.valueOf(latitude) + String.valueOf(longitude);
    }
}

