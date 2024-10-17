package com.github.rosecky.scoreboard.model;

import lombok.Data;

@Data
public class Score {
    private final int homeTeamPoints;
    private final int awayTeamPoints;

    public Score(int homeTeamPoints, int awayTeamPoints) {
        if (homeTeamPoints < 0 || awayTeamPoints < 0) {
            throw new IllegalArgumentException("Score must be non-negative");
        }
        this.homeTeamPoints = homeTeamPoints;
        this.awayTeamPoints = awayTeamPoints;
    }

    public static Score initial() {
        return new Score(0, 0);
    }

    public int getTotal() {
        return homeTeamPoints + awayTeamPoints;
    }
}
