package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `로또 결과의 랭크 갯수를 카운트한다`() {
        //given
        val countOfTicket = 3
        val lottoResult = LottoResult()
        //when
        repeat(countOfTicket) {
            lottoResult.plusRankCount(Rank.THIRD)
        }
        //then
        assertThat(lottoResult._resultContainer[Rank.THIRD]).isEqualTo(countOfTicket)
    }

    @Test
    fun `수익률을 계산한다`() {
        //given
        val result = hashMapOf(Rank.FOURTH to 2)
        val lottoResult = LottoResult()

        //when
        val actual = lottoResult.getProfitRate(result)
        //then
        assertThat(actual).isEqualTo(5000.0)
    }


}