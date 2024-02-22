package domain

import domain.model.LottoNumber
import domain.model.LottoNumber.Companion.NUMBER_RANGE_EXCEPTION_MESSAGE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 45])
    fun `당첨번호가 1 ~ 45면 예외를 던지지 않는다`(number: Int) {
        val lottoNumber = LottoNumber(number)

        assertThat(lottoNumber.value).isEqualTo(number)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 46])
    fun `당첨번호가 1 ~ 45를 벗어나면 예외를 던진다`(number: Int) {
        val exception = assertThrows<IllegalArgumentException> { LottoNumber(number) }

        assertThat(exception.message).isEqualTo(NUMBER_RANGE_EXCEPTION_MESSAGE)
    }
}
