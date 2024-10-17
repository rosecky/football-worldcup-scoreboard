import com.github.rosecky.scoreboard.model.GameState;
import com.github.rosecky.scoreboard.model.Score;
import com.github.rosecky.scoreboard.model.Team;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.assertj.core.api.Condition;

@UtilityClass
public class TestUtils {

    static Condition<? super @NonNull Score> score(int homeTeamPoints, int awayTeamPoints) {
        return new Condition<>(score ->
                score.getHomeTeamPoints() == homeTeamPoints
                        && score.getAwayTeamPoints() == awayTeamPoints,
                "Score must be %d:%d", homeTeamPoints, awayTeamPoints);
    }

    static Condition<? super @NonNull GameState> teams(Team homeTeam, Team awayTeam) {
        return new Condition<>(game ->
                game.getHomeTeam().equals(homeTeam)
                        && game.getAwayTeam().equals(awayTeam),
                "Game must be between %s and %s", homeTeam, awayTeam);
    }

    static Condition<? super @NonNull GameState> teams(String homeTeamName, String awayTeamName) {
        return new Condition<>((GameState game) ->
                game.getHomeTeam().getName().equals(homeTeamName)
                        && game.getAwayTeam().getName().equals(awayTeamName),
                "Game must be between %s and %s", homeTeamName, awayTeamName);
    }
}
