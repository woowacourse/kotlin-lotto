package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankTest {
    @ParameterizedTest
    @CsvSource(value = ["6, false, FIRST", "5, true, SECOND", "5, false, THIRD", "4, false, FOURTH", "3, false, FIFTH"])
    fun `일치하는 숫자와 보너스 매치 여부가 들어오면 rank를 반환한다`(
        countOfMatch: Int,
        matchBonus: Boolean,
        expected: Rank
    ) {
        val actual = Rank.valueOf(countOfMatch, matchBonus)

        Assertions.assertThat(actual).isEqualTo(expected)
    }
}