package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class CountTest {
    @ValueSource(ints = [0, 5, 10])
    @ParameterizedTest
    fun `장수는 0 이상, 구매 금액에 로또 가격을 나눈 값이다`(int: Int) {
        assertDoesNotThrow {
            Count(int, Amount(10000))
        }
    }

    @Test
    fun `장수가 0 이상이 아니면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> { Count(-1, Amount(10000)) }
    }

    @Test
    fun `장수가 구매 금액에 로또 가격을 나눈 값 이하가 아니면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Count(11, Amount(10000))
        }
    }
}
