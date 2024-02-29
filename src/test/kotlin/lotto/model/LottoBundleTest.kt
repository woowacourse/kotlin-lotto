package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoBundleTest {
    @Test
    fun `로또를 여러개 묶을 수 있다`() {
        val lottoBundle =
            LottoBundle(
                List(3) {
                    Lotto(
                        setOf(
                            LottoNumber(1),
                            LottoNumber(2),
                            LottoNumber(3),
                            LottoNumber(4),
                            LottoNumber(5),
                            LottoNumber(6),
                        ),
                    )
                },
            )
        val newLottoBundle = lottoBundle.append(lottoBundle)
        Assertions.assertThat(newLottoBundle.lottos.size).isEqualTo(6)
    }
}
