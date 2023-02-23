package lotto.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoCountTest {
    @ValueSource(ints = [-100, -1, 6, 100])
    @ParameterizedTest
    fun `5000원을 구매하면 수동 로또 개수는 0 이상 5 이하여야 한다`(manualPurchaseNumber: Int) {
        assertThrows<IllegalArgumentException> { LottoCount.from(5, manualPurchaseNumber) }
    }
}
