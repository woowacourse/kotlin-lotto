package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 46])
    fun `범위(1~45)를 벗어나는 숫자가 들어오면 오류가 발생한다`(input: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber.create(input)
        }
    }
}
