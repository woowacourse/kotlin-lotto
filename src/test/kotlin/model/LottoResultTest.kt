package model

import domain.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `로또 결과의 랭크 갯수를 카운트한다`() {
        // given
        val countOfTicket = 3
        val lottoResult = LottoResult()
        // when
        repeat(countOfTicket) {
            lottoResult.plusRankCount(Rank.THIRD, lottoResult.result)
        }
        // then
        assertThat(lottoResult.result[Rank.THIRD]).isEqualTo(countOfTicket)
    }

    @Test
    fun `수익률을 계산한다`() {
        // given
        val result = hashMapOf(Rank.FOURTH to 2)
        val lottoResult = LottoResult()

        // when
        val actual = lottoResult.getProfitRate(result)
        // then
        assertThat(actual).isEqualTo(50.0)
    }
}
