package com.github.rosecky.scoreboard.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Team {
    @NonNull
    private final String name;
    public static Team withName(String name) {
        return new Team(name);
    }
}
