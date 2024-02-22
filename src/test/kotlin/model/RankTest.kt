package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankTest {
    @ParameterizedTest
    @CsvSource(
        "6, false, FIRST",
        "5, true, SECOND",
        "5, false, THIRD",
        "4, true, FOURTH",
        "3, true, FIFTH",
    )
    fun `당첨번호 개수와 보너스번호 존재 여부에 따라 등수를 판별한다`(
        countOfMatch: Int,
        matchBonus: Boolean,
        rank: Rank,
    ) {
        val actual = rank
        val expected = Rank.decideRank(countOfMatch, matchBonus)
        assertThat(actual).isEqualTo(expected)
    }
}
