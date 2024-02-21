package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoTest {
    @ParameterizedTest
    @CsvSource("1,2,3,3,4,5", "1,2,3,4,4,5", "1,2,3,4,45,45")
    fun `로또 번호는 중복이 되면 안된다`(strNumber: String) {
        val numbers = strNumber.split(",").map { LottoNumber(it) }
        assertThrows<IllegalArgumentException> {
            Lotto(numbers)
        }
    }
}
