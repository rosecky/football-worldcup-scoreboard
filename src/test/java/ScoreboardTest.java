import com.github.rosecky.scoreboard.implementation.InMemoryScoreboard;
import com.github.rosecky.scoreboard.model.GameState;
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

        scoreboard.trackGameState(gameA());
        assertThat(scoreboard.getSummaryOfGamesInProgress())
                .hasSize(1)
                .contains(gameA());
    }

    @Test
    public void startingGameBetweenTheSameOpponentsThrows() {
        var scoreboard = scoreboardWithGamesAToE();
        assertThatThrownBy(() -> scoreboard.startGameBetween(gameA().getHomeTeam(), gameA().getAwayTeam()))
                .hasMessageContaining("already in progress");
    }

    @Test
    public void endingAGameRemovesIt() {
        var scoreboard = scoreboardWithGamesAToE();
        assertThat(scoreboard.getSummaryOfGamesInProgress())
                .hasSize(5)
                .containsAll(List.of(gameA(), gameB(), gameC(), gameD(), gameE()));

        scoreboard.finishGameBetween(gameA().getHomeTeam(), gameA().getAwayTeam());
        assertThat(scoreboard.getSummaryOfGamesInProgress())
                .hasSize(4)
                .containsAll(List.of(gameB(), gameC(), gameD(), gameE()));
    }

    @Test
    public void endingUnknownGameThrows() {
        var scoreboard = scoreboardWithGamesAToE();
        assertThatThrownBy(() -> scoreboard.finishGameBetween(new Team("blah"), new Team("nonsense")))
                .hasMessageContaining("not exist");
    }

    @Test
    public void updatingUnknownGameThrows() {
        var scoreboard = scoreboardWithGamesAToE();
        assertThatThrownBy(() -> scoreboard.updateScore(new Team("blah"), new Team("nonsense"), new Score(1, 0)))
                .hasMessageContaining("not exist");
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

    @Test
    public void updatingAGameChangesSummaryOrder() {
        var scoreboard = scoreboardWithGamesAToE();
        scoreboard.updateScore(gameA().getHomeTeam(), gameA().getAwayTeam(), new Score(11, 3));

        var summary = scoreboard.getSummaryOfGamesInProgress().toArray(new GameState[0]);
        assertThat(summary).hasSize(5);
        assertThat(summary[0])
                .has(TestUtils.teams(gameA().getHomeTeam(), gameA().getAwayTeam()));
        assertThat(summary[0].getScore())
                .has(TestUtils.score(11, 3));
        assertThat(summary[1]).isEqualTo(gameD());
        assertThat(summary[2]).isEqualTo(gameB());
        assertThat(summary[3]).isEqualTo(gameE());
        assertThat(summary[4]).isEqualTo(gameC());
    }

    private Scoreboard scoreboard() {
        return new InMemoryScoreboard();
    }

    private Scoreboard scoreboardWithGamesAToE() {
        var scoreboard = scoreboard();
        scoreboard.trackGameState(gameA());
        scoreboard.trackGameState(gameB());
        scoreboard.trackGameState(gameC());
        scoreboard.trackGameState(gameD());
        scoreboard.trackGameState(gameE());
        return scoreboard;
    }

    private GameState game(String homeTeamName, String awayTeamName, int homeTeamPoints, int awayTeamPoints) {
        return GameState.withTeamsAndScore(new Team(homeTeamName), new Team(awayTeamName), new Score(homeTeamPoints, awayTeamPoints));
    }

    private GameState gameA() {
        return game("Mexico", "Canada", 0, 5);
    }

    private GameState gameB() {
        return game("Spain", "Brazil", 10, 2);
    }

    private GameState gameC() {
        return game("Germany", "France", 2, 2);
    }

    private GameState gameD() {
        return game("Uruguay", "Italy", 6, 6);
    }

    private GameState gameE() {
        return game("Argentina", "Australia", 3, 1);
    }
}
