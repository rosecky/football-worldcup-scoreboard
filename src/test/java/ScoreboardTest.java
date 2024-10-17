import com.github.rosecky.scoreboard.model.Game;
import com.github.rosecky.scoreboard.model.Score;
import com.github.rosecky.scoreboard.model.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreboardTest {

    @Test
    public void gamesInProgressAreReturnedInCorrectOrder() {
        var game1 = Game.withTeamsAndScore(new Team("Mexico"), new Team("Canada"), new Score(0, 5));
    }
}
