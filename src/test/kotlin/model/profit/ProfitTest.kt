package model.profit

import model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitTest {
    @Test
    fun `구매 금액과 총 당첨 금액의 수익률을 계산한다`() {
        // given
        val purchaseAmount = Money.wons(5_000)
        val totalWinningPrize = Money.wons(30_000_000)

        // when
        val result = Profit().calculateRate(purchaseAmount, totalWinningPrize)

        // then
        assertThat(result).isEqualTo(ProfitRate(6000.0))
    }
}
