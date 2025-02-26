package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.WinningLotto
import lotto.domain.value.LottoNumber
import lotto.enums.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoCalculatorTest {
    private lateinit var winningLotto: WinningLotto
    private val lottoCalculator = LottoCalculator()

    @BeforeEach
    fun setUp() {
        val winningNumbers = Lotto.of(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.from(45)
        winningLotto = WinningLotto(winningNumbers, bonusNumber)
    }

    @Test
    fun `로또 1등이 한번 당첨되면 1등 당첨 횟수는 1이다`() {
        // given
        val firstRankLotto = Lotto.of(1, 2, 3, 4, 5, 6)
        val lottos = listOf(firstRankLotto)

        // when
        val lottoResult = lottoCalculator.calculate(winningLotto, lottos)
        val winningStats = lottoResult.winningStats

        // then
        assertThat(winningStats[Rank.FIRST]).isEqualTo(1)
    }

    @Test
    fun `당첨되지 않은 로또가 1개이면 미당첨 횟수는 1이다`() {
        // given
        val firstRankLotto = Lotto.of(1, 2, 3, 4, 5, 6)
        val missRankLotto = Lotto.of(11, 12, 13, 14, 15, 16)
        val lottos = listOf(firstRankLotto, missRankLotto)

        // when
        val lottoResult = lottoCalculator.calculate(winningLotto, lottos)
        val winningStats = lottoResult.winningStats

        // then
        assertThat(winningStats[Rank.MISS]).isEqualTo(1)
    }
}
