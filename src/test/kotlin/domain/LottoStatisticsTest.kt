package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoStatisticsTest {
    @Test
    fun `당첨 번호와 일치하는 숫자의 개수를 찾는다`() {
        val lotto = Lotto(setOf(1, 2, 3, 4, 5, 6))
        val winningNumbers = setOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val matchOfCount = lottoStatistics.compareNumbers(lotto.numbers)

        assertThat(matchOfCount).isEqualTo(6)
    }
}
