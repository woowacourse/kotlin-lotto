package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ValueSource(ints = [1, 2, 3, 43, 44, 45])
    @ParameterizedTest
    fun `로또 번호는 1~45 사이이다`(input: Int) {
        val lottoNumber = LottoNumber(input)
        assertThat(lottoNumber.number).isEqualTo(input)
    }

    @ValueSource(ints = [0, -1, 46, 100, 1000])
    @ParameterizedTest
    fun `로또 번호가 1~45 사이 숫가자 아니면 에러가 발생한다`(input: Int) {
        assertThrows<IllegalArgumentException> {
            LottoNumber(input)
        }
    }
}
