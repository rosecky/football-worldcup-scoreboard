import com.github.rosecky.scoreboard.implementation.InMemoryScoreboard;
import com.github.rosecky.scoreboard.model.Game;
import com.github.rosecky.scoreboard.model.Score;
import com.github.rosecky.scoreboard.model.Team;
import com.github.rosecky.scoreboard.repository.Scoreboard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ScoreboardTest {

    @Test
    public void addedGamePresentInSummary() {
        var scoreboard = scoreboard();
        assertThat(scoreboard.getSummaryOfGamesInProgress())
                .isEmpty();

        scoreboard.addGame(gameA());
        assertThat(scoreboard.getSummaryOfGamesInProgress())
                .hasSize(1)
                .contains(gameA());
    }

    @Test
    public void addingTheSameGameDoesNotAffectSummary() {
        var scoreboard = scoreboard();
        assertThat(scoreboard.getSummaryOfGamesInProgress())
                .isEmpty();

        scoreboard.addGame(gameA());
        scoreboard.addGame(gameA());
        assertThat(scoreboard.getSummaryOfGamesInProgress())
                .hasSize(1);
    }

    @Test
    public void endingAGameRemovesIt() {
        var scoreboard = scoreboardWithGamesAToE();
        assertThat(scoreboard.getSummaryOfGamesInProgress())
                .hasSize(5)
                .containsAll(List.of(gameA(), gameB(), gameC(), gameD(), gameE()));

        scoreboard.finishGame(gameA());
        assertThat(scoreboard.getSummaryOfGamesInProgress())
                .hasSize(4)
                .containsAll(List.of(gameB(), gameC(), gameD(), gameE()));
    }

    @Test
    public void gamesInProgressAreReturnedInCorrectOrder() {
        var scoreboard = scoreboardWithGamesAToE();
        var summary = scoreboard.getSummaryOfGamesInProgress().toArray();
        assertThat(summary).hasSize(5);
        assertThat(summary[0]).isEqualTo(gameD());
        assertThat(summary[1]).isEqualTo(gameB());
        assertThat(summary[2]).isEqualTo(gameA());
        assertThat(summary[3]).isEqualTo(gameE());
        assertThat(summary[4]).isEqualTo(gameC());
    }

    private Scoreboard scoreboard() {
        return new InMemoryScoreboard();
    }

    private Scoreboard scoreboardWithGamesAToE() {
        var scoreboard = scoreboard();
        scoreboard.addGame(gameA());
        scoreboard.addGame(gameB());
        scoreboard.addGame(gameC());
        scoreboard.addGame(gameD());
        scoreboard.addGame(gameE());
        return scoreboard;
    }

    private Game game(String homeTeamName, String awayTeamName, int homeTeamPoints, int awayTeamPoints) {
        return Game.withTeamsAndScore(new Team(homeTeamName), new Team(awayTeamName), new Score(homeTeamPoints, awayTeamPoints));
    }

    private Game gameA() {
        return game("Mexico", "Canada",0, 5);
    }
    private Game gameB() {
        return game("Spain", "Brazil", 10, 2);
    }
    private Game gameC() {
        return game("Germany", "France", 2, 2);
    }
    private Game gameD() {
        return game("Uruguay", "Italy", 6, 6);
    }
    private Game gameE() {
        return game("Argentina", "Australia", 3, 1);
    }
}
