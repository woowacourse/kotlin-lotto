package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PurchaseAmountTest {
    @ValueSource(ints = [0, 1, 2])
    @ParameterizedTest
    fun `구매 금액은 양수여야 한다`(purchaseAmount: Int) {
        assertDoesNotThrow {
            PurchaseAmount(purchaseAmount)
        }
    }

    @ValueSource(ints = [-1, -2, -3])
    @ParameterizedTest
    fun `구매 금액이 0미만이라면 예외가 발생한다`(purchaseAmount: Int) {
        assertThrows<IllegalArgumentException> {
            PurchaseAmount(purchaseAmount)
        }
    }

    @CsvSource(
        "0, 0",
        "1000, 1",
        "10000, 10",
        "14000, 14",
    )
    @ParameterizedTest(name = "{0}원인 경우 {1}장을 구매할 수 있다")
    fun `구매 금액을 통해 로또 구매 가능 개수를 계산할 수 있다`(
        amount: Int,
        count: Int,
    ) {
        val actual = PurchaseAmount(amount).calculatePurchaseLottoCount(1000)

        assertThat(actual).isEqualTo(count)
    }

    @Test
    fun `초기 구매 금액 3000원에서 1000원 사용하면 2000원이 남아야 한다`() {
        val purchaseAmount = PurchaseAmount(3000)
        purchaseAmount.reducePurchaseAmount(1000)
        val expected = 2000

        assertThat(purchaseAmount.amount).isEqualTo(expected)
    }

    @Test
    fun `초기 구매 금액 1000원에서 2000원 사용하면 예외가 발생한다`() {
        val purchaseAmount = PurchaseAmount(1000)

        assertThrows<IllegalArgumentException> {
            purchaseAmount.reducePurchaseAmount(2000)
        }
    }
}
