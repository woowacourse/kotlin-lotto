package domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 46])
    fun `당첨번호가 1 ~ 45 사이를 벗어나면 예외를 던지는 지`(number: Int) {
        val exception = assertThrows<IllegalArgumentException> { LottoNumber(number) }

        assertThat(exception.message).isEqualTo("${number}는 1 ~ 45 사이의 정수가 아닙니다.")
    }
}
