package org.greentracker.requests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.greentracker.models.Session;
import org.greentracker.models.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static org.greentracker.App.API_URI;

public class UserRequest {
    static public String GetUserById(Session session) throws Exception {
        URL url = new URL(API_URI + "java-api/user/" + session.getUserId());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Bearer " + session.getToken());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("GET");

        int HttpResult = connection.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output;
            StringBuilder response = new StringBuilder();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();

            return response.toString();
        } else {
            System.out.println(connection.getResponseMessage());
        }
        return null;
    }

    static public String GetUserName(Session session, Integer user) throws Exception {
        URL url = new URL(API_URI + "java-api/user/" + user);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Bearer " + session.getToken());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("GET");

        int HttpResult = connection.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output;
            StringBuilder response = new StringBuilder();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();

            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> map = mapper.readValue(response.toString(), Map.class);

            return map.get("name").toString();
        } else {
            System.out.println(connection.getResponseMessage());
        }
        return null;
    }
}
