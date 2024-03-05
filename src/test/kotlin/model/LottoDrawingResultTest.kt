package model

import lotto.model.Lotto
import lotto.model.LottoDrawingResult
import lotto.model.LottoNumber
import lotto.model.Prize
import lotto.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoDrawingResultTest {

    @Test
    fun `로또 결과에 대한 총 상금을 구한다`() {
        val lottoDrawingResult = LottoDrawingResult()
        val lottoes = listOf(Lotto(1, 2, 3, 4, 5, 6))
        val winningLotto = WinningLotto(Lotto(1, 2, 3, 4, 5, 6), LottoNumber.from(7))

        lottoDrawingResult.countRank(lottoes, winningLotto)
        val actual = lottoDrawingResult.calculateTotalPrize()
        val expected = Prize(2_000_000_000)

        assertThat(actual).isEqualTo(expected)
    }
}
