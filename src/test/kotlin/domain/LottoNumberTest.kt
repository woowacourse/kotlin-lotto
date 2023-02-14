package domain

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class LottoNumberTest {
    @ValueSource(ints = [1, 45])
    @ParameterizedTest
    fun `1부터 45까지 번호를 받아 생성할 수 있다`(int: Int) {
        assertDoesNotThrow { LottoNumber(int) }
    }

    @ValueSource(ints = [0, 46])
    @ParameterizedTest
    fun `1부터 45까지 이외에 숫자를 받으면 에러가 발생한다`(int: Int) {
        assertThrows<IllegalArgumentException> { LottoNumber(int) }
    }
}
