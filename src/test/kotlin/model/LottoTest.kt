package model

import model.Lotto.Companion.LOTTO_NUMBER_SIZE
import model.Lotto.Companion.NUMBER_SIZE_EXCEPTION_MESSAGE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoTest {

    @ParameterizedTest
    @ValueSource(ints = [5, 7])
    fun `로또 번호가 6개가 아닐 때 예외를 던진다`(size: Int) {
        val lottoNumbers = List(size) { LottoNumber(it + 1) }
        val exception = assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
        assertThat(exception.message).isEqualTo(NUMBER_SIZE_EXCEPTION_MESSAGE)
    }

    @Test
    fun `로또 번호가 6개일 때 예외를 던지지 않는다`() {
        val lottoNumbers = List(6) { LottoNumber(it + 1) }
        assertThat(lottoNumbers.size).isEqualTo(LOTTO_NUMBER_SIZE)
    }
}
