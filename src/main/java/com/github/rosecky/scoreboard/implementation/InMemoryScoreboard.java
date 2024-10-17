package com.github.rosecky.scoreboard.implementation;

import com.github.rosecky.scoreboard.model.Game;
import com.github.rosecky.scoreboard.model.Team;
import com.github.rosecky.scoreboard.repository.Scoreboard;

import java.util.List;

public class InMemoryScoreboard implements Scoreboard {


    @Override
    public Game startGame(Team homeTeam, Team awayTeam) {
        return null;
    }

    @Override
    public void addGame(Game game) {

    }

    @Override
    public void finishGame(Game game) {

    }

    @Override
    public List<Game> getSummaryOfGamesInProgress() {
        return null;
    }
}
