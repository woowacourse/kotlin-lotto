package lotto.model

import lotto.model.Rank.FIFTH
import lotto.model.Rank.FIRST
import lotto.model.Rank.FOURTH
import lotto.model.Rank.MISS
import lotto.model.Rank.SECOND
import lotto.model.Rank.SIXTH
import lotto.model.Rank.THIRD
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoWinningPrizeTest {
    @Test
    fun `1등 당첨되면 1등상금만큼의 수익률을 얻는다`() {
        val winningTable =
            WinningTable(
                mapOf(
                    FIRST to 1,
                    SECOND to 0,
                    THIRD to 0,
                    FOURTH to 0,
                    FIFTH to 0,
                    SIXTH to 0,
                    MISS to 10,
                ),
            )
        val ticketCount = 1
        val purchaseAmount = ticketCount * LottoMachine.PRICE_OF_LOTTO_TICKET
        val lottoWinningPrize = LottoWinningPrize(winningTable)
        val winningRate = lottoWinningPrize.calculateWinningRate(ticketCount)
        assertThat(winningRate).isEqualTo(
            FIRST.winningMoney.toFloat() / purchaseAmount,
        )
    }
}
