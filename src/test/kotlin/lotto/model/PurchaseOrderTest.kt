package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PurchaseOrderTest {
    @ParameterizedTest
    @ValueSource(ints = [1000, 14000, 30000])
    fun `로또 하나의 금액이 1000원이고, 구매 금액이 올바른 경우 예외가 발생하지 않는다`(purchasePrice: Int) {
        assertDoesNotThrow { PurchaseOrder(purchasePrice, lottoPrice = 1000) }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1000, 1000100, 100])
    fun `로또 하나의 금액이 1000원이고, 구매 금액이 잘못된 경우 예외가 발생한다`(purchasePrice: Int) {
        assertThrows<IllegalArgumentException> { PurchaseOrder(purchasePrice, lottoPrice = 1000) }
    }

    @ParameterizedTest
    @CsvSource("1000, 0", "1000, 1", "10000, 5", "3000, 2")
    fun `로또 하나의 금액이 1000원이고, 수동 로또의 개수가 올바른 경우 예외가 발생하지 않는다`(
        purchasePrice: Int,
        manualLottoSize: Int,
    ) {
        assertDoesNotThrow { PurchaseOrder(purchasePrice, manualLottoSize, 1000) }
    }

    @ParameterizedTest
    @CsvSource("1000, 10", "10000, 11", "10000, -1")
    fun `로또 하나의 금액이 1000원이고, 수동 로또의 개수가 잘못된 경우 예외가 발생한다`(
        purchasePrice: Int,
        manualLottoSize: Int,
    ) {
        assertThrows<IllegalArgumentException> { PurchaseOrder(purchasePrice, manualLottoSize, 1000) }
    }

    @ParameterizedTest
    @CsvSource("1000, 0, 1", "10000, 5, 5", "14000, 3, 11")
    fun `로또 하나의 금액이 1000원이고, 수동 로또 개수의 나머지를 자동 로또의 개수로 저장한다`(
        purchasePrice: Int,
        manualLottoSize: Int,
        expected: Int,
    ) {
        val actual = PurchaseOrder(purchasePrice, manualLottoSize, 1000).automaticLottoSize
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
