package model

import org.assertj.core.api.Assertions
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
}
