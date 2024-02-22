package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoMachineTest {
    @ParameterizedTest
    @CsvSource("-1", "한글", "999")
    fun `구입 금액은 자연수이면서 1000 이상이다`(price: String) {
        assertThrows<IllegalArgumentException> {
            LottoMachine(price)
        }
    }
}
