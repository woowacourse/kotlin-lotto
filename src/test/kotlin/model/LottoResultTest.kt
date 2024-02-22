package model

import model.LottoResult.round
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `1등, 2등, 3등, 꽝 한 장씩에 대한 결과 테스트`() {
        val winningLotto = Lotto.fromList((1..6).toList())
        val bonus = Bonus("7", winningLotto)

        val userLottos = listOf(
            listOf(1, 2, 3, 4, 5, 6),
            listOf(1, 2, 3, 4, 5, 7),
            listOf(1, 2, 3, 4, 5, 8),
            listOf(10, 11, 12, 13, 14, 15)
        ).map { Lotto.fromList(it) }

        val result = LottoResult.getStats(userLottos, winningLotto, bonus)

        assertThat(result[Rank.FIRST]).isEqualTo(1)
        assertThat(result[Rank.SECOND]).isEqualTo(1)
        assertThat(result[Rank.THIRD]).isEqualTo(1)
        assertThat(result[Rank.MISS]).isEqualTo(1)
    }

    @Test
    fun `5000원 투자 결과로 1등 한 장, 3등 세 장 결과를 테스트`() {
        val amount = Amount("5000")
        val winningResult =
            mapOf(
                Rank.FIRST to 1,
                Rank.THIRD to 3,
            )

        val profit = LottoResult.calculateROI(amount, winningResult)
        val expected = (((Rank.FIRST.winningMoney + (Rank.THIRD.winningMoney * 3)) / 5000.0)).round(2)
        assertThat(profit).isEqualTo(expected)
    }
}
