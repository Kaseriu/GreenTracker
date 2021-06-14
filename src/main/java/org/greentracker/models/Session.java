package org.greentracker.models;

public class Session {
    private int sessionId;
    private String token;
    private int userId;

    public Session(int sessionId, String token, int userId) {
        this.sessionId = sessionId;
        this.token = token;
        this.userId = userId;
    }
}
