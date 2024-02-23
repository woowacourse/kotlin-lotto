package lotto.domain

import lotto.domain.model.LottoDrawingResult
import lotto.domain.model.Margin
import lotto.domain.model.Money
import lotto.domain.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MarginCalculatorTest {
    @Test
    fun `로또 결과에 대한 총 상금을 구한다`() {
        val rank = mapOf(Rank.FIRST to 0, Rank.SECOND to 0, Rank.THIRD to 1, Rank.FOURTH to 0, Rank.FIFTH to 0)
        val actual = MarginCalculator.calculateTotalPrize(LottoDrawingResult(rank))

        assertThat(actual).isEqualTo(Money(1_500_000))
    }

    @Test
    fun `Int형 범위를 넘어가는 총 상금이어도 정상 작동한다`() {
        val rank = mapOf(Rank.FIRST to 2, Rank.SECOND to 0, Rank.THIRD to 0, Rank.FOURTH to 0, Rank.FIFTH to 0)
        val actual = MarginCalculator.calculateTotalPrize(LottoDrawingResult(rank))

        assertThat(actual).isEqualTo(Money(4_000_000_000))
    }

    @Test
    fun `구입 금액 대비 수익 금액의 비율을 구한다`() {
        val winningPrize = Money(5000)
        val purchaseAmount = Money(14000)
        val actual = MarginCalculator.calculateMarginRate(winningPrize, purchaseAmount)
        assertThat(actual).isEqualTo(Margin(0.357))
    }
}
