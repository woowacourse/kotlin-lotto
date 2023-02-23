package lotto.domain

import lotto.constant.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningResultTest {

    @Test
    fun `10000원으로 5등에 당첨되면 수익률은 영점오 이다`() {
        val winningResult = WinningResult.from(ranks = listOf<Rank>(Rank.FIFTH))
        assertThat(winningResult.getYieldRate(PurchaseMoney(10000))).isEqualTo(0.5)
    }
}
