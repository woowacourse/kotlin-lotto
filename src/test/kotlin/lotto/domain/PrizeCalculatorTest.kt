package lotto.domain

import lotto.domain.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PrizeCalculatorTest {
    @Test
    fun `총 수익을 입력 금액으로 나누어 수익률을 계산한다`() {
        // Given
        val money = 1000
        val result = mapOf(Rank.FIFTH to 1)

        // When
        val prizeCalculator = PrizeCalculator()
        val earningRate = prizeCalculator.calculateEarningRate(money, result)

        // Then
        assertThat(earningRate).isEqualTo((Rank.FIFTH.winningMoney / 1000).toDouble())
    }
}
