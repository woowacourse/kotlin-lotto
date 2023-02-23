package domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoBundleTest {

    @Test
    fun `자동으로 발행해야할 로또 개수만큼 발행`() {
        // given
        val lottoBundle = LottoBundle(mutableListOf<Lotto>())
        val lotto = Lotto(
            setOf(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6)
            )
        )

        // when
        lottoBundle.autoGenerate(5) { lotto }

        // then
        Assertions.assertThat(lottoBundle.lottos.size).isEqualTo(5)
    }
}
