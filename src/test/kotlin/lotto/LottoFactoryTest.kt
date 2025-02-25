package lotto

import lotto.domain.LottoFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoFactoryTest {
    @Test
    fun `로또 개수에 따라 로또를 발급할 수 있다`() {
        val amountOfLotto = 10
        assertThat(LottoFactory().generateLottos(amountOfLotto).size).isEqualTo(10)
    }

    @Test
    fun `수동 로또를 n개 발급 할 수 있다`() {
        val manualLottos =
            listOf(
                lottoOf(1, 2, 3, 4, 5, 6),
                lottoOf(1, 2, 3, 4, 5, 6),
                lottoOf(1, 2, 3, 4, 5, 6),
            )
        assertThat(LottoFactory().generateManualLotto(manualLottos).size).isEqualTo(3)
    }
}
