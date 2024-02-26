package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PurchaseInfoTest {
    @ParameterizedTest
    @ValueSource(ints = [1000, 14000, 30000])
    fun `구매 금액이 올바른 경우 예외가 발생하지 않는다`(purchasePrice: Int) =
        assertDoesNotThrow {
            PurchaseInfo(purchasePrice)
        }

    @ParameterizedTest
    @ValueSource(ints = [0, -1000, 1000100, 100])
    fun `구매 금액이 잘못된 경우 예외가 발생한다`(purchasePrice: Int) =
        assertThrows<IllegalArgumentException> {
            PurchaseInfo(purchasePrice)
        }

    @ParameterizedTest
    @CsvSource("1000, 1", "10000, 10")
    fun `구매할 수 있는 로또의 개수를 계산한다`(
        purchasePrice: Int,
        expected: Int,
    ) {
        val actual = PurchaseInfo(purchasePrice).amount
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
