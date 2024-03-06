package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 45, 34])
    fun `올바른 로또 번호에 대해서, 에러를 던지지 않아야한다`(number: Int) {
        assertDoesNotThrow { LottoNumber(number) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", "0", "123"])
    fun `숫자 범위를 벗어나는 로또 번호에 대해서, 에러를 던져야한다`(number: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber(number)
        }
    }

    @Test
    fun `로또 번호 생성시 같은지 확인한다`() {
        assertThat(LottoNumber(1)).isEqualTo(LottoNumber(1))
    }
}
