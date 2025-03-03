package domain.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ValueSource(ints = [0, 46])
    @ParameterizedTest
    fun `로또 번호는 1이상 45 이하여야한다`(value: Int) {
        assertThrows<IllegalArgumentException>(
            message = "[ERROR] 로또 번호는 1부터 45 사이입니다.",
        ) {
            LottoNumber.from(value)
        }
    }
}
