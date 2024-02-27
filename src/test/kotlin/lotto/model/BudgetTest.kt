package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class BudgetTest {
    @Test
    fun `예산은 수동 로또 혹은 자동 로또 한장 가격 이상이여야한다`() {
        val totalBudget = 800
        val pricePerManualLotto = 1000
        val pricePerAutolLotto = 500
        assertDoesNotThrow { Budget(totalBudget, pricePerManualLotto, pricePerAutolLotto) }
    }

    @Test
    fun `예산은 수동 로또 혹은 자동 로또 한장 가격 이상이여야한다2`() {
        val totalBudget = 800
        val pricePerManualLotto = 500
        val pricePerAutolLotto = 1000
        assertDoesNotThrow { Budget(totalBudget, pricePerManualLotto, pricePerAutolLotto) }
    }
}
