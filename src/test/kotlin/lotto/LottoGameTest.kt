package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGameTest {
    @Test
    fun `6개 숫자를 넣으면 일치한 숫자의 개수를 알 수 있다`() {
        val number: List<Int> = listOf(1, 2, 3, 4, 5, 6)
        val winningNumber: List<Int> = listOf(3, 4, 5, 6, 7, 8)
        val lottoGame = LottoGame(winningNumber)

        assertThat(lottoGame.getSameNumberCount(number)).isEqualTo(4)
    }
}
