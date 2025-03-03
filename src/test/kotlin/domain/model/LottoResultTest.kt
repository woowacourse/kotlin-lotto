package domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `Lotto 결과에 따라 수익률이 구해진다`() {
        val purchasePriceValue: Int = 1_000_000

        val lottoResult =
            LottoResult(
                mapOf(
                    Rank.FIRST to 1,
                    Rank.SECOND to 2,
                    Rank.THIRD to 3,
                    Rank.FOURTH to 4,
                ),
            )

        val profitRate = lottoResult.getProfitRate(purchasePriceValue)

        assertThat(profitRate).isEqualTo("2064.70")
    }

    @Test
    fun `Lotto 개별 등수 당첨 결과를 확인할 수 있다`() {
        val lottoResult =
            LottoResult(
                mapOf(
                    Rank.FIRST to 3,
                ),
            )

        val firstRankCount = lottoResult.getRankMatchCount(Rank.FIRST)
        val secondRankCount = lottoResult.getRankMatchCount(Rank.SECOND)

        assertThat(firstRankCount).isEqualTo(3)
        assertThat(secondRankCount).isEqualTo(0)
    }
}
