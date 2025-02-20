package domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `Lotto 당첨 결과에 따른 수익률 테스트`() {
        val profitRate =
            LottoResult(
                mapOf(
                    Rank.FOURTH to 1,
                ),
            ).getProfitRate(PurchasePrice(11_000_0))

        assertThat(profitRate).isEqualTo("0.45")
    }
}
