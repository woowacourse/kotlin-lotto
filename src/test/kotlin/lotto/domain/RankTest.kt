package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankTest {
    @ParameterizedTest
    @CsvSource(value = ["6:false:FIRST", "5:true:SECOND", "5:false:THIRD", "4:true:FOURTH", "1:true:MISS"], delimiter = ':')
    fun `당첨 등수를 구한다`(countOfMatch: Int, matchBonus: Boolean, expected: Rank) {
        val actual = Rank.valueOf(countOfMatch, matchBonus)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `일치하는 번호의 개수는 0과 6 사이여야 한다`() {
        assertThrows<IllegalArgumentException> { Rank.valueOf(7, true) }
    }
}
