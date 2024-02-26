package domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MoneyTest {
    @Test
    fun `돈이 0원 미만이면 예외를 던지는지`() {
        val amount = -1L

        val exception = assertThrows<IllegalArgumentException> { Money(amount) }

        assertThat(exception.message).isEqualTo("-1원은 안됩니다. 0원 이상이어야 합니다.")
    }
}
