package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `2개의 로또를 구매했을 때, 2등과 3등이 된다면 수익률은 15750 이다`() {
        val lottoNumbers =
            listOf(
                Lotto(
                    setOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                    ),
                ),
                Lotto(
                    setOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(8),
                    ),
                ),
            )
        val winningLottoNumbers =
            Lotto(
                setOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(7),
                ),
            )
        val lottoResult =
            LottoAnalyzer.calculateResult(LottoBundle(lottoNumbers), DrawResult(winningLottoNumbers, LottoNumber.from("8")))

        Assertions.assertThat(lottoResult.getProfitRate()).isEqualTo(15750.0)
    }
}
