package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.Lottos
import lotto.domain.model.WinningLotto
import lotto.domain.service.RandomLottoGenerator
import lotto.domain.value.EarningRate
import lotto.domain.value.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoWinningStatsTest {
    private lateinit var winningLotto: WinningLotto
    private lateinit var lottos: Lottos

    @BeforeEach
    fun setUp() {
        val lottoNumbers = (1..6).map { LottoNumber(it) }.toSet()
        winningLotto = WinningLotto(Lotto(lottoNumbers), LottoNumber(45))
        lottos = Lottos(listOf(RandomLottoGenerator().generateLotto()))
    }

    @Test
    fun `당첨 수익률 객체를 반환한다`() {
        val winningStats = lottos.getLottoWinningStats(winningLotto)
        assertThat(winningStats.getEarningRate()).isExactlyInstanceOf(EarningRate::class.java)
    }
}
