package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AmountTest {
    @Test
    fun `구입 금액이 0 이하인 경우 예외가 발생한다`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                Amount(-1)
            }
        assertThat(exception.message).isEqualTo("[ERROR] 금액은 양수이어야 합니다.")
    }

    @Test
    fun `구입 금액이 로또 가격으로 나눌 수 없는 경우 예외가 발생한다`() {
        val amount = Amount(9999)
        val exception =
            assertThrows<IllegalArgumentException> {
                amount.moneySplit(Amount(1000))
            }
        assertThat(exception.message).isEqualTo("[ERROR] 로또 금액에 맞게 나눌 수 없습니다")
    }
}
