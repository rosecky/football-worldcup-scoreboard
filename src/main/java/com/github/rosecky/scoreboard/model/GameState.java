package com.github.rosecky.scoreboard.model;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class GameState {
    @NonNull
    private final Team homeTeam;
    @NonNull
    private final Team awayTeam;
    @NonNull
    private final Score score;
    @NonNull
    private final LocalDateTime startedAt;

    public static GameState startedNowWithTeams(Team homeTeam, Team awayTeam) {
        return startedWithTeamsAt(homeTeam, awayTeam, LocalDateTime.now());
    }

    public static GameState startedWithTeamsAt(Team homeTeam, Team awayTeam, LocalDateTime gameStartedAt) {
        return startedWithTeamsAtTimeAndWithScore(homeTeam, awayTeam, gameStartedAt, Score.initial());
    }
    public static GameState startedWithTeamsAtTimeAndWithScore(Team homeTeam, Team awayTeam, LocalDateTime gameStartedAt, Score score) {
        return new GameState(homeTeam, awayTeam, score, gameStartedAt);
    }

    public GameState withNewScore(Score newScore) {
        return startedWithTeamsAtTimeAndWithScore(homeTeam, awayTeam, startedAt, newScore);
    }
}
