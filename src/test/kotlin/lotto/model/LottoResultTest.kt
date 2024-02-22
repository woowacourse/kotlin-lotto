package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `2개의 로또를 구매했을 때, 2등과 3등이 된다면 수익률은 15750 이다`() {
        val lottos =
            listOf(
                Lotto((1..6).map { LottoNumber(it) }),
                Lotto(listOf(1, 2, 3, 4, 5, 8).map { LottoNumber(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 7).map { LottoNumber(it) })
        val lottoResult = LottoAnalyzer.calculateResult(lottos, DrawResult(winningLotto, LottoNumber(8)))

        Assertions.assertThat(lottoResult.getProfitRate()).isEqualTo(15750.0)
    }
}
