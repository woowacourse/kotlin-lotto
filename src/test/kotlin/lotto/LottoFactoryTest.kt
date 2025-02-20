package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoFactoryTest {
    @Test
    fun `로또 개수에 따라 로또를 발급할 수 있다`() {
        val amountOfLotto = 10
        assertThat(LottoFactory().generateLottos(amountOfLotto).size).isEqualTo(10)
    }
}
