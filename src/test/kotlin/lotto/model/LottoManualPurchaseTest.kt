package lotto.model

import lotto.exception.Exceptions.ManualPurchaseCountNotNaturalNumberException
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoManualPurchaseTest {
    @ParameterizedTest
    @CsvSource("asd", "한글")
    fun `로또 수동 구입 개수는 숫자여야 한다`(count: String) {
        assertThrows<ManualPurchaseCountNotNaturalNumberException> {
            LottoManualPurchaseCount.from(count)
        }
    }
}
