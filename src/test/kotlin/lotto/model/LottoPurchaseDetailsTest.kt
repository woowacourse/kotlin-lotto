package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoPurchaseDetailsTest {
    @Test
    fun `따로 입력받거나 설정하지 않으면, 전체 예산에서 수동 로또 구입 금액을 뺀 만큼 자동 로또 구입을 한다`() {
        val purchaseBudget = LottoBudget(10000, 1000, 1000)
        val lottoPurchaseDetails = LottoPurchaseDetails(purchaseBudget, 2)
        assertThat(lottoPurchaseDetails.autoPurchaseCount).isEqualTo(8)
    }

    @Test
    fun `자동 로또 구입 금액과 수동 로또 구입 금액의 합은 예산을 초과할 수 없다`() {
        val purchaseBudget = LottoBudget(10000, 1000, 1000)
        assertThrows<IllegalArgumentException> {
            LottoPurchaseDetails(purchaseBudget, 2, 9)
        }
    }

    @Test
    fun `전체 예산과 실제 구매 금액이 다를 수 있다`() {
        val purchaseBudget = LottoBudget(10000, 1000, 1000)
        val totalPurchaseCost = LottoPurchaseDetails(purchaseBudget, 2, 5).calculateTotalPurchaseCost()
        assertThat(totalPurchaseCost).isNotEqualTo(purchaseBudget.totalBudget)
    }
}
