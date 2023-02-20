package domain.lotto.size

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class LottoSizeTest {
    @ParameterizedTest
    @ValueSource(strings = ["1", "3", "5", "30", "100"])
    fun `문자열이 양수일 때, LottoSize_from() 호출시, 예외가 발생하지 않는다`(size: String) {
        assertDoesNotThrow {
            LottoSize.from(size)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "-1", "-30", "-100"])
    fun `문자열이 양수가 아닐 때, LottoSize_from() 호출시, IllegalArgumentException이 발생한다`(size: String) {
        assertThrows<IllegalArgumentException> {
            LottoSize.from(size)
        }
    }
}
