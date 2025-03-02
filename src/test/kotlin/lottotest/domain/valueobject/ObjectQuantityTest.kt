package lottotest.domain.valueobject

import lotto.domain.valueobject.ObjectQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ObjectQuantityTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, -1000])
    fun `로또 수량이 음수라면 인스턴스를 생성하지 않는다`(quantity: Int) {
        assertThrows<IllegalArgumentException> {
            ObjectQuantity(quantity)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1000, 123_000])
    fun `양의 정수로 로또 수량을 생성하면 인스턴스에 입력한 값을 포함한다`(quantity: Int) {
        // given when
        val lottoQuantity = ObjectQuantity(quantity)

        // then
        assertThat(lottoQuantity.quantity).isEqualTo(quantity)
    }
}
