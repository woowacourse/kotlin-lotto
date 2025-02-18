package lotto.domain

import lotto.util.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGameTest {
    @Test
    fun `6개 숫자를 넣으면 일치한 숫자의 개수를 알 수 있다`() {
        val number: List<Int> = listOf(1, 2, 3, 4, 5, 6)
        val winningNumber: List<Int> = listOf(3, 4, 5, 6, 7, 8)
        val lottoGame = LottoGame(winningNumber, 1)

        assertThat(lottoGame.getSameNumberCount(number)).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `5개의 숫자가 일치하고 보너스 볼이 일치하면 2등을 반환한다`() {
        val number: List<Int> = listOf(1, 2, 3, 4, 5, 6)
        val winningNumber: List<Int> = listOf(2, 3, 4, 5, 6, 7)
        val winningBonusNumber: Int = 1
        val lottoGame = LottoGame(winningNumber, winningBonusNumber)

        assertThat(lottoGame.getSameNumberCount(number)).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `5개의 숫자가 일치하고 보너스 볼이 일치하지 않으면 3등을 반환한다`() {
        val number: List<Int> = listOf(1, 2, 3, 4, 5, 6)
        val winningNumber: List<Int> = listOf(2, 3, 4, 5, 6, 7)
        val winningBonusNumber: Int = 45
        val lottoGame = LottoGame(winningNumber, winningBonusNumber)

        assertThat(lottoGame.getSameNumberCount(number)).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `구입한 로또들의 당첨 통계를 알 수 있다`() {
        val number1: List<Int> = listOf(1, 2, 3, 4, 5, 6) // 5개 매치
        val number2: List<Int> = listOf(2, 3, 4, 5, 6, 7) // 6개 매치
        val number3: List<Int> = listOf(3, 4, 5, 6, 7, 45) // 5개 + 보너스볼 매치
        val lottos: List<Lotto> = listOf(Lotto(number1), Lotto(number2), Lotto(number3))

        val winningNumber: List<Int> = listOf(2, 3, 4, 5, 6, 7)
        val winningBonusNumber: Int = 45

        val lottoGame = LottoGame(winningNumber, winningBonusNumber)
        lottoGame.matchLotto(lottos)

        assertThat(lottoGame.winningStats).isEqualTo(listOf(1, 1, 1, 0, 0))
    }

    @Test
    fun `당첨 통계로 당첨 금액을 계산한다`() {
        val winningNumber: List<Int> = listOf(2, 3, 4, 5, 6, 7)
        val winningBonusNumber: Int = 45

        val lottoGame = LottoGame(winningNumber, winningBonusNumber)

        lottoGame.winningStats = arrayListOf(1, 1, 1, 1, 1)

        assertThat(lottoGame.calculatePrize()).isEqualTo(2_031_555_000)
    }
}
