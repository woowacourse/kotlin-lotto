package lotto

import lotto.domain.LottoFactory
import lotto.domain.LottoNumber
import lotto.service.AutoLottoGenerator
import lotto.service.LottoNumberGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoFactoryTest() {
    private val factory = LottoFactory()

    @Test
    fun `자동 로또 생성`() {
        val generator = LottoNumberGenerator { listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) } }

        val lotto = factory.generateAutoLotto(generator as AutoLottoGenerator)
        assertThat(lotto.lottoNums).isEqualTo(generator.generate())
    }

    @Test
    fun `수동 로또 생성`() {
        val numbers = listOf(7, 8, 9, 10, 11, 12)
        val lotto = factory.generateManualLotto(numbers)
        assertThat(lotto.lottoNums).isEqualTo(numbers)
    }
}
