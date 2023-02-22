package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ManualLottoCountValidatorTest {
    @Test
    fun `수동 로또 구매 개수가 투입 금액으로 만들 수 있는 개수보다 크면 안된다`() {
        assertThrows<IllegalArgumentException> { ManualLottoCountValidator.checkAvailable(14, 10) }
    }
}
