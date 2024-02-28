package lotto

import lotto.model.LottoNumber
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(strings = ["a", "-", " "])
    fun `로또번호가 숫자가 아닐 경우 예외가 발생한다`(number: String) {
        assertThrows<IllegalArgumentException> {
            LottoNumber.from(number)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 46, 100])
    fun `로또번호가 1~45 사이가 아닐 경우 예외가 발생한다`(number: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber(number)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", "0", "46", "100"])
    fun `로또번호가 1~45 사이가 아닐 경우 예외가 발생한다(from)`(number: String) {
        assertThrows<IllegalArgumentException> {
            LottoNumber.from(number)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 44, 45])
    fun `로또번호가 1~45 사이일 경우 예외가 발생하지 않는다`(number: Int) {
        assertDoesNotThrow { LottoNumber(number) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "2", "44", "45"])
    fun `로또번호가 1~45 사이일 경우 예외가 발생하지 않는다(from)`(number: String) {
        assertDoesNotThrow { LottoNumber.from(number) }
    }
}
