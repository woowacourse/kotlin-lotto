package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoAnalyzerTest {
    @Test
    fun `5개의 당첨 번호가 일치하고 보너스 번호가 일치하지 않으면 3등이고, 일치하면 2등이다`() {
        val numbers =
            listOf(
                Lotto(numbers = listOf("1", "2", "3", "4", "5", "6")),
                Lotto(numbers = listOf("1", "2", "3", "4", "5", "8")),
            )
        val winningNumbers = Lotto(numbers = listOf("1", "2", "3", "4", "5", "7"))
        val lottoGame = LottoAnalyzer(numbers, DrawResult(winningNumbers, "8"))
        val lottoResult = lottoGame.calculateResult()

        assertThat(lottoResult).isEqualTo(LottoResult(mapOf(Rank.THIRD to 1, Rank.SECOND to 1)))
    }
}
