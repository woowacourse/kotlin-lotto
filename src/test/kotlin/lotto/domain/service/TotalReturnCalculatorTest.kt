package lotto.domain.service

import lotto.domain.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TotalReturnCalculatorTest {
    @Test
    fun `총 수익률을 계산할 수 있다`() {
        val ranks = listOf(Rank.THIRD, Rank.FIFTH, Rank.SECOND)
        val totalReturn = TotalReturnCalculator().calculate(ranks)
        val actual = 10_501.66.toBigDecimal()
        assertThat(totalReturn).isEqualTo(actual)
    }
}
