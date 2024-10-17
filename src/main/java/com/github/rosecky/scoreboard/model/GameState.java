package com.github.rosecky.scoreboard.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class GameState {
    @NonNull
    private final Team homeTeam;
    @NonNull
    private final Team awayTeam;
    @NonNull
    private final Score score;

    public static GameState initialWithTeams(Team homeTeam, Team awayTeam) {
        return withTeamsAndScore(homeTeam, awayTeam, Score.initial());
    }
    public static GameState withTeamsAndScore(Team homeTeam, Team awayTeam, Score score) {
        return new GameState(homeTeam, awayTeam, score);
    }
}
