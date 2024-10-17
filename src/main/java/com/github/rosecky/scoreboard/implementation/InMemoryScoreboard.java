package com.github.rosecky.scoreboard.implementation;

import com.github.rosecky.scoreboard.model.GameState;
import com.github.rosecky.scoreboard.model.Score;
import com.github.rosecky.scoreboard.model.Team;
import com.github.rosecky.scoreboard.repository.Scoreboard;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.*;

public class InMemoryScoreboard implements Scoreboard {

    // map of tuples <home team, away team> to their current game state
    private final Map<ImmutablePair<Team, Team>, GameState> gamesInProgress = new HashMap<>();

    @Override
    public void trackGameState(GameState gameState) {
        var key = new ImmutablePair<>(gameState.getHomeTeam(), gameState.getAwayTeam());
        if (gamesInProgress.containsKey(key)) {
            throw new IllegalArgumentException("Game between %s and %s already in progress".formatted(gameState.getHomeTeam(), gameState.getAwayTeam()));
        }
        gamesInProgress.put(key, gameState);
    }

    @Override
    public GameState updateScore(Team homeTeam, Team awayTeam, Score newScore) {
        var key = new ImmutablePair<>(homeTeam, awayTeam);
        var oldGameState = Optional.ofNullable(gamesInProgress.get(key))
                .orElseThrow(() -> new IllegalArgumentException("Game between %s and %s does not exist".formatted(homeTeam, awayTeam)));
        var newGameState = oldGameState.withNewScore(newScore);
        gamesInProgress.put(key, newGameState);
        return newGameState;
    }

    @Override
    public GameState finishGameBetween(Team homeTeam, Team awayTeam) {
        var key = new ImmutablePair<>(homeTeam, awayTeam);
        var gameState = Optional.ofNullable(gamesInProgress.get(key))
                .orElseThrow(() -> new IllegalArgumentException("Game between %s and %s does not exist".formatted(homeTeam, awayTeam)));
        gamesInProgress.remove(key);
        return gameState;
    }

    @Override
    public List<GameState> getSummaryOfGamesInProgress() {
        return gamesInProgress.values()
                .stream()
                .sorted(
                        Comparator.comparing((GameState gameState) -> gameState.getScore().getTotal())
                                .thenComparing(GameState::getStartedAt)
                                .reversed()
                ).toList();
    }
}
