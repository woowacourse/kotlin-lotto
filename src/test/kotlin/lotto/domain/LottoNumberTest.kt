package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ValueSource(ints = [1, 2, 44, 45])
    @ParameterizedTest
    fun `로또 번호는 1~45 사이이다`(value: Int) {
        val number = LottoNumber(value)
        assertThat(number.toInt()).isEqualTo(value)
    }

    @ValueSource(ints = [-1, 0, 46, 47])
    @ParameterizedTest
    fun `로또 번호는 1~45가 아니면 오류를 발생한다`(value: Int) {
        assertThrows<IllegalArgumentException> { LottoNumber(value) }
    }
}
