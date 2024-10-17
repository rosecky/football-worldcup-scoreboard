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

    public void updateScore(@NonNull Score newCurrentScore) {
        currentScore = newCurrentScore;
    }

    // For our purposes, games are identified only by home and away teams.
    // This is a big simplification: in reality, the identity would be defined differently,
    // at least to also incorporate the day/time on the match.
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Game other
                && other.homeTeam.equals(this.homeTeam)
                && other.awayTeam.equals(this.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam);
    }
}
