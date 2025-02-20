package domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `Lotto 결과에 따른 수익률 구하기`() {
        val profitRate =
            LottoResult(
                mutableMapOf(
                    Rank.FIRST to 1,
                    Rank.SECOND to 2,
                    Rank.THIRD to 3,
                    Rank.FOURTH to 4,
                ),
            ).getProfitRate(PurchasePrice(1000000))

        assertThat(profitRate).isEqualTo("2064.70")
    }
}
