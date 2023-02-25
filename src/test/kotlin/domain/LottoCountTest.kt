package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoCountTest {
    @Test
    fun `수동 로또 개수가 0보다 작은 경우 실패`() {
        assertThrows<IllegalArgumentException> { LottoCount(-1) }
    }

    @Test
    fun `수동 로또 개수는 0인 경우 성공`() {
        assertDoesNotThrow { LottoCount(0) }
    }

    @Test
    fun `수동 로또 개수는 10인 경우 성공`() {
        assertDoesNotThrow { LottoCount(0) }
    }
}
