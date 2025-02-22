package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.WinningLotto
import lotto.domain.service.LottoCalculator
import lotto.domain.value.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoWinningStatsTest {
    private lateinit var winningLotto: WinningLotto

    @BeforeEach
    fun setUp() {
        val lottoNumbers = (1..6).map { LottoNumber(it) }.toSet()
        val winningNumbers = Lotto(lottoNumbers)
        val bonusNumber = LottoNumber(45)
        winningLotto = WinningLotto(winningNumbers, bonusNumber)
    }

    @Test
    fun `당첨 수익률을 계산한다`() {
        val thirdRankNumbers = (4..9).map { LottoNumber(it) }.toSet()
        val missRankNumbers = (11..16).map { LottoNumber(it) }.toSet()
        val lottos = mutableListOf(Lotto(thirdRankNumbers))
        repeat(13) { lottos.add(Lotto(missRankNumbers)) }

        val lottoCalculator = LottoCalculator(winningLotto, lottos)
        val winningStats = lottoCalculator.calculateWinningStats()

        assertThat(winningStats.calculateEarningRate().rate).isEqualTo(0.36)
    }
}
