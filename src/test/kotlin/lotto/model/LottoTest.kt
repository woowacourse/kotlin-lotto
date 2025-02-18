package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또가 6개가 아닌 번호를 가질 때 오류를 반환한다`() {
        val numbers = listOf(1, 2, 3, 4, 5)
        assertThrows<IllegalArgumentException> { Lotto(numbers) }
    }
}
