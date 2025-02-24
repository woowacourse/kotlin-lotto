package lotto

import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "6,false,FIRST",
            "5,true,SECOND",
            "5,false,THIRD",
            "4,false,FOURTH",
            "3,false,FIFTH",
            "2,false,MISS",
            "1,false,MISS",
        ],
    )
    fun `맞춘 개수와 보너스 일치 여부에 따라 등수를 판단한다`(
        matchCount: Int,
        isMatchedBonus: Boolean,
        expected: Rank,
    ) {
        assertThat(Rank.findRank(matchCount, isMatchedBonus)).isEqualTo(expected)
    }
}
