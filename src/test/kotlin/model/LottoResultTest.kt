package model

import domain.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {

    @Test
    fun `수익률을 계산한다`() {
        // given
        val result = hashMapOf(Rank.FOURTH to 2)
        val lottoResult = LottoResult()

        // when
        val actual = lottoResult.getProfitRate()
        // then
        assertThat(actual).isEqualTo(50.0)
    }
}
