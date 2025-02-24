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
        assertDoesNotThrow { LottoNumber.of(value) }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 49, 0, 46])
    fun `로또 번호는 1~45 숫자 사이에 없을 경우 예외가 발생한다`(value: Int) {
        assertThrows<IllegalArgumentException> { LottoNumber.of(value) }
    }

    @ParameterizedTest
    @EmptySource
    fun ` 보너스 볼 번호는 공백이 불가하다`(num: String) {
        assertThrows<IllegalArgumentException> { LottoNumber.of(num.toInt()) }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 7, 9, 44])
    fun ` 보너스 볼 번호는 정수 형태로 입력되어야 한다`(input: Int) {
        assertDoesNotThrow { LottoNumber.of(input) }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 48, 0, 46, 90])
    fun ` 보너스 볼 번호는 1~45 숫자 사이에 해당한다`(input: Int) {
        assertThrows<IllegalArgumentException> { LottoNumber.of(input) }
    }
}
