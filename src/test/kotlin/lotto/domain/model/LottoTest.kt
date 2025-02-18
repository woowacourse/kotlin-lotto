package lotto.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또는 6개의 숫자를 가지지 않으면 예외가 발생한다`() {
        val numbers = listOf(1, 2, 3)

        assertThrows<IllegalArgumentException> {
            Lotto(numbers)
        }
    }
}
