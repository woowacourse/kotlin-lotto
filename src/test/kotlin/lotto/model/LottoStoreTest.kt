package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoStoreTest {
    private val automaticLottos =
        LottoStore.buyLottos(
            5,
            object : LottoMachine {
                override fun generate() = listOf(1, 2, 3, 4, 5, 6)
            },
        )

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6])
    fun `자동 로또의 번호를 임의로 지정해주면 해당 번호로 로또가 발행된다`(number: Int) {
        automaticLottos.forEach { lotto ->
            val actual = lotto.contains(LottoNumber.from(number))
            assertThat(actual).isEqualTo(true)
        }
    }

    @Test
    fun `지정해준 자동 로또의 개수만큼 로또가 발행된다`() {
        val actual = automaticLottos.size
        assertThat(actual).isEqualTo(5)
    }
}
