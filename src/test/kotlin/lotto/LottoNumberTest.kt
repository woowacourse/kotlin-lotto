package lotto

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

data class LottoNumber(
    val number: Int,
) {
    init {
        require(number in 1..45) {
            "숫자는 1에서 45 사이여야 합니다."
        }
    }
}

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 46, -1])
    fun `로또 번호는 1에서 45 사이이다`(number: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber(number)
        }
    }
}
