package lottotest.domain.valueobject

import lotto.domain.valueobject.LottoQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoQuantityTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 123546])
    fun `로또 수량은 0 이상의 정수 값이다`(quantity: Int) {
        // given when
        val lottoQuantity = LottoQuantity(quantity)

        // then
        assertThat(lottoQuantity.quantity).isEqualTo(quantity)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -1000])
    fun `로또 수량이 음수일 수는 없다`(quantity: Int) {
        assertThrows<IllegalArgumentException> {
            LottoQuantity(quantity)
        }
    }
}
