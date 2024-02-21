package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `2개의 로또를 구매했을 때, 2등과 3등이 된다면 수익률은 1575000 이다`() {
        val numbers =
            listOf(
                Lotto(numbers = listOf("1", "2", "3", "4", "5", "6")),
                Lotto(numbers = listOf("1", "2", "3", "4", "5", "8")),
            )
        val winningNumbers = Lotto(numbers = listOf("1", "2", "3", "4", "5", "7"))
        val lottoGame = LottoAnalyzer(numbers, DrawResult(winningNumbers, "8"))
        val lottoResult = lottoGame.calculateResult()

        Assertions.assertThat(lottoResult.getProfitRate()).isEqualTo(1575000.0)
    }
}
