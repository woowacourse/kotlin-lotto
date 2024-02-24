package lotto

import lotto.model.PurchaseInfo
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class PurchaseInfoTest {
    @ParameterizedTest
    @CsvSource("1000, 1", "10000, 10")
    fun `구매할 수 있는 로또의 개수 계산한다`(
        purchasePrice: Int,
        amount: Int,
    ) {
        val purchaseInfo = PurchaseInfo(purchasePrice)
        Assertions.assertThat(purchaseInfo.amount).isEqualTo(amount)
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["0", "-1000", "1000100", "100", "2200000000"])
    fun `로또의 구입 금액을 검증한다`(purchasePrice: Int) =
        assertThrows<IllegalArgumentException> {
            PurchaseInfo(purchasePrice)
        }
}
