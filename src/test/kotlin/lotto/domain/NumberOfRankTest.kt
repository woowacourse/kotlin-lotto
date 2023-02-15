package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class NumberOfRankTest {
    @ParameterizedTest
    @EnumSource(Rank::class)
    fun `등수를 받아 카운트한다`(rank: Rank) {
        val counter = NumberOfRank()

        counter.increaseNumber(rank)

        assertThat(counter.numbers[rank.name]).isEqualTo(1)
    }
}
