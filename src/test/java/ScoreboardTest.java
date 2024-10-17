import com.github.rosecky.scoreboard.model.Game;
import com.github.rosecky.scoreboard.model.Score;
import com.github.rosecky.scoreboard.model.Team;
import org.junit.jupiter.api.Test;

public class ScoreboardTest {

    @Test
    public void gamesInProgressAreReturnedInCorrectOrder() {
        var game1 = Game.withTeamsAndScore(Team.withName("Mexico"), Team.withName("Canada"), new Score(0, 5));
    }
}
