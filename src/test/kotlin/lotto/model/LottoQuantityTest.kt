package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoQuantityTest {
    @Test
    fun `getAutoLottoQuantity`() {
    }

    @Test
    fun `구입 금액은 1000원 이상이어야 한다`() {
        val amount = 999
        assertThrows<IllegalArgumentException> {
            LottoQuantity(amount, 0)
        }
    }

    @Test
    fun `수동 로또의 개수는 전체 로또의 개수보다 작거나 같아야 한다`() {
        val amount = 4000
        val manualLottoQuantity = 5
        assertThrows<IllegalArgumentException> {
            LottoQuantity(amount, manualLottoQuantity)
        }
    }

    @Test
    fun `수동 로또의 개수는 0 이상이다`() {
        assertThrows<IllegalArgumentException> {
            LottoQuantity(4000, -1)
        }
    }
}
