package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PrizeAmountManagerTest {
    @Test
    fun `당첨 총 금액을 모든 로또의 순위에 따라 계산한다`() {
        var prizeAmountManager = PrizeAmountManager()
        assertThat(prizeAmountManager.calculateAllAmount(listOf(Rank.FIRST, Rank.SECOND)) == 2_030_000_000)
    }
}
