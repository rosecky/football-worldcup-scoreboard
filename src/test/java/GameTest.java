import com.github.rosecky.scoreboard.model.Game;
import com.github.rosecky.scoreboard.model.Score;
import com.github.rosecky.scoreboard.model.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GameTest {

    @Test
    public void updatedGameHasUpdatedScore() {
        var game = Game.withTeams(new Team("HH"), new Team("AW"));
        assertThat(game.getCurrentScore())
                .has(TestUtils.score(0, 0));
    }

    @Test
    public void nullTeamsThrow() {
        assertThatThrownBy(() -> Game.withTeams(null, new Team("team")))
                .hasMessageContaining("null")
                .hasMessageContaining("homeTeam");

        assertThatThrownBy(() -> Game.withTeams(new Team("team"), null))
                .hasMessageContaining("null")
                .hasMessageContaining("awayTeam");
    }

    @Test
    public void nullScoresThrow() {
        assertThatThrownBy(() -> Game.withTeamsAndScore(new Team("homes"), new Team("aways"), null))
                .hasMessageContaining("null")
                .hasMessageContaining("Score");

        assertThatThrownBy(() ->
                Game.withTeams(new Team("homes"), new Team("aways"))
                        .updateScore(null))
                .hasMessageContaining("null")
                .hasMessageContaining("Score");
    }

    @Test
    public void gamesAreIdentifiedSolelyByTeams() {
        var game1 = Game.withTeams(new Team("HH"), new Team("AW"));
        var game2 = Game.withTeamsAndScore(new Team("HH"), new Team("AW"), new Score(1, 0));

        assertThat(game1).isEqualTo(game2);
        assertThat(game1.hashCode()).isEqualTo(game2.hashCode());
    }
}
