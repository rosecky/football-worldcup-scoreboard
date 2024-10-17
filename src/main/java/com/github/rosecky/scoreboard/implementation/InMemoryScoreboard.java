package com.github.rosecky.scoreboard.implementation;

import com.github.rosecky.scoreboard.model.GameState;
import com.github.rosecky.scoreboard.model.Score;
import com.github.rosecky.scoreboard.model.Team;
import com.github.rosecky.scoreboard.repository.Scoreboard;

import java.util.List;

public class InMemoryScoreboard implements Scoreboard {



    @Override
    public void trackGameState(GameState gameState) {

    }

    @Override
    public GameState updateScore(Team homeTeam, Team awayTeam, Score newScore) {
        return null;
    }

    @Override
    public GameState finishGameBetween(Team homeTeam, Team awayTeam) {
        return null;
    }

    @Override
    public List<GameState> getSummaryOfGamesInProgress() {
        return null;
    }
}
