package lotto

import lotto.exception.ErrorCode
import lotto.exception.Exceptions
import lotto.model.LottoNumber
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(strings = ["1", "2", "45", "34"])
    fun `올바른 로또 번호에 대해서, 에러를 던지지 않아야한다`(number: String) {
        assertDoesNotThrow { LottoNumber.from(number) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["apple", "12fj", "ㅇㄹ"])
    fun `숫자로 변환 할 수 없는 로또 번호에 대해서, INVALID_LOTTO_NUMBER을 던져야한다`(number: String) {
        val exception =
            assertThrows<Exceptions.LottoNumberException> {
                LottoNumber.from(number)
            }

        assertEquals(ErrorCode.INVALID_LOTTO_NUMBER.message, exception.message)
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", "0", "123"])
    fun `숫자 범위를 벗어나는 로또 번호에 대해서, INVALID_LOTTO_NUMBER_RANGE을 던져야한다`(number: String) {
        val exception =
            assertThrows<Exceptions.LottoNumberRangeException> {
                LottoNumber.from(number)
            }

        assertEquals(ErrorCode.INVALID_LOTTO_NUMBER_RANGE.message, exception.message)
    }
}
