package lotto.domain.service

import lotto.domain.model.LottoRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TotalReturnCalculatorTest {
    @Test
    fun `총 수익률을 계산할 수 있다`() {
        val lottoRanks = listOf(LottoRank.THIRD, LottoRank.FIFTH, LottoRank.SECOND)
        val totalReturn = TotalReturnCalculator().calculate(lottoRanks)
        val actual = 10_501.66.toBigDecimal()
        assertThat(totalReturn).isEqualTo(actual)
    }
}
