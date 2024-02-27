package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ResultTest {
    @Test
    fun `2개의 로또를 구매했을 때, 2등과 3등이 된다면 수익률은 15750 이다`() {
        val lottos =
            listOf(
                Lotto.of(1, 2, 3, 4, 5, 6),
                Lotto.of(1, 2, 3, 4, 5, 8),
            )
        val winningLotto = Lotto.of(1, 2, 3, 4, 5, 7)
        val lottoWinningBundle = LottoWinningBundle(winningLotto, LottoNumber.of(8))
        val lottoResult = lottoWinningBundle.calculateResult(lottos)

        Assertions.assertThat(lottoResult.getProfitRate(2000)).isEqualTo(15750.0)
    }
}
