package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 번호들은 중복이 없어야 한다`() {
        val numbers = listOf("1", "2", "3", "3", "4", "5")
        assertThrows<IllegalArgumentException> {
            Lotto(numbers.map { LottoNumber.valueOf(it) })
        }
    }
}
