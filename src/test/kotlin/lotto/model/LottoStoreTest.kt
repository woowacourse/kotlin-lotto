package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoStoreTest {
    private val lottoStore =
        LottoStore.buyLottos(
            PurchaseOrder(5000),
            object : LottoNumberGenerator {
                override fun generate() = listOf(1, 2, 3, 4, 5, 6)
            },
        )

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6])
    fun `발행한 로또의 값을 확인한다`(number: Int) {
        val actual = lottoStore.lottos.first().contains(LottoNumber(number))
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `발행한 로또의 개수를 확인한다`() {
        val actual = lottoStore.lottos.size
        assertThat(actual).isEqualTo(5)
    }
}
