package lotto.model

import lotto.exception.Exceptions.DuplicateNumbersException
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoTest {
    @ParameterizedTest
    @CsvSource("1,2,3,3,4,5", "1,2,3,4,4,5", "1,2,3,4,45,45")
    fun `로또의 숫자들이 중복되면 DuplicateNumbersException 예외처리가 발생한다`(strNumber: String) {
        val numbers = strNumber.split(",").map { LottoNumber.from(it) }.toSet()

        assertThrows<DuplicateNumbersException> {
            Lotto(numbers)
        }
    }
}
