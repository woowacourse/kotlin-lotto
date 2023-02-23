package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class MoneyTest {
    @Test
    fun `금액이 1000원 단위인 경우 성공`() {
        assertDoesNotThrow { Money(1000) }
    }

    @Test
    fun `금액이 1000원 단위가 아닌 경우 실패`() {
        assertThrows<IllegalArgumentException> { Money(1500) }
    }

    @Test
    fun `금액이 0원인경우 실패`() {
        assertThrows<IllegalArgumentException> { Money(0) }
    }
}
