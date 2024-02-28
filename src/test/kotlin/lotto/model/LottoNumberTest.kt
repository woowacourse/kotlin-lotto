package lotto.model

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 46, -1])
    fun `로또 번호는 1~45 사이의 자연수를 갖지 않으면 null을 반환한다`(input: Int) {
        assertNull(LottoNumber.valueOf(input))
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 45, 20])
    fun `로또 번호는 1~45 사이의 자연수를 갖는다`(input: Int) {
        assertNotNull(LottoNumber.valueOf(input))
    }
}
