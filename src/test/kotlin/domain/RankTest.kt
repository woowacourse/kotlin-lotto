package domain

import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankTest {

    @ParameterizedTest
    @CsvSource(
        "6,false,FIRST",
        "5, true, SECOND",
        "5, false, THIRD",
        "4, false, FOURTH",
        "3, false, FIFTH",
        "2, false, MISS",
        "1, false, MISS",
        "0, false, MISS"
    )
    fun `일치 개수와 보너스 매치 여부에 따른 랭크 반환하는 지`(countOfMatch: Int, matchBonus: Boolean, rank: Rank) {
        val expected = Rank.valueOf(countOfMatch, matchBonus)

        assertThat(expected).isEqualTo(rank)
    }
}
