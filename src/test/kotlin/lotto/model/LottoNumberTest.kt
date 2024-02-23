package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoNumberTest {
    @ParameterizedTest
    @CsvSource("한글", "46")
    fun `로또 번호는 1부터 45사이의 자연수로 구성이 된다`(strNumber: String) {
        assertThrows<IllegalArgumentException> {
            LottoNumber.from(strNumber)
        }
    }
}
