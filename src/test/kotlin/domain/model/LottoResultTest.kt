package domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `Lotto 결과에 따른 수익률 구하기`() {
        val purchasePriceValue: Int = 1_000_000

        val lottoResult =
            LottoResult(
                mutableMapOf(
                    Rank.FIRST to 1,
                    Rank.SECOND to 2,
                    Rank.THIRD to 3,
                    Rank.FOURTH to 4,
                ),
            )

        val profitRate = lottoResult.getProfitRate(purchasePriceValue)

        assertThat(profitRate).isEqualTo("2064.70")
    }
}
