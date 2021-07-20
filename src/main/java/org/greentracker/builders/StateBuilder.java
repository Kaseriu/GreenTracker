package org.greentracker.builders;

import org.greentracker.models.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StateBuilder {

    public List<State> stateList = new ArrayList<>();
    public String allState;

    public StateBuilder(String allState) {
        this.allState = allState;
        setStateList();
    }

    public List<State> getStateList() {
        return this.stateList;
    }

    private void setStateList() {
        String[] states = allState.split("},\\{");
        for (String state : states) {
            String[] tmp = Arrays.toString(state.split(",")).replaceAll("[{}\\[\\]]", "").split(",");
            String[] id = tmp[0].split(":");
            String[] name = tmp[1].split(":");
            this.stateList.add(new State(Integer.parseInt(id[1]), name[1].replace("\"", "")));
        }
    }
}
