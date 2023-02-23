package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MoneyTest {
    @Test
    fun `로또 가격으로 나누어 떨어지지 않는 경우 오류가 발생한다`() {
        assertThrows<IllegalArgumentException> { Money(14100) }
    }

    @Test
    fun `금액을 통해 몇 장의 로또가 발급되는지 구한다`() {
        val money = Money(14000)
        Assertions.assertThat(money.getNumberOfLotto()).isEqualTo(14)
    }
}
