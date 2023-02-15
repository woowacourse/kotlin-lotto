package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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

    @Test
    fun `총 상금을 계산한다`() {
        val counter = NumberOfRank(
            mapOf(
                "FIRST" to 2,
                "SECOND" to 0,
                "THIRD" to 1,
                "FOURTH" to 0,
                "FIFTH" to 1
            )
        )

        val actual = counter.calculateTotalPrize()

        assertThat(actual).isEqualTo(4001505000)
    }
}
