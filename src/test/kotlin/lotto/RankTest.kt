package lotto

import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RankTest {
    @ParameterizedTest
    @CsvSource(
        "6, false, FIRST",
        "5, true, SECOND",
        "5, false, THIRD",
        "4, false, FOURTH",
        "3, false, FIFTH",
        "2, false, MISS",
    )
    fun `당첨번호와 보너스볼 매치 갯수에 따라 당첨 등수 확인 `(
        countOfMatch: Int,
        matchBonus: Boolean,
        expectedRank: Rank,
    ) {
        val result = Rank.fromMatch(countOfMatch, matchBonus)
        assertThat(result).isEqualTo(expectedRank)
    }
}
