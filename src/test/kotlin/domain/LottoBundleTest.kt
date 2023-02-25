package domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoBundleTest {

    @Test
    fun `수동으로 로또를 발행`() {
        // given
        val lottoBundle = LottoBundle()
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
        lottoBundle.manualGenerate(lotto)

        // then
        assertThat(lottoBundle.lottos[0]).isEqualTo(lotto)
    }

    @Test
    fun `자동으로 발행해야할 로또 개수만큼 발행`() {
        // given
        val lottoBundle = LottoBundle()
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
