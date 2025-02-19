package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMatcherTest {
    @Test
    fun `당첨 로또 번호와 로또 번호를 비교하여 순위를 리턴한다`() {
        val lottoNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        val lotto = Lotto(lottoNumbers)
        val winLottoNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(7),
            )
        val winLotto = Lotto(winLottoNumbers)
        val bonusNumber = LottoNumber(10)
        val lottoMatcher = LottoMatcher()
        val rank = lottoMatcher.calculateRank(lotto, winLotto, bonusNumber)
        assertThat(rank == Rank.THIRD)
    }
}
