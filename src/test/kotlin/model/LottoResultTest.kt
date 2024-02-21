package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `1,2,3,꽝 한장씩에 대한 결과 테스트`() {
        val resultLotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val resultBonus = Bonus("7", resultLotto.numbers)

        val userLottos =
            listOf(
                Lotto(listOf(1, 2, 3, 4, 5, 6)),
                Lotto(listOf(1, 2, 3, 4, 5, 7)),
                Lotto(listOf(1, 2, 3, 4, 5, 8)),
                Lotto(
                    listOf(10, 11, 12, 13, 14, 15),
                ),
            )
        val result = LottoResult.getStats(userLottos, resultLotto, resultBonus)

        assertThat(result[Rank.FIRST]).isEqualTo(1)
        assertThat(result[Rank.SECOND]).isEqualTo(1)
        assertThat(result[Rank.THIRD]).isEqualTo(1)
        assertThat(result[Rank.MISS]).isEqualTo(1)
    }

    @Test
    fun `5000원 투자 결과로 1등 한 장, 3등 세 장 결과를 테스트`() {
        val amount = Amount("5000")
        val lottoResult =
            mapOf(
                Rank.FIRST to 1,
                Rank.THIRD to 3,
            )

        val profit = LottoResult.calculateROI(amount, lottoResult)
        val expected = (((Rank.FIRST.winningMoney + (Rank.THIRD.winningMoney * 3)) / 5000) * 100.0).round(2)
        assertThat(profit).isEqualTo(expected)
    }
}
