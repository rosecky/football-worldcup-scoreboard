import com.github.rosecky.scoreboard.model.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {
    @Test
    public void negativeScoresThrow() {
        assertThatThrownBy(() -> new Score(-1, 0))
                .hasMessageContaining("negative");
        assertThatThrownBy(() -> new Score(1, -100))
                .hasMessageContaining("negative");
        assertThatCode(() -> new Score(1, 100))
                .doesNotThrowAnyException();
        assertThatCode(Score::initial)
                .doesNotThrowAnyException();
    }
}
