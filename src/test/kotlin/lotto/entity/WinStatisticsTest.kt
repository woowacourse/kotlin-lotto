package lotto.entity

import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinStatisticsTest {
    @Test
    fun `5등이 2개이면 상금은 10000원이다`() {
        // given
        val winStatistics = WinStatistics(listOf(Rank.FIFTH, Rank.FIFTH))

        // when
        val winMoney = winStatistics.calculateWinMoney()

        // then
        assertThat(winMoney.value).isEqualTo(10000)
    }
}
