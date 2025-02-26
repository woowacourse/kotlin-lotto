package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AmountTest {
    @Test
    fun `금액이 0 이하인 경우 예외가 발생한다`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                Amount(-1)
            }
        assertThat(exception.message).isEqualTo("[ERROR] 금액은 양수이어야 합니다.")
    }
}
