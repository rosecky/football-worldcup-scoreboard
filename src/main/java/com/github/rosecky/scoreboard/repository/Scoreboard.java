package com.github.rosecky.scoreboard.repository;

import com.github.rosecky.scoreboard.model.Game;
import com.github.rosecky.scoreboard.model.Team;

import java.util.List;

public interface Scoreboard {
    default Game startGame(Team homeTeam, Team awayTeam) {
        var game = Game.withTeams(homeTeam, awayTeam);
        addGame(game);
        return game;
    }
    void addGame(Game game);
    void finishGame(Game game);
    List<Game> getSummaryOfGamesInProgress();
}
