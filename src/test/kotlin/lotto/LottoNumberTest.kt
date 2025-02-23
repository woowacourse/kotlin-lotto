package lotto

import lotto.domain.LottoNumber
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 10, 22, 44])
    fun `로또 번호는 1~45 숫자 사이에 해당한다`(value: Int) {
        assertDoesNotThrow { LottoNumber(value) }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 49, 0])
    fun `로또 번호는 1~45 숫자 사이에 없을 경우 예외가 발생한다`(value: Int) {
        assertThrows<IllegalArgumentException> { LottoNumber(value) }
    }

    @ParameterizedTest
    @EmptySource
    fun ` 보너스 볼 번호는 공백이 불가하다`(num: String) {
        assertThrows<IllegalArgumentException> { LottoNumber(num.toInt()) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1번", "seven", "-1"])
    fun ` 보너스 볼 번호는 정수 형태로 입력되어야 한다`(input: String) {
        assertThrows<IllegalArgumentException> { LottoNumber(input.toInt()) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["lotto", "seven", "1번"])
    fun `당첨 번호는 정수 형태로 입력되어야 한다`(winningNumber: String) {
        assertThrows<IllegalArgumentException> { LottoNumber(winningNumber.toInt()) }
    }
}
