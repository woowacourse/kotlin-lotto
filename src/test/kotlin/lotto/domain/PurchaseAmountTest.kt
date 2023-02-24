package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PurchaseAmountTest {
    @ParameterizedTest(name = "{0}원의 구입 금액을 가진다")
    @ValueSource(ints = [1000, 50000])
    fun `구입금액을 가진다`(amount: Int) {
        val purchaseAmount = PurchaseAmount(amount)
        assertThat(purchaseAmount.amount).isEqualTo(amount)
    }

    @ParameterizedTest(name = "구입 금액이 {0}원일 수 있다")
    @ValueSource(ints = [1000, 50000])
    fun `구입금액이 1000원 이상이고 5만원 이하여야 한다`(amount: Int) {
        assertDoesNotThrow {
            PurchaseAmount(amount)
        }
    }

    @ParameterizedTest(name = "구입 금액이 {0}원일 수 없다")
    @ValueSource(ints = [999, 900, 51000, 50001])
    fun `구입금액이 1000원 이상이 아니고 5만원을 초과하면 안 된다`(amount: Int) {
        assertThrows<IllegalArgumentException> {
            PurchaseAmount(amount)
        }
    }

    @ParameterizedTest(name = "구입 금액이 {0}원일 수 있다")
    @ValueSource(ints = [1000, 50000])
    fun `구입금액이 1000원 단위로 나누어 떨어져야 한다`(amount: Int) {
        assertDoesNotThrow { PurchaseAmount(amount) }
    }

    @ParameterizedTest(name = "구입 금액이 {0}원일 수 없다")
    @ValueSource(ints = [1001, 49999])
    fun `구입금액이 1000원 단위로 나누어 떨어지지 않으면 안 된다`(amount: Int) {
        assertThrows<IllegalArgumentException> { PurchaseAmount(amount) }
    }

    @ParameterizedTest(name = "{0}원으로 로또 {1}장을 구매할 수 있다")
    @CsvSource("1000, 1", "50000, 50")
    fun `구입한 로또 개수를 계산한다`(amount: Int, expected: Int) {
        val actual = PurchaseAmount(amount).getPurchaseQuantity()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `수동 로또 개수가 구입 로또 개수를 초과하지 않는지 확인한다`() {
        val purchase = PurchaseAmount(10000)
        assertDoesNotThrow { purchase.checkEnough(10) }
        assertThrows<IllegalArgumentException> { purchase.checkEnough(11) }
    }

    @Test
    fun `자동 로또 개수를 계산한다`() {
        val purchase = PurchaseAmount(10000)

        val actual = purchase.getAutoPurchaseQuantity(8)

        assertThat(actual).isEqualTo(2)
    }
}
