package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningStatisticsTest {
    @Test
    fun `로또 수익률 계산 확인`() {
        val purchaseAmount = 14_000
        val result = mapOf(Rank.FIFTH to 1)
        val winningStatistics = WinningStatistics(result)

        val actual = winningStatistics.calculateRateOfReturn(purchaseAmount)
        val expected = 0.35714285714285715

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `올바른 우승상황 테스트`() {
        val lotto = listOf(11, 15, 17, 21, 22, 35).map { LottoNumber(it) }
        val bonus = LottoNumber(8)
        val winningLotto = WinningLotto(lotto, bonus)

        val firstLotto = Lotto(11, 15, 17, 21, 30, 31)
        val secondLotto = Lotto(11, 15, 17, 21, 22, 40)
        val publishedLottos = Lottos(listOf(firstLotto, secondLotto))

        val actual = winningLotto.makeWinningStatics(publishedLottos)
        val expected =
            WinningStatistics(
                mapOf(
                    Rank.THIRD to 1,
                    Rank.FOURTH to 1,
                ),
            )
        assertThat(actual).isEqualTo(expected)
    }
}
