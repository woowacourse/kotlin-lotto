package lotto

import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.Rank
import lotto.domain.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `당첨 등수를 확인할 수 있다`() {
        val lotto = lottoOf(1, 2, 3, 4, 5, 6)
        val winningLottoNumber = lottoOf(1, 2, 4, 5, 8, 9)

        val bonusNumber: LottoNumber = LottoNumber(7)
        val winningLotto = WinningLotto(winningLottoNumber, bonusNumber)

        val rank = LottoResult(listOf(lotto), winningLotto).getRanks()

        assertThat(rank).containsExactly(Rank.FOURTH)
    }

    @Test
    fun `당첨 수익률을 계산할 수 있다`() {
        val lotto = lottoOf(1, 2, 3, 4, 5, 6)
        val winningLottoNumber = lottoOf(1, 2, 8, 4, 5, 9)
        val bonusNumber: LottoNumber = LottoNumber(7)

        val winningLotto = WinningLotto(winningLottoNumber, bonusNumber)
        val rank = LottoResult(listOf(lotto), winningLotto).calculateProfitRate()

        assertThat(rank).isEqualTo(50.0)
    }
}
