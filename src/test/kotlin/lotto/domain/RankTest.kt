package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankTest {
    @ParameterizedTest
    @CsvSource(value = ["6:false:1", "5:true:2", "5:false:3", "4:true:4", "1:true:0"], delimiter = ':')
    fun `당첨 등수를 구한다`(countOfMatch: Int, matchBonus: Boolean, rank: Int) {
        val ranks = mapOf(1 to Rank.FIRST, 2 to Rank.SECOND, 3 to Rank.THIRD, 4 to Rank.FOURTH, 0 to Rank.MISS)
        assertThat(Rank.valueOf(countOfMatch, matchBonus)).isEqualTo(ranks[rank])
    }

    @Test
    fun `일치하는 번호의 개수는 0과 6 사이여야 한다`() {
        assertThrows<IllegalArgumentException> { Rank.valueOf(7, true) }
    }
}
