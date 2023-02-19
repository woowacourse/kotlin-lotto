package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PurchaseAmountTest {
    @ParameterizedTest
    @ValueSource(ints = [1000, 50000])
    fun `구입금액을 가진다`(amount: Int) {
        println("${amount}원의 구입 금액을 가진다")

        val purchaseAmount = PurchaseAmount(amount)
        assertThat(purchaseAmount.amount).isEqualTo(amount)
    }

    @ParameterizedTest
    @ValueSource(ints = [1000, 50000])
    fun `구입금액이 1000원 이상이고 5만원 이하면 에러가 발생하지 않는다`(amount: Int) {
        println("구입 금액이 ${amount}원일 수 있다")

        assertDoesNotThrow {
            PurchaseAmount(amount)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [999, 900, 51000, 50001])
    fun `구입금액이 1000원 이상이 아니고 5만원을 초과하면 에러가 발생한다`(amount: Int) {
        println("구입 금액이 ${amount}원일 수 없다")

        assertThrows<IllegalArgumentException> {
            PurchaseAmount(amount)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1000, 50000])
    fun `구입금액이 1000원 단위면 에러가 발생하지 않는다`(amount: Int) {
        println("구입 금액이 ${amount}원일 수 있다")

        assertDoesNotThrow { PurchaseAmount(amount) }
    }

    @ParameterizedTest
    @ValueSource(ints = [1001, 49999])
    fun `구입금액이 1000원 단위로 나누어 떨어지지 않으면 에러가 발생한다`(amount: Int) {
        println("구입 금액이 ${amount}원일 수 없다")

        assertThrows<IllegalArgumentException> { PurchaseAmount(amount) }
    }

    @ParameterizedTest
    @CsvSource("1000, 1", "50000, 50")
    fun `구입한 로또 개수를 계산한다`(amount: Int, expected: Int) {
        println("${amount}원으로 로또 ${expected}장을 구매할 수 있다")

        val actual = PurchaseAmount(amount).getPurchaseQuantity()
        assertThat(actual).isEqualTo(expected)
    }
}
