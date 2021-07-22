package org.greentracker.requests;

import org.greentracker.builders.StateBuilder;
import org.greentracker.models.Session;
import org.greentracker.models.User;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.greentracker.App.API_URI;

public class TicketRequest {
    static public String getAllTickets(Session session) throws Exception {
        URL url = new URL(API_URI + "java-api/ticket");
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

    static public String getUserTickets(Session session, User user) throws Exception {
        URL url = new URL(API_URI + "java-api/ticket/user/" + user.getId());
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

    static public String getTicketByName(Session session, String name) throws Exception {
        if (!name.isEmpty()) {
            URL url = new URL(API_URI + "java-api/ticket/" + name);
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
                System.out.println("Il n'y a aucun ticket de ce nom");
                return null;
            }
        }
        System.out.println("Veuillez renseigner un nom");
        return null;
    }

    static public void createTicket(Session session, User user, String[] ticketInfo) throws Exception {
        URL url = new URL(API_URI + "java-api/ticket/add");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Bearer " + session.getToken());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", ticketInfo[0]);
        jsonObject.put("description", ticketInfo[1]);
        jsonObject.put("assignee", ticketInfo[2]);
        jsonObject.put("id_user", user.getId());
        jsonObject.put("id_state", new StateBuilder(StateRequest.getAllStates(session)).getStateList().get(0).getId());

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
        outputStreamWriter.write(jsonObject.toString());
        outputStreamWriter.close();

        if (connection.getResponseCode() == 201) {
            System.out.println("Ticket créé");
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

    static public void updateTicket(Session session, String ticketName, String[] ticketInfo) throws Exception {
        URL url = new URL(API_URI + "java-api/ticket/" + ticketName);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Bearer " + session.getToken());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("PUT");

        JSONObject jsonObject = new JSONObject();
        if (!ticketInfo[0].isEmpty()) {
            jsonObject.put("name", ticketInfo[0]);
        }
        if (!ticketInfo[1].isEmpty()) {
            jsonObject.put("description", ticketInfo[1]);
        }
        if (!ticketInfo[2].isEmpty()) {
            jsonObject.put("assigneeName", ticketInfo[2]);
        }
        if (!ticketInfo[3].isEmpty()) {
            jsonObject.put("stateName", ticketInfo[3]);
        }

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
        outputStreamWriter.write(jsonObject.toString());
        outputStreamWriter.close();

        if (connection.getResponseCode() == 200) {
            System.out.println("Ticket modifié");
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

    static public void deleteTicket(Session session, String name) throws Exception {
        URL url = new URL(API_URI + "java-api/ticket/" + name);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Bearer " + session.getToken());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("DELETE");
        connection.getResponseCode();
    }
}
