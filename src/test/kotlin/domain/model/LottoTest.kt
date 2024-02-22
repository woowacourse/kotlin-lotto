package domain.model

import domain.model.Lotto.Companion.DUPLICATED_NUMBER_EXCEPTION_MESSAGE
import domain.model.Lotto.Companion.LOTTO_NUMBER_SIZE
import domain.model.Lotto.Companion.NUMBER_SIZE_EXCEPTION_MESSAGE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoTest {

    @Test
    fun `로또 번호가 고유한 6개일 때 예외를 던지지 않는다`() {
        val lottoNumbers = List(6) { LottoNumber(it + 1) }
        assertThat(lottoNumbers.size).isEqualTo(LOTTO_NUMBER_SIZE)
    }

    @ParameterizedTest
    @ValueSource(ints = [5, 7])
    fun `로또 번호가 6개가 아닐 때 예외를 던진다`(size: Int) {
        val lottoNumbers = List(size) { LottoNumber(it + 1) }
        val exception = assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
        assertThat(exception.message).isEqualTo(NUMBER_SIZE_EXCEPTION_MESSAGE)
    }

    @Test
    fun `로또 번호가 서로 중복될 때 예외를 던진다`() {
        val lottoNumbers = List(6) { LottoNumber(1) }
        val exception = assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
        assertThat(exception.message).isEqualTo(DUPLICATED_NUMBER_EXCEPTION_MESSAGE)
    }
}
