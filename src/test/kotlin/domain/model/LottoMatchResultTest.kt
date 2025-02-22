package domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMatchResultTest {
    @Test
    fun `Lotto 당첨 결과에 따른 수익률 테스트`() {
        val rank = LottoMatchResult(mapOf(Rank.FIFTH to 1))
        val profitRate = rank.getProfitRate(PurchasePrice(10_000))

        assertThat(profitRate).isEqualTo(0.5)
    }
}
