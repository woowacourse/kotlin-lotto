package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoPurchasePlanTest {
    @Test
    fun `따로 입력받거나 설정하지 않으면, 전체 예산에서 수동 로또 구입 금액을 뺀 만큼 자동 로또 구입을 한다`() {
        val totalBudget = 10000
        val pricePerLotto = 1000
        val purchaseBudget = LottoPurchaseBudget(totalBudget, pricePerLotto)
        val manualPurchaseCount = 2
        val lottoPurchasePlan = LottoPurchasePlan(purchaseBudget, manualPurchaseCount)
        assertThat(lottoPurchasePlan.autoPurchaseCount).isEqualTo(8)
    }

    @Test
    fun `자동 로또 구입 금액과 수동 로또 구입 금액의 합은 예산을 초과할 수 없다`() {
        val totalBudget = 10000
        val pricePerLotto = 1000
        val purchaseBudget = LottoPurchaseBudget(totalBudget, pricePerLotto)
        val manualPurchaseCount = 2
        val autoPurchaseCount = 9
        assertThrows<IllegalArgumentException> {
            LottoPurchasePlan(purchaseBudget, manualPurchaseCount, autoPurchaseCount)
        }
    }
}
