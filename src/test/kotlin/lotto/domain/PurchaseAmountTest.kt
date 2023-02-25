package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchaseAmountTest {
    @ParameterizedTest
    @ValueSource(ints = [1000, 50000])
    fun `1000원 이상 50000 이하의 구입금액을 생성할 수 있다`(amount: Int) {
        val purchaseAmount = PurchaseAmount(amount, 0)
        assertThat(purchaseAmount.toInt()).isEqualTo(amount)
    }

    @Test
    fun `수동 구매 개수를 가진다`() {
        assertDoesNotThrow { PurchaseAmount(2000, 2) }
    }

    @Test
    fun `구입금액과 수동 구매 개수를 이용해 자동 구입 개수를 계산해 가진다`() {
        val purchaseAmount = PurchaseAmount(5000, 1)
        assertThat(purchaseAmount.manualNumber).isEqualTo(4)
    }

    @ParameterizedTest
    @ValueSource(ints = [1000, 50000])
    fun `구입금액이 1000원 이상이고 5만원 이하면 에러가 발생하지 않는다`(amount: Int) {
        assertDoesNotThrow {
            PurchaseAmount(amount, 0)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [999, 900, 51000, 50001])
    fun `구입금액이 1000원 이상이 아니고 5만원을 초과하면 에러가 발생한다`(amount: Int) {
        assertThrows<IllegalArgumentException> {
            PurchaseAmount(amount, 0)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1000, 50000])
    fun `구입금액이 1000원 단위면 에러가 발생하지 않는다`(amount: Int) {
        assertDoesNotThrow { PurchaseAmount(amount, 0) }
    }

    @ParameterizedTest
    @ValueSource(ints = [1001, 49999])
    fun `구입금액이 1000원 단위로 나누어 떨어지지 않으면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> { PurchaseAmount(1001, 0) }
    }

    @Test
    fun `수동 로또의 수가 구입금액을 1000으로 나눈 수 이하라면 에러가 발생하지 않는다`() {
        assertDoesNotThrow { PurchaseAmount(1000, 0) }
    }

    @Test
    fun `수동 로또의 수가 구입금액을 1000으로 나눈 수를 초과하면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> { PurchaseAmount(1000, 2) }
    }
}
