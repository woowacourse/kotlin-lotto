package model.winning

import model.Money
import model.Quantity
import model.profit.ProfitRate
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningResultTest {
    @Test
    fun `로또의 총 당첨금을 계산한다`() {
        val winningResult =
            WinningResult(
                mapOf(
                    WinningRank.FIRST to Quantity(0),
                    WinningRank.SECOND to Quantity(1),
                    WinningRank.THIRD to Quantity(0),
                    WinningRank.FOURTH to Quantity(1),
                    WinningRank.FIFTH to Quantity(1),
                    WinningRank.NONE to Quantity(0),
                ),
            )
        val lotteriesPrize = winningResult.calculate()
        Assertions.assertThat(lotteriesPrize).isEqualTo(Money.wons(30_055_000))
    }

    @Test
    fun `로또 수익률을 계산한다`() {
        val purchaseAmount = Money.wons(10000)
        val winningResult =
            WinningResult(
                mapOf(
                    WinningRank.FIRST to Quantity(0),
                    WinningRank.SECOND to Quantity(0),
                    WinningRank.THIRD to Quantity(0),
                    WinningRank.FOURTH to Quantity(1),
                    WinningRank.FIFTH to Quantity(1),
                    WinningRank.NONE to Quantity(0),
                ),
            )
        val actual = winningResult.calculateProfitRate(purchaseAmount)

        assertThat(actual).isEqualTo(ProfitRate(5.5))
    }
}
