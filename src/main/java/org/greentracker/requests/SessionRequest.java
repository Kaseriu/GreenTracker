package org.greentracker.requests;

import org.greentracker.App;
import org.greentracker.models.Session;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SessionRequest {

    static public String UserSubscription(String[] userInfo) {
        try {
            URL url = new URL(App.API_URI + "java-api/auth/subscribe");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", userInfo[0]);
            jsonObject.put("email", userInfo[1]);
            jsonObject.put("password", userInfo[2]);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(jsonObject.toString());
            outputStreamWriter.close();

            StringBuilder sb = new StringBuilder();
            if (connection.getResponseCode() == 201) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                return sb.toString();
            } else {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                System.out.print(sb);
                return null;
            }
        } catch (Exception exception) {
            System.out.println("API injoignable !");
            return null;
        }
    }

    static public String SessionConnection(String email, String password) {
        try {
            URL url = new URL(App.API_URI + "java-api/auth/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", email);
            jsonObject.put("password", password);


            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(jsonObject.toString());
            outputStreamWriter.close();

            StringBuilder sb = new StringBuilder();
            if (connection.getResponseCode() == 200) {
                System.out.println();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                return sb.toString();
            } else {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                System.out.print(sb);
                return null;
            }
        } catch (Exception exception) {
            System.out.println("API injoignable !");
            return null;
        }
    }

    static public void SessionDisconnection(Session session) {
        try {
            URL url = new URL(App.API_URI + "java-api/auth/logout?token=" + session.getToken());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Bearer " + session.getToken());
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("DELETE");
            connection.getResponseCode();
        } catch (Exception exception) {
            System.out.println("API injoignable !");
        }
    }
}
