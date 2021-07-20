package org.greentracker.builders;

import org.greentracker.models.Session;

public class SessionBuilder {

    private Session session;
    private final String sessionInfo;

    public SessionBuilder(String sessionInfo) {
        this.sessionInfo = sessionInfo;
        setSession();
    }

    public Session getSession() {
        return session;
    }

    private void setSession() {
        String[] sessionInfo = this.sessionInfo.replaceAll("[{}\\[\\]\n]", "").split(",");
        String[] sessionId = sessionInfo[0].split(":");
        String[] token = sessionInfo[1].split(":");
        String[] userId = sessionInfo[2].split(":");
        this.session = new Session(
                Integer.parseInt(sessionId[1]),
                token[1].replace("\"", ""),
                Integer.parseInt(userId[1]));
    }
}
