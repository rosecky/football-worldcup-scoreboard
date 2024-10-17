import com.github.rosecky.scoreboard.model.GameState;
import com.github.rosecky.scoreboard.model.Team;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GameStateTest {

    @Test
    public void initialScoreIs00() {
        var game = GameState.startedNowWithTeams(new Team("HH"), new Team("AW"));
        assertThat(game.getScore())
                .has(TestUtils.score(0, 0));
    }

    @Test
    public void nullTeamsThrow() {
        assertThatThrownBy(() -> GameState.startedNowWithTeams(null, new Team("team")))
                .hasMessageContaining("null")
                .hasMessageContaining("homeTeam");

        assertThatThrownBy(() -> GameState.startedNowWithTeams(new Team("team"), null))
                .hasMessageContaining("null")
                .hasMessageContaining("awayTeam");
    }

    @Test
    public void nullScoresThrow() {
        assertThatThrownBy(() -> GameState.startedWithTeamsAtTimeNowWithScore(new Team("homes"), new Team("aways"), LocalDateTime.now(), null))
                .hasMessageContaining("null")
                .hasMessageContaining("score");
    }
}
