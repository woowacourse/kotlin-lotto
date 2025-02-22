package lotto.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoPurchaseAmountTest {
    @ValueSource(ints = [1234, 3456, 100])
    @ParameterizedTest
    fun `구매 가격이 1000 단위가 아니면 IllegalArgumentException이 일어난다`(input: Int) {
        assertThrows<IllegalArgumentException> {
            LottoPurchaseAmount(input)
        }
    }

    @ValueSource(ints = [-1000, 0, -1234])
    @ParameterizedTest
    fun `구매 가격에 0 이하의 숫자가 들어오면 IllegalArgumentException이 일어난다`(input: Int) {
        assertThrows<IllegalArgumentException> {
            LottoPurchaseAmount(input)
        }
    }
}
