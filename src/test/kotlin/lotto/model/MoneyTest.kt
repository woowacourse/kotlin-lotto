package lotto.model

import lotto.exception.Exceptions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {
    @ParameterizedTest
    @CsvSource("8000,8", "1000,1", "15000,15")
    fun `구매 금액 1000원당, 로또를 1장씩 구매할 수 있다`(
        purchaseAmount: String,
        numberOfLotto: Int,
    ) {
        val money = Money.from(purchaseAmount)
        val actual = money.calculateNumberOfLotto()

        assertThat(actual).isEqualTo(numberOfLotto)
    }

    @ParameterizedTest
    @CsvSource("5,2", "5,1", "5,0")
    fun `자동 로또의 수는 총발행 로또 수에서 수동 로또 수를 빼면 구할 수 있다`(
        numberOfLotto: Int,
        manualLotto: Int,
    ) {
        val money = Money.from("5000")
        val actual = numberOfLotto - manualLotto
        val autoCount = money.calculateAutoLottoCount(manualLotto)

        assertThat(actual).isEqualTo(autoCount)
    }

    @ParameterizedTest
    @ValueSource(strings = ["8000", "1000", "15000"])
    fun `올바른 구매 금액에 대해서, 에러를 던지지 않아야한다`(purchaseAmount: String) {
        assertDoesNotThrow { Money.from(purchaseAmount) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["400", "999", "-1"])
    fun `구매 금액이 1000원 미만인 경우, 에러를 던져야한다`(purchaseAmount: String) {
        assertThrows<Exceptions.PurchaseAmountRangeException> {
            Money.from(purchaseAmount)
        }
    }
}
