package com.github.rosecky.scoreboard.implementation;

import com.github.rosecky.scoreboard.model.Game;
import com.github.rosecky.scoreboard.model.Team;
import com.github.rosecky.scoreboard.repository.Scoreboard;

import java.util.SortedSet;

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
    public SortedSet<Game> getSummaryOfGamesInProgress() {
        return null;
    }
}
