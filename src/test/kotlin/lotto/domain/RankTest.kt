package lotto.domain

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
        "3, true, FIFTH",
        "2, false, MISS",
        "1, false, MISS",
        "0, false, MISS",
    )
    fun `일치하는 개수와 보너스번호 매치 여부를 알려주면 등수를 알려준다`(countOfMatch: Int, matchBonus: Boolean, expected: Rank) {
        assertThat(Rank.valueOf(countOfMatch, matchBonus)).isEqualTo(expected)
    }
}
