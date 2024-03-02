package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class CountTest() {
    @Test
    fun `로또 구매 장 수를 저장한다`() {
        assertThat(Count(3).amount).isEqualTo(3)
    }

    @Test
    fun `구입금액과 수동 구매 로또 장 수의 입력을 받아 구매가 가능한지 판별한다`() {
        assertThat(Count(3).isPurchasable(money)).isTrue
    }

    @Test
    fun `구입금액과 수동 구매 로또 장 수의 입력을 받아 자동 로또 구매 장 수를 계산한다`() {
        assertThat(Count(3).getAutoCount(money)).isEqualTo(11)
    }

    companion object {
        private val money = Money(BigDecimal(14000))
    }
}
