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

    @Test
    fun `입력받은 개수만큼 로또를 발급한다`() {
        val lottoSeller = LottoSeller(object : RandomGenerator {
            val pattern = listOf(
                setOf(1, 2, 3, 4, 5, 6),
                setOf(9, 8, 7, 6, 5, 4)
            )
            var i = 0
            override fun generate(): Set<Int> {
                return pattern[i++]
            }
        })
        val lottos = lottoSeller.sellLottos(2)
        assertThat(lottos.map { lotto -> lotto.numbers }).isEqualTo(
            listOf(
                setOf(1, 2, 3, 4, 5, 6),
                setOf(9, 8, 7, 6, 5, 4)
            )
        )
    }

    inner class TestNumberGenerator : RandomGenerator {
        override fun generate(): Set<Int> {
            return setOf(1, 2, 3, 4, 5, 45)
        }
    }
}
