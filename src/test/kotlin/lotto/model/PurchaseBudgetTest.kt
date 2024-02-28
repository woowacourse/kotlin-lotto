package lotto.model

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PurchaseBudgetTest {
    @ParameterizedTest
    @CsvSource("1000, 500", "500, 1000")
    fun `예산은 수동 로또 혹은 자동 로또 한장 가격 이상이여야한다`(
        pricePerManualLotto: Int,
        pricePerAutolLotto: Int,
    ) {
        val totalBudget = 800
        assertDoesNotThrow { PurchaseBudget(totalBudget, pricePerManualLotto, pricePerAutolLotto) }
    }
}
