package domain

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class AmountTest {
    @ValueSource(ints = [1000, 5000, 100000])
    @ParameterizedTest
    fun `구매 금액은 1000원 이상, 100000원 이하이다`(int: Int) {
        assertDoesNotThrow {
            Amount(int)
        }
    }

    @ValueSource(ints = [999, 100001])
    @ParameterizedTest
    fun `1000부터 100000 이외의 숫자를 받으면 에러가 발생한다`(int: Int) {
        assertThrows<IllegalArgumentException> { Amount(int) }
    }
}
