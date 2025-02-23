package lotto.value

import lotto.domain.value.LottoPayInfo
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoPayInfoTest {
    @ParameterizedTest
    @ValueSource(ints = [-100, -1000, -45, 0, 999])
    fun `구매 금액이 로또 1장 가격보다 작으면 예외가 발생한다`(purchaseAmount: Int) {
        assertThrows<IllegalArgumentException> {
            LottoPayInfo(purchaseAmount)
        }
    }
}
