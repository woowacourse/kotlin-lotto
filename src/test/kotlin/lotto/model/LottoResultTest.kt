package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `2개의 로또를 구매했을 때, 2등과 3등이 된다면 수익률은 15750 이다`() {
        val lottos =
            listOf(
                Lotto((1..6).map { LottoNumber.of(it) }),
                Lotto(listOf(1, 2, 3, 4, 5, 8).map { LottoNumber.of(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 7).map { LottoNumber.of(it) })
        val lottoWinningBundle = LottoWinningBundle(winningLotto, LottoNumber.of(8))
        val lottoResult = lottoWinningBundle.calculateResult(lottos)

        Assertions.assertThat(lottoResult.getProfitRate()).isEqualTo(15750.0)
    }
}
