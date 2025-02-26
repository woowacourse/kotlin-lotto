package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PrizeCalculatorTest {
    @Test
    fun `총 수익을 입력 금액으로 나누어 수익률을 계산한다`() {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        val publishedLotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val prizeCalculator = PrizeCalculator(winningLotto, listOf(publishedLotto))
        val earningRate = prizeCalculator.calculateEarningRate()
        assertThat(earningRate).isEqualTo((Rank.FIRST.winningMoney / 1000).toDouble())
    }

    fun WinningLotto(
        numbers: List<Int>,
        bonusNumber: Int,
    ): WinningLotto = WinningLotto(LottoNumbers(numbers.map { number -> LottoNumber(number) }), LottoNumber(bonusNumber))

    fun Lotto(numbers: List<Int>): Lotto = Lotto(LottoNumbers(numbers.map { number -> LottoNumber(number) }))
}
