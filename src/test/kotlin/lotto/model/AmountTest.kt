package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AmountTest {
    @Test
    fun `2000원일 때 금액이 정상적으로 생성된다`() {
        // given
        val money = 2000

        // when
        val amount = Amount(money)

        // then
        assertThat(amount.value).isEqualTo(money)
    }

    @Test
    fun `초기 금액이 0원 이하면 예외를 발생시킨다`() {
        // given
        val money = 0

        // when & then
        assertThrows<IllegalArgumentException> { Amount(money) }
    }

    @Test
    fun `금액이 1000원 단위가 아니면 예외를 발생시킨다`() {
        // given
        val money = 1500

        // when & then
        assertThrows<IllegalArgumentException> { Amount(money) }
    }

    @Test
    fun `주어진 수동 로또 개수를 구매할 수 있는지 여부를 확인한다`() {
        // given
        val amount = Amount(5000)

        // when & then
        assertThat(amount.isAffordable(5)).isTrue
        assertThat(amount.isAffordable(6)).isFalse
    }

    @Test
    fun `자동 로또 구매 개수를 정확하게 반환한다`() {
        // given
        val amount = Amount(5000)

        // when & then
        assertThat(amount.getAutoLottoQuantity(2)).isEqualTo(3)
    }
}
