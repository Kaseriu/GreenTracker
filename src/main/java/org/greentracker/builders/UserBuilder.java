package org.greentracker.builders;

import org.greentracker.models.User;

public class UserBuilder {

    private User user;
    private final String userInfo;

    public UserBuilder(String userInfo) {
        this.userInfo = userInfo;
        setUser();
    }

    public User getUser() {
        return user;
    }

    private void setUser() {
        String[] userInfo = this.userInfo.replaceAll("[{}\\[\\]]", "").split(",");
        String[] id = userInfo[0].split(":");
        String[] name = userInfo[1].split(":");
        String[] email = userInfo[2].split(":");
        this.user = new User(
                Integer.parseInt(id[1]),
                name[1].replace("\"", ""),
                email[1]);
    }
}
