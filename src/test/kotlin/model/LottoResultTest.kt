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
            lottoResult.updateLottoResult(Rank.THIRD)
        }
        // then
        assertThat(lottoResult.result[Rank.THIRD]).isEqualTo(countOfTicket)
    }

    @Test
    fun `로또 하나를 사서 5등이 한번 당첨됐을 때 수익률은 5이다`() {
        // given
        val result = LottoResult()
        result.updateLottoResult(Rank.FIFTH)
        // when
        val actual = result.getProfitRate()
        // then
        assertThat(actual).isEqualTo(5.0)
    }
}
