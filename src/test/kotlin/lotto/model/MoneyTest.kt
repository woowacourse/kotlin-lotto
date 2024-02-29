package lotto.model

import lotto.exception.Exceptions
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MoneyTest {
    @ParameterizedTest
    @CsvSource("asdf", "한글")
    fun `구입 금액은 자연수여야 합니다`(strMoney: String) {
        assertThrows<Exceptions.PurchaseAmountNotNaturalNumberException> {
            Money.from(strMoney)
        }
    }
}
