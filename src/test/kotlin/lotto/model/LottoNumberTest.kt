package lotto.model

import lotto.exception.Exceptions.InvalidNumberException
import lotto.exception.Exceptions.LottoNumberOutOfRangeException
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoNumberTest {
    @ParameterizedTest
    @CsvSource("46", "47", "0")
    fun `로또의 숫자가 1에서 45가 아니라면 LottoNumberOutOfRangeException 예외처리가 발생한다`(strNumber: String) {
        assertThrows<LottoNumberOutOfRangeException> {
            LottoNumber.from(strNumber)
        }
    }

    @ParameterizedTest
    @CsvSource("한글", "asd")
    fun `로또가 숫자가 아니라면 InvalidNumberException 예외처리가 발생한다`(strNumber: String) {
        assertThrows<InvalidNumberException> {
            LottoNumber.from(strNumber)
        }
    }
}
