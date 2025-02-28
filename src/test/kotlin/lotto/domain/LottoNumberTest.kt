package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 23, 45])
    fun `로또 번호는 1부터 45 사이의 값을 가진다`(value: Int) {
        assertDoesNotThrow { LottoNumber.from(value) }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 46])
    fun `로또 번호가 1부터 45 사이의 값이 아닐 경우 오류가 발생한다`(value: Int) {
        assertThrows<IllegalArgumentException> { LottoNumber.from(value) }
    }

    @Test
    fun `같은 번호를 가진 로또 번호 객체들은 서로 동일하다`() {
        val lottoNumber1: LottoNumber = LottoNumber.from(1)
        val lottoNumber2: LottoNumber = LottoNumber.from(1)
        assertThat(lottoNumber1).isEqualTo(lottoNumber2)
        assertThat(lottoNumber1).isSameAs(lottoNumber2)
    }

    @Test
    fun `다른 번호를 가진 로또 번호 객체들은 서로 동일하지 않다`() {
        val lottoNumber1: LottoNumber = LottoNumber.from(1)
        val lottoNumber2: LottoNumber = LottoNumber.from(2)
        assertThat(lottoNumber1).isNotEqualTo(lottoNumber2)
        assertThat(lottoNumber1).isNotSameAs(lottoNumber2)
    }
}
