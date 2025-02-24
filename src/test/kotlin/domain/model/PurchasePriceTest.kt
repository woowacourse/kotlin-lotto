package domain.model

import domain.model.PurchasePrice.Companion.DEFAULT_AMOUNT_UNIT
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PurchasePriceTest {
    @Test
    fun `구입 금액이 0 이하면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException>(
            message = "[ERROR] ${DEFAULT_AMOUNT_UNIT}원 이상 입력해주세요.",
        ) {
            PurchasePrice(0)
        }
    }

    @Test
    fun `구입 금액이 천원 단위가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException>(
            message = "[ERROR] ${DEFAULT_AMOUNT_UNIT}원 단위로 입력해주세요",
        ) {
            PurchasePrice(10001)
        }
    }
}
