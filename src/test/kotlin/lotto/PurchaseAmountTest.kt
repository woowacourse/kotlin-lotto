package lotto

import lotto.model.PurchaseAmount
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PurchaseAmountTest {
    @ParameterizedTest
    @CsvSource("l000, 3", "1ooo, 5")
    fun `구입금액이 숫자가 아닌 경우 예외가 발생한다`(
        invalidMoney: String,
        validNumberOfManualLottos: String,
    ) {
        assertThrows<IllegalArgumentException> { PurchaseAmount(invalidMoney, validNumberOfManualLottos) }
    }

    @ParameterizedTest
    @CsvSource("0, 3", "999, 5")
    fun `구입금액이 1000이상이 아닌경우 예외가 발생한다`(
        invalidMoney: String,
        validNumberOfManualLottos: String,
    ) {
        assertThrows<IllegalArgumentException> { PurchaseAmount(invalidMoney, validNumberOfManualLottos) }
    }

    @ParameterizedTest
    @CsvSource("10000, three", "2000, o")
    fun `자동 로또수량이 숫자가 아닌경우 예외가 발생한다`(
        invalidMoney: String,
        validNumberOfManualLottos: String,
    ) {
        assertThrows<IllegalArgumentException> { PurchaseAmount(invalidMoney, validNumberOfManualLottos) }
    }

    @ParameterizedTest
    @CsvSource("10000, -1", "2000, -3000")
    fun `자동 로또수량이 0미만일 경우 예외가 발생한다`(
        invalidMoney: String,
        validNumberOfManualLottos: String,
    ) {
        assertThrows<IllegalArgumentException> { PurchaseAmount(invalidMoney, validNumberOfManualLottos) }
    }

    @ParameterizedTest
    @CsvSource("3000, 4", "2000, 3")
    fun `자동 로또수량이 구입금액을 초과하는 경우 예외가 발생한다`(
        invalidMoney: String,
        validNumberOfManualLottos: String,
    ) {
        assertThrows<IllegalArgumentException> { PurchaseAmount(invalidMoney, validNumberOfManualLottos) }
    }
}
