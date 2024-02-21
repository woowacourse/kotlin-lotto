package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "6:true:FIRST", "6:false:FIRST",
            "5:true:SECOND", "5:false:THIRD",
            "4:true:FOURTH", "4:false:FOURTH",
            "3:true:FIFTH", "3:false:FIFTH",
            "2:true:MISS", "2:false:MISS",
            "1:true:MISS", "1:false:MISS",
            "0:true:MISS", "0:false:MISS",
        ],
        delimiter = ':',
    )
    fun `일치하는 숫자의 수와 보너스 번호 일치 여부를 통해 Rank가 결정된다`(
        countOfMatch: Int,
        matchBonus: Boolean,
        expectedRank: Rank,
    ) {
        // when
        val actualRank = Rank.valueOf(countOfMatch, matchBonus)
        // then
        assertThat(actualRank).isEqualTo(expectedRank)
    }
}
