package lotto.model.winning

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankTest {
    @ParameterizedTest
    @CsvSource(
        "6, false, FIRST",
        "5, true, SECOND",
        "5, false, THIRD",
        "4, false, FOURTH",
        "3, false, FIFTH",
        "2, false, MISS",
        "1, false, MISS",
        "0, false, MISS",
    )
    fun `로또 당첨 결과 판별`(
        countOfMatch: Int,
        matchBonus: Boolean,
        expectedRank: Rank,
    ) {
        assertThat(Rank.valueOf(countOfMatch, matchBonus)).isEqualTo(expectedRank)
    }
}
