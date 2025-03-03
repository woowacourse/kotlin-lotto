package lotto

import lotto.domain.LottoFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoFactoryTest() {
    private val factory = LottoFactory()

    @Test
    fun `수동 로또 생성`() {
        val numbers = listOf(7, 8, 9, 10, 11, 12)
        val lotto = factory.generateManualLotto(numbers)
        assertThat(lotto.lottoNums).isEqualTo(numbers)
    }
}
