package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class LottoBuyBudgetTest {
    companion object {
        @JvmStatic
        fun provideLottoPricesAndInvalidPrices() =
            listOf(
                arrayOf(arrayOf(-1, 0, 4999), 5000),
                arrayOf(arrayOf(-1, 0, 999), 1000),
            )
    }

    @ParameterizedTest
    @MethodSource("provideLottoPricesAndInvalidPrices")
    fun `예산(구입 금액)은 로또 1장의 가격 이상이여야한다`(
        availableFunds: Array<Int>,
        pricePerLotto: Int,
    ) {
        availableFunds.forEach { availableFund ->
            assertThrows<IllegalArgumentException> {
                LottoBuyBudget(availableFund, pricePerLotto)
            }
        }
    }
}
