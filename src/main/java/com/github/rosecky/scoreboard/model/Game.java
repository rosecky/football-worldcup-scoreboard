package com.github.rosecky.scoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class Game {
    @NonNull
    private final Team homeTeam;
    @NonNull
    private final Team awayTeam;
    @NonNull
    private Score currentScore;

    public static Game withTeams(Team homeTeam, Team awayTeam) {
        return withTeamsAndScore(homeTeam, awayTeam, Score.initial());
    }
    public static Game withTeamsAndScore(Team homeTeam, Team awayTeam, Score score) {
        return new Game(homeTeam, awayTeam, score);
    }

    public void updateScore(Score newCurrentScore) {
        currentScore = newCurrentScore;
    }
}
