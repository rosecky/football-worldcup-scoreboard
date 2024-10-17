import com.github.rosecky.scoreboard.model.GameState;
import com.github.rosecky.scoreboard.model.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GameStateTest {

    @Test
    public void initialScoreIs00() {
        var game = GameState.initialWithTeams(new Team("HH"), new Team("AW"));
        assertThat(game.getScore())
                .has(TestUtils.score(0, 0));
    }

    @Test
    public void nullTeamsThrow() {
        assertThatThrownBy(() -> GameState.initialWithTeams(null, new Team("team")))
                .hasMessageContaining("null")
                .hasMessageContaining("homeTeam");

        assertThatThrownBy(() -> GameState.initialWithTeams(new Team("team"), null))
                .hasMessageContaining("null")
                .hasMessageContaining("awayTeam");
    }

    @Test
    public void nullScoresThrow() {
        assertThatThrownBy(() -> GameState.withTeamsAndScore(new Team("homes"), new Team("aways"), null))
                .hasMessageContaining("null")
                .hasMessageContaining("score");
    }
}
