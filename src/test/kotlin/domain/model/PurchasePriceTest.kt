package domain.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PurchasePriceTest {
    @Test
    fun `구입 금액이 0 이하면 예외가 발생한다`() {
        Assertions
            .assertThatThrownBy {
                PurchasePrice(0)
            }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(INVALID_MINIMUM_PURCHASE_AMOUNT)
    }

    @Test
    fun `구입 금액이 천원 단위가 아니면 예외가 발생한다`() {
        Assertions
            .assertThatThrownBy {
                PurchasePrice(10001)
            }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(INVALID_THOUSAND_WON_UNIT)
    }

    companion object {
        const val INVALID_MINIMUM_PURCHASE_AMOUNT = "[ERROR] 천원 이상 입력해주세요."
        const val INVALID_THOUSAND_WON_UNIT = "[ERROR] 천원 단위로 입력해주세요."
    }
}
