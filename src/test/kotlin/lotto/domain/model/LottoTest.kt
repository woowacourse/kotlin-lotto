package lotto.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또는 6개의 숫자를 가지지 않으면 예외가 발생한다`() {
        val numbers = (1..7).map { LottoNumber(it) }.toSet()
        assertThrows<IllegalArgumentException> {
            Lotto(numbers)
        }
    }

    @Test
    fun `중복된 번호를 제거한 뒤 번호가 6개가 아니라면 예외가 발생한다`() {
        val numbers = listOf(1, 1, 2, 3, 4, 5)
        assertThrows<IllegalArgumentException> {
            Lotto(numbers)
        }
    }
}
