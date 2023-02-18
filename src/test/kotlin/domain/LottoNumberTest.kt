package domain

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest(name = "{0}인 경우")
    @ValueSource(ints = [1, 30, 45])
    fun `로또 번호는 1이상 45이하의 수만 가능하다`(number: Int) {
        assertDoesNotThrow { LottoNumber(number) }
    }

    @ParameterizedTest(name = "{0}인 경우")
    @ValueSource(ints = [0, -1, 46])
    fun `로또 번호는 0이하 46이상일 수 없다`(number: Int) {
        assertThrows<IllegalArgumentException> { LottoNumber(number) }
    }
}
