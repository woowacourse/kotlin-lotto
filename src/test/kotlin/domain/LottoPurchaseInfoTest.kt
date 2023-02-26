package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class LottoPurchaseInfoTest() {
    @Test
    fun `수동 발급 개수가 구매 가능 개수보다 크면 에러 발생`() {
        assertThrows<IllegalArgumentException> {
            LottoPurchaseInfo(PurchaseLottoMoney(14000), 15)
        }
    }

    @Test
    fun `수동 발급 개수가 음수면 에러 발생`() {
        assertThrows<IllegalArgumentException> {
            LottoPurchaseInfo(PurchaseLottoMoney(14000), -1)
        }
    }

    @Test
    fun `수동 구매 개수가 구매 가능 개수의 범위 안에 들어오면 에러 발생하지 않음`() {
        assertDoesNotThrow { LottoPurchaseInfo(PurchaseLottoMoney(14000), 14) }
        assertDoesNotThrow { LottoPurchaseInfo(PurchaseLottoMoney(14000), 0) }
        assertDoesNotThrow { LottoPurchaseInfo(PurchaseLottoMoney(14000), 10) }
    }

    @Test
    fun `입력받은 수동구매개수와 총 구매개수로부터 자동구매개수가 제대로 정해지는지 검사`() {
        val purchaseInfo = LottoPurchaseInfo(PurchaseLottoMoney(14000), 10)
        val expected = 4
        assertThat(purchaseInfo.autoCount).isEqualTo(expected)
    }
}
