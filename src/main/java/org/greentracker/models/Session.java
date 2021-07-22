package org.greentracker.models;

public class Session {
    private final int sessionId;
    private final String token;
    private final int userId;

    public Session(int sessionId, String token, int userId) {
        this.sessionId = sessionId;
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public int getUserId() {
        return userId;
    }
}
