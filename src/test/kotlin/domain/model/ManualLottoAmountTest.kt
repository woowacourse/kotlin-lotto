package domain.model

import domain.model.ManualLottoAmount.Companion.toManualLottoAmountResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ManualLottoAmountTest {
    @Test
    fun `수동 로또 구매 개수는 총 구매 개수를 넘을 수 없다`() {
        val tryManualLottoAmount = 15
        val totalPurchaseAmount = 10

        val actual = tryManualLottoAmount.toManualLottoAmountResult(totalPurchaseAmount)
        val expected = ManualLottoAmountResult.CannotMoreThanTotalPurchaseAmount::class.java

        assertThat(actual).isInstanceOf(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 5, 10])
    fun `총 구매 개수를 넘지 않을 경우 수동 로또가 정상 구매된다`(value: Int) {
        val totalPurchaseAmount = 10

        val actual = value.toManualLottoAmountResult(totalPurchaseAmount)
        val expected = ManualLottoAmountResult.Success::class.java

        assertThat(actual).isInstanceOf(expected)
    }
}
