package lotto.value

import lotto.domain.value.LottoPayInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class LottoPayInfoTest {
    @ParameterizedTest
    @ValueSource(ints = [-100, -1000, -45, 0, 999])
    fun `구매 금액이 로또 1장 가격보다 작으면 예외가 발생한다`(purchaseAmount: Int) {
        assertThrows<IllegalArgumentException> {
            LottoPayInfo(purchaseAmount, 0)
        }
    }

    @ParameterizedTest
    @CsvSource(
        "10000, 5, 5",
        "10000, 10, 0",
        "10000, 0, 10",
    )
    fun `자동 로또 구매수량은 전체 구매금액과 수동 구매수량을 사용해 결정된다`(
        payAmount: Int,
        manualQuantity: Int,
        autoQuantity: Int,
    ) {
        // Given When
        val payInfo = LottoPayInfo(payAmount, manualQuantity)

        // Then
        assertThat(payInfo.autoLottoQuantity).isEqualTo(autoQuantity)
    }
}
