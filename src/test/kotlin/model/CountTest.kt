package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class CountTest() {
    @Test
    fun `로또 구매 장 수를 저장한다`() {
        assertThat(Count.from(3, Money.from(14000)).amount).isEqualTo(3)
    }

    @Test
    fun `구입금액과 수동 구매 로또 장 수의 입력을 받아 구매가 가능한지 판별한다`() {
        assertDoesNotThrow { (Count.from(3, Money.from(14000))) }
    }

    @Test
    fun `구입금액과 수동 구매 로또 장 수의 입력을 받아 자동 로또 구매 장 수를 계산한다`() {
        assertThat(Count.from(3, Money.from(14000)).getAutoCount(Money.from(14000))).isEqualTo(11)
    }
}
