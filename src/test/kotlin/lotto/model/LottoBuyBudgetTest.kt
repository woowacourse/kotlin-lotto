package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoBuyBudgetTest {
    @ParameterizedTest
    @CsvSource(
        "-1, 5000",
        "0, 5000",
        "4999, 5000",
        "-1, 1000",
        "0, 1000",
        "999, 1000",
    )
    fun `예산(구입 금액)은 로또 1장의 가격 이상이여야한다`(
        availableFund: Int,
        pricePerLotto: Int,
    ) {
        assertThrows<IllegalArgumentException> {
            LottoBuyBudget(availableFund, pricePerLotto)
        }
    }
}
