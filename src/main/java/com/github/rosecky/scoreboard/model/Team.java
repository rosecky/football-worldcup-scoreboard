package com.github.rosecky.scoreboard.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Team {
    @NonNull
    private final String name;
}
