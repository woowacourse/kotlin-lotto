package lotto.model

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import lotto.domain.model.Rank
import lotto.domain.model.WinningLotto
import lotto.domain.service.WinningListMaker
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningListMakerTest {
    @Test
    fun `로또들이 담긴 로또 리스트와 당첨 로또를 비교하여 순위 리스트를 반환한다`() {
        val lottoList = listOf<Lotto>(Lotto(listOf(1, 2, 3, 4, 5, 6)), Lotto(listOf(1, 2, 3, 4, 5, 7)))
        val winningLotto = WinningLotto.valueOf(listOf(1, 2, 3, 4, 5, 6).map { it -> LottoNumber.valueOf(it) }, LottoNumber.valueOf(7))
        val winningListMaker = WinningListMaker()
        assertThat(winningListMaker.calculateRanks(winningLotto, lottoList)).isEqualTo(listOf(Rank.FIRST, Rank.SECOND))
    }

    fun Lotto(numberList: List<Int>): Lotto = Lotto.valueOf(numberList.map { it -> LottoNumber.valueOf(it) })
}
