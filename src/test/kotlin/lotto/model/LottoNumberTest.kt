package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [-2, 0, 50])
    fun `1~45 사이의 숫자가 아닌 경우 예외가 발생한다`(number: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber(number)
        }
    }

    @Test
    fun `번호가 같으면 같은 객체이다`() {
        assertThat(LottoNumber(1)).isEqualTo(LottoNumber(1))
    }
}
