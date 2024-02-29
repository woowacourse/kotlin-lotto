package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoWinningPrizeTest {
    @Test
    fun `1등 당첨되면 1등상금만큼의 수익률을 얻는다`() {
        val rankMap = mapOf(Rank.FIRST to 1)
        val ticketCount = 1
        val purchaseAmount = ticketCount * AutoLottoMachine.PRICE_OF_LOTTO_TICKET
        val lottoWinningPrize = LottoWinningPrize(rankMap)
        val winningRate = lottoWinningPrize.calculateWinningRate(ticketCount)
        assertThat(winningRate).isEqualTo(
            Rank.FIRST.winningMoney.toFloat() / purchaseAmount,
        )
    }
}
