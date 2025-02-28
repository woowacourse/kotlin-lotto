package lotto.model

import lotto.Lotto
import lotto.LottoNumber
import lotto.Rank
import lotto.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningLottoTest {
    @Test
    fun `당첨 로또 번호와 보너스 볼이 중복이면 null을 반환한다`() {
        assertThat(WinningLotto.createOrNull(lottoNumberList(listOf(1, 2, 3, 4, 5, 6)), LottoNumber.valueOf(6))).isEqualTo(null)
    }

    @Test
    fun `당첨 로또와 로또를 비교하여 순위를 반환한다`() {
        val winningLotto = WinningLotto.createOrNull(lottoNumberList(listOf(1, 2, 3, 4, 5, 6)), LottoNumber.valueOf(7))
        assertThat(winningLotto?.findRank(Lotto.valueOf(lottoNumberList(listOf(1, 2, 3, 4, 5, 6))))).isEqualTo(Rank.FIRST)
    }

    private fun lottoNumberList(numberList: List<Int>): List<LottoNumber> = numberList.map { it -> LottoNumber.valueOf(it) }
}
