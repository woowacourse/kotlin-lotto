package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoControllerTest {
    @Test
    fun `구입 금액과 당첨금을 통해 수익률을 계산한다`() {
        val lottoResults: List<LottoResult> = listOf(LottoResult.FIRST, LottoResult.SECOND, LottoResult.THIRD)
        val price: Int = 10 * Lotto.LOTTO_PRICE
        val profit = lottoResults.sumOf { lottoResult: LottoResult -> lottoResult.prizeAmount }
        val profitRate: Double = LottoController.getProfitRate(lottoResults, price)
        assertThat(profitRate).isEqualTo(profit / price.toDouble())
    }
}
