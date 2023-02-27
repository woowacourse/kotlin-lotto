package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoBundleTest {

    @Test
    fun `수동생성된 로또와 자동 생성 로또를 합쳐서 로또번들을 만든다`() {
        // given
        val manualLottos = listOf(Lotto("1, 2, 3, 4, 5, 6"))
        val autoLottos = listOf(Lotto("7, 8, 9, 10, 11, 12"))

        // when
        val lottoBundle = LottoBundle(manualLottos, autoLottos)

        // then
        assertThat(lottoBundle.lottos[0]).isEqualTo(manualLottos[0])
        assertThat(lottoBundle.lottos[1]).isEqualTo(autoLottos[0])
    }
}
