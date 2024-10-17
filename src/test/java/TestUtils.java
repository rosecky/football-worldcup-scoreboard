import com.github.rosecky.scoreboard.model.Score;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.assertj.core.api.Condition;

@UtilityClass
public class TestUtils {
    static Condition<? super @NonNull Score> score(int homeTeamPonts, int awayTeamPoints) {
        return new Condition<>(score ->
                score.getHomeTeamPoints() == homeTeamPonts
                        && score.getAwayTeamPoints() == awayTeamPoints,
                "Score must be %d:%d", homeTeamPonts, awayTeamPoints);
    }
}
