package org.greentracker.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.greentracker.models.Session;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.greentracker.App.API_URI;

public class StateRequest {
    static public String getAllStates(Session session) throws Exception {
        URL url = new URL(API_URI + "java-api/state");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + session.getToken());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;

        StringBuilder response = new StringBuilder();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        return response.toString();
    }

    static public String getStateByName(Session session, String name) throws Exception {
        if (!name.isEmpty()) {
            URL url = new URL(API_URI + "java-api/state/" + name);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Bearer " + session.getToken());
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("GET");

            StringBuilder response = new StringBuilder();
            if (connection.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String output;
                while ((output = in.readLine()) != null) {
                    response.append(output);
                }
                in.close();
                return response.toString();
            } else {
                System.out.println("Il n'y a aucune State de ce nom");
                return null;
            }
        }
        System.out.println("Veuillez renseigner un nom");
        return null;
    }

    static public String GetStateName(Session session, Integer state) throws Exception {
        URL url = new URL(API_URI + "java-api/state/id/" + state);
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

    static public void createState(Session session, String stateName) throws Exception {
        URL url = new URL(API_URI + "java-api/state");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Bearer " + session.getToken());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", stateName);

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
        outputStreamWriter.write(jsonObject.toString());
        outputStreamWriter.close();

        if (connection.getResponseCode() == 200) {
            System.out.println("State créé");
        } else {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            System.out.print(sb);
        }
    }

    static public void updateState(Session session, String stateName, String newStateName) throws Exception {
        URL url = new URL(API_URI + "java-api/state/" + stateName);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Bearer " + session.getToken());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("PUT");

        JSONObject jsonObject = new JSONObject();
        if (!stateName.isEmpty()) {
            jsonObject.put("newName", newStateName);
        }

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
        outputStreamWriter.write(jsonObject.toString());
        outputStreamWriter.close();

        if (connection.getResponseCode() == 200) {
            System.out.println("State modifié");
        } else {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            System.out.print(sb);
        }
    }

    static public void deleteState(Session session, String name) throws Exception {
        URL url = new URL(API_URI + "java-api/state/" + name);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Bearer " + session.getToken());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("DELETE");
        connection.getResponseCode();
    }
}
