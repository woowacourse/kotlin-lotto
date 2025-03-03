package lotto.domain.value

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoCountTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 1, 10, 30, 50])
    fun `로또 개수는 0 이상이다`(count: Int) {
        assertDoesNotThrow {
            LottoCount(count)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [-100, -10, -3, -1])
    fun `로또 개수가 0 이상이 아니면 예외가 발생한다`(count: Int) {
        assertThrows<IllegalArgumentException> {
            LottoCount(count)
        }
    }
}
