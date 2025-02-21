package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoRanksTest {
    @Test
    fun `총 수익률을 계산할 수 있다`() {
        val lottoRanks = listOf(LottoRank.THIRD, LottoRank.FIFTH, LottoRank.SECOND)
        val totalReturn = LottoRanks(lottoRanks).calculateTotalReturn()
        val actual = 10_501.66.toBigDecimal()
        assertThat(totalReturn).isEqualTo(actual)
    }
}
