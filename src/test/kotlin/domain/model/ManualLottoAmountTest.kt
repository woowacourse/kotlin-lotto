package domain.model

import domain.model.ManualLottoAmount.Companion.toManualLottoAmountResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLottoAmountTest {
    @Test
    fun `수동 로또 구매 개수는 총 구매 개수를 넘을 수 없다`() {
        val tryManualLottoAmount = 15
        val totalPurchaseAmount = 10

        val actual = tryManualLottoAmount.toManualLottoAmountResult(totalPurchaseAmount)
        val expected = ManualLottoAmountResult.CannotMoreThanTotalPurchaseAmount::class.java

        assertThat(actual).isInstanceOf(expected)
    }
}
