package lotto

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.Rank
import lotto.domain.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `당첨 등수를 확인할 수 있다`() {
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

        val winningNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(8),
                LottoNumber(9),
            )
        val bonusNumber: String = "7"
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)

        val rank = LottoResult(listOf(lotto), winningLotto).getRanks()

        assertThat(rank).containsExactly(Rank.FOURTH)
    }

    @Test
    fun `당첨 수익률을 계산할 수 있다`() {
        val lottoNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        val winningNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(8),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(9),
            )
        val lotto = Lotto(lottoNumbers)
        val bonusNumber: String = "7"

        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
        val rank = LottoResult(listOf(lotto), winningLotto).calculateProfitRate()

        assertThat(rank).isEqualTo(50.0)
    }
}
