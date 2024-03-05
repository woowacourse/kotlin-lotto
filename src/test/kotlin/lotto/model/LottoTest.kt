package lotto.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `올바른 로또에 대해서, 에러를 던지지 않아야한다`() {
        Assertions.assertDoesNotThrow { Lotto(1, 2, 3, 4, 5, 6) }
    }

    @Test
    fun `로또 숫자가 중복인 경우에 대해서, 에러를 던져야 한다`() {
        assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 5, 5, 5) }
    }
}
