package lottotest.domain.valueobject

import lotto.domain.valueobject.LottoQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoQuantityTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 123546])
    fun `로또의 개수는 1 이상의 정수 값이다`(quantity: Int) {
        // given when
        val lottoQuantity = LottoQuantity(quantity)

        // then
        assertThat(lottoQuantity.quantity).isEqualTo(quantity)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1000])
    fun `로또의 개수가 0 이하일 수는 없다`(quantity: Int) {
        assertThrows<IllegalArgumentException> {
            LottoQuantity(quantity)
        }
    }
}
