package com.github.rosecky.scoreboard.repository;

import com.github.rosecky.scoreboard.model.GameState;
import com.github.rosecky.scoreboard.model.Score;
import com.github.rosecky.scoreboard.model.Team;

import java.time.LocalDateTime;
import java.util.List;

public interface Scoreboard {
    default GameState newGameBetween(Team homeTeam, Team awayTeam, LocalDateTime startedAt) {
        var game = GameState.startedWithTeamsAt(homeTeam, awayTeam, startedAt);
        trackGameState(game);
        return game;
    }
    void trackGameState(GameState gameState);
    GameState updateScore(Team homeTeam, Team awayTeam, Score newScore);
    GameState finishGameBetween(Team homeTeam, Team awayTeam);
    List<GameState> getSummaryOfGamesInProgress();
}
