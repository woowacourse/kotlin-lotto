package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoSellerTest {
    @Test
    fun `로또를 한 장 발급한다`() {
        val lottoSeller = LottoSeller(TestNumberGenerator())
        val lotto = lottoSeller.sellLotto()
        assertThat(lotto.numbers).isEqualTo(setOf(1, 2, 3, 4, 5, 45))
    }

    inner class TestNumberGenerator : RandomGenerator {
        override fun generate(): Set<Int> {
            return setOf(1, 2, 3, 4, 5, 45)
        }
    }
}
