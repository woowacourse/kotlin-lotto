package domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoNumberTest {
    @ParameterizedTest
    @CsvSource("46,0")
    fun `로또 번호가 1이상 45이하가 아니라면 예외를 발생한다`(number: Int) {
        val exception = assertThrows<IllegalArgumentException> { LottoNumber(46) }
        assertEquals(LottoNumber.LOTTO_NUMBER_RANGE_ERROR_MESSAGE, exception.message)
    }
}
