package model

import model.domain.Rank.FIFTH
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {

    @Test
    fun `5등이 2번 당첨되었다`() {
        // given
        val rankResult = listOf(FIFTH, FIFTH)

        // when
        val actual = LottoResult(rankResult).result.get(FIFTH)

        // then
        assertThat(actual).isEqualTo(2)
    }

    @Test
    fun `2000원으로 로또 2장을 구매해, 10000원의 수익을 얻었다`() {
        // given
        val rankResult = listOf(FIFTH, FIFTH)

        // when
        val actual = LottoResult(rankResult).getProfitRate()

        // then
        assertThat(actual).isEqualTo(5.00)
    }
}
