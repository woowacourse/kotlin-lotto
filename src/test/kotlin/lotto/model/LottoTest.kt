package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoTest {
    @ParameterizedTest
    @CsvSource("1,2,3,3,4,5", "1,2,3,한글,4,5", "1,2,3,4,5,46")
    fun `로또 번호는 중복이 없는 6개의 1부터 45사이의 자연수로 구성이 된다`(strNumber: String) {
        val numbers = strNumber.split(",")
        assertThrows<IllegalArgumentException> {
            Lotto(numbers)
        }
    }
}
