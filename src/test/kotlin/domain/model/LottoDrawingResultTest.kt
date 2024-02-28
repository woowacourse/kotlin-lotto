package domain.model

import lotto.model.LottoDrawingResult
import lotto.model.Money
import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoDrawingResultTest {

    @Test
    fun `로또 결과에 대한 총 상금을 구한다`() {
        val rank = mapOf(Rank.FIRST to 0, Rank.SECOND to 0, Rank.THIRD to 1, Rank.FOURTH to 0, Rank.FIFTH to 0)
        val lottoDrawingResult = LottoDrawingResult(rank)

        val expected = lottoDrawingResult.calculateTotalPrize()
        val actual = 1_500_000L

        assertThat(expected).isEqualTo(Money(actual))
    }
}
