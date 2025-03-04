package lottotest.domain.valueobject

import lotto.domain.valueobject.WinningQuantity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WinningQuantityTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 123546])
    fun `당첨 수량은 0 이상의 정수 값이다`(quantity: Int) {
        // given when
        val winningQuantity = WinningQuantity(quantity)

        // then
        assertThat(winningQuantity.quantity).isEqualTo(quantity)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -1000])
    fun `당첨 수량이 음수일 수는 없다`(quantity: Int) {
        assertThrows<IllegalArgumentException> {
            WinningQuantity(quantity)
        }
    }
}
